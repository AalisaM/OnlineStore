package jschool.service.impl;

import jschool.dao.*;
import jschool.dto.OrderDTO;
import jschool.dto.OrderProductDTO;
import jschool.dto.OrderStatusDTO;
import jschool.dto.ShippingDTO;
import jschool.dto.*;
import jschool.model.*;
import jschool.service.CartService;
import jschool.service.DTOConverterService;
import jschool.service.OrderService;
import jschool.service.UserService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final DTOConverterService dtoConverterService;
    private UserService userService;
    private OrderDAO orderDAO;
    private ShippingTypeDAO shippingTypeDAO;
    private OrderProductDAO orderProductDAO;
    private ProductDAO productDAO;
    private final UserDAO userDAO;
    private final PaymentStatusDAO paymentStatusDAO;
    private final PaymentTypeDAO paymentTypeDAO;
    private final OrderStatusDAO orderStatusDAO;
    private final OrderHistoryDAO orderHistoryDAO;
    private final CartService cartService;


    @Autowired
    public OrderServiceImpl(DTOConverterService dtoConverterService, UserService userService,
                            OrderDAO orderDAO, ShippingTypeDAO shippingTypeDAO, OrderProductDAO orderProductDAO,
                            ProductDAO productDAO, UserDAO userDAO, PaymentStatusDAO paymentStatusDAO, PaymentTypeDAO paymentTypeDAO, OrderStatusDAO orderStatusDAO, OrderHistoryDAO orderHistoryDAO, CartService cartService) {
        this.dtoConverterService = dtoConverterService;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
        this.paymentStatusDAO = paymentStatusDAO;
        this.paymentTypeDAO = paymentTypeDAO;
        this.orderStatusDAO = orderStatusDAO;
        this.orderHistoryDAO = orderHistoryDAO;
        this.shippingTypeDAO =shippingTypeDAO;
        this.userService = userService;
        this.orderProductDAO = orderProductDAO;
        this.orderDAO = orderDAO;
        this.cartService = cartService;
    }


    @Override
    public List<Order> listOrders() {
        return  this.orderDAO.list();
    }

    @Override
    public List<ShippingDTO> listShippingType() {
        return dtoConverterService.getShippingDTOList(this.shippingTypeDAO.list());
    }

    @Override
    public List<PaymentTypeDTO> listPaymentType() {
        return dtoConverterService.getPaymentTypeDTOList(this.paymentTypeDAO.list());
    }

    @Override
    public void update(Order o) {
        this.orderDAO.update(o);
    }



    /**changes order status to cancelled and returns payment if necessary*/
    @Override
    public Message cancelOrder(OrderDTO o) {
        Message m = new Message();
        try {
            Order order = this.orderDAO.findById(o.getId());
            order.setOrderStatus(this.orderStatusDAO.getByStatus("cancelled"));
            if (order.getPaymentStatus().getStatus().equals("paid")) {
                m.getConfirms().add("We sent you refund. Please contanct manager if money did not come.\n manager@imaginarium.com");
                order.setPaymentStatus(this.paymentStatusDAO.getByStatus("not paid"));
            }
            updateProductStorageAmount(o, false);
            this.orderDAO.update(order);
            saveOrderHistory(o,order);
        }catch (Exception e){
          m.getErrors().add("cannot cancel the order");
        }
        return m;
    }


    @Override
    public Message updateOrder(OrderDTO o) {
        Message m = new Message();
        try {
            Order order = this.orderDAO.findById(o.getId());
            order.setOrderStatus(this.orderStatusDAO.findById(o.getOrderStatus().getId()));
            order.setPaymentStatus(this.paymentStatusDAO.findById(o.getPaymentStatus().getId()));

            if (o.getOrderStatus().getStatus().equals("delivered")){
                order.setPaymentStatus(this.paymentStatusDAO.getByStatus("paid"));
            }
            this.orderDAO.update(order);
            saveOrderHistory(o,order);
            m.getConfirms().add("We fixed order information, track in cabinet.");
        }catch (Exception e){
            e.printStackTrace();
            m.getErrors().add("cannot update the order. Try again or contact admin@imaginarium.com ");
        }
        return m;
    }

    /**updates product amount in the store when user makes order.*/
    private void updateProductStorageAmount(OrderDTO orderDTO, boolean isToDec){
        for(OrderProductDTO opDto : orderDTO.getOrderProducts()){
            int productid = opDto.getProductid();
            int amount = isToDec ? opDto.getAmount() : - opDto.getAmount();

            Product product = this.productDAO.findById(productid);
            product.setAmount(product.getAmount() - amount);

//            product.setAmount(product.getAmount() - amount < 0 ? 0 : product.getAmount() - amount);
            this.productDAO.update(product);
        }
    }
    private void saveOrderHistory(OrderDTO orderDTO, Order orderNew){
        OrderHistory oh = new OrderHistory();
        oh.setAddress(orderDTO.getAddress());
        oh.setAmount(orderDTO.getAmount());
        oh.setClient_name(orderNew.getUser().getFullName());
        oh.setDate(orderNew.getDate());
        oh.setOrder(orderNew);
        oh.setOrderStatus(orderNew.getOrderStatus().getStatus());
        oh.setPaymentStatus(orderNew.getPaymentStatus().getStatus());
        //
        oh.setPaymentType(orderDTO.getPaymentType().getType());
        //
        oh.setShippingType(orderDTO.getShippingType().getType());
        oh.setTotalPrice(orderNew.getTotalPrice());
        oh.setEmail(orderNew.getUser().getEmail());

        this.orderHistoryDAO.add(oh);
    }
    @Override
    public void add(Order c) {
        this.orderDAO.add(c);

    }

    /**checks products amount in store, returns sad message in case its not enought gods.*/
    private boolean checkAmount(Message m,  List<OrderProduct> op,OrderDTO orderDTO){
        for(OrderProductDTO opDto : orderDTO.getOrderProducts()){
            int productid = opDto.getProductid();
            int amount = opDto.getAmount();

            Product product = this.productDAO.findById(productid);
            int storageAmount = product.getAmount();
            if (amount > storageAmount){
                m.getErrors().add(String.format("Sorry, item '%s' is not performed in enough quantity ",product.getName() ));
                return false;
            }
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setAmount(amount);
            orderProduct.setPrice(opDto.getPrice());
            orderProduct.setProduct(product);
            op.add(orderProduct);
        }
        return true;

    }
    @Override
    public Message add(OrderDTO orderDTO) {
        Message message = new Message();
        if (orderDTO.getAmount() <=0) {
            message.getErrors().add("cart is empty");
            return message;
        }
        Order orderNew = new Order();
        orderNew.setDate(new Timestamp(System.currentTimeMillis()));
        orderNew.setTotalPrice(orderDTO.getTotalPrice());
        orderNew.setUser(this.userDAO.findByEmail(orderDTO.getEmail()));
        orderNew.setPaymentType(this.paymentTypeDAO.findByName(orderDTO.getPaymentType().getType()));
        orderNew.setShippingType(this.shippingTypeDAO.findById(orderDTO.getShippingType().getId()));

        orderNew.setPaymentStatus(this.paymentStatusDAO.getByStatus("not paid"));
        orderNew.setOrderStatus(this.orderStatusDAO.getByStatus("not paid"));

        List<OrderProduct> op = new LinkedList<>();
        //check
         if (!checkAmount(message,op,orderDTO)){
             return message;
         };

        //reduceAmount
        updateProductStorageAmount(orderDTO, true);

        this.orderDAO.add(orderNew);

        for (OrderProduct o : op){
            o.setOrder(orderNew);
            this.orderProductDAO.add(o);
        }

        //OrderHistory
        //TODO SET LIST OF PRODUCT NAMES
        saveOrderHistory(orderDTO,orderNew);
        message.getConfirms().add(String.format("Order %d : %d items, %d total price is successfully saved",
                orderNew.getId(), orderDTO.getAmount(),orderNew.getTotalPrice()));

        orderDTO.setId(orderNew.getId());
        orderDTO.setPaymentStatus(dtoConverterService.getPaymentStatusDTO(orderNew.getPaymentStatus()));
        orderDTO.setOrderStatus(dtoConverterService.getOrderStatusDTO(orderNew.getOrderStatus()));
        return message;
    }

    @Override
    public OrderDTO convertToDTO(Order o){
        return dtoConverterService.getOrderDTO(o);
    }


    @Override
    public Integer getCurUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            User curU = userDAO.findByEmail(auth.getName());
            Collection<? extends GrantedAuthority> authorities
                    = auth.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("USER")) {
                    return curU.getId();
                } else if (grantedAuthority.getAuthority().equals("ADMIN") || grantedAuthority.getAuthority().equals("MANAGER")) {
                    return -1;
                }
            }
        }
        return null;
    }

    @Transactional
    public User getCurUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            User curU = userDAO.findByEmail(auth.getName());
            return curU;
        }else return null;
    }


    /**helper method, returns unpaid, paid or delivered orders list depends on type value.*/
    private List<OrderDTO> getProperList(int type, boolean isAdmin){
        User cUser = getCurUser();
        Integer id =  Objects.isNull(cUser) ? null : cUser.getId();
        if (isAdmin){
            id = getCurUserId();
        }
        List<Order> orders = new LinkedList<>();
        if (Objects.isNull(id)){
            return null;
        }
        switch (type) {
            case -1: orders = this.orderDAO.getUnpaidOrderList(id);break;
            case 0: orders = this.orderDAO.getPaidOrderList(id);break;
            case 1: orders =this.orderDAO.getProcessedOrderList(id);break;
        }
        List<OrderDTO> dto = new LinkedList<>();
        for (Order o : orders){
            dto.add(convertToDTO(o));
        }
        return dto;
    }

    @Override
    public List<OrderDTO> getUnpaidOrderList(boolean isAdmin){
       return getProperList(-1,isAdmin);
    };

    @Override
    public List<OrderDTO> getPaidOrderList(boolean isAdmin){
        return getProperList(0, isAdmin);
    };

    @Override
    public List<OrderDTO> getProcessedOrderList(boolean isAdmin) {
        return getProperList(1, isAdmin);
    };


    @Override
    public List<OrderStatus> getNextOrderStatus(OrderStatus status){
        List<OrderStatus> resultlist = new LinkedList<>();
        resultlist.add(status);
        switch (status.getId()){
            case 1  : resultlist.add(this.orderStatusDAO.findById(2));
                      resultlist.add(this.orderStatusDAO.findById(3));
                      break;
            case 2: resultlist.add(this.orderStatusDAO.findById(3));break;
            case 3: resultlist.add(this.orderStatusDAO.findById(4));break;
            default: break;
        }
        return resultlist;
    }

    /**builds status graph*/
    @Override
    public LinkedHashMap<OrderStatusDTO, List<OrderStatusDTO>> getStatusGraph(){
        List<OrderStatus> list = this.orderStatusDAO.list();
        LinkedHashMap<OrderStatusDTO,List<OrderStatusDTO>> map = new LinkedHashMap<>();
        for (OrderStatus l : list){
            map.put(dtoConverterService.getOrderStatusDTO(l),dtoConverterService.getOrderStatusDTOList(this.getNextOrderStatus(l)));
        }
        return map;
    }

    @Override
    public CartDTO getCurUserCart(){
        return this.cartService.getCurUserCart();
    }


    /**returns dto for proper cart display*/
    @Override
    public CartExtendedDTO getDTOForCart(){
        return dtoConverterService.getCartExtendedDto(null,
                getCurUserCart(),
                listPaymentType(),
                listShippingType(),
                this.userService.loggedIn());

    }

    /**returns proper dto for  given cart, where anon cart got from cookie*/

    @Override
    public CartExtendedDTO getDTOForCart(String anonCookie){
        User u = this.userService.loggedIn();
        LinkedHashMap anonCart = null;
        CartDTO curC = null;
        if (Objects.isNull(u)) {
            if (!Objects.isNull(anonCookie)){
                anonCart = this.cartService.formAnonymousCartByJSONString(new String(Base64.getDecoder().decode(anonCookie.getBytes())));
            }
        }else{
            curC = getCurUserCart();
        }
        return dtoConverterService.getCartExtendedDto(
                anonCart,
                curC,
                listPaymentType(),
                listShippingType(),
                u);
    }

    @Override
    public CategorizedOrdersDTO getCategorizedOrdersDTO(boolean isAdmin){
        return dtoConverterService.getCategorizedOrdersDTO(
                this.getUnpaidOrderList(isAdmin),
                this.getPaidOrderList(isAdmin),
                this.getProcessedOrderList(isAdmin),
                this.getCurUserId());
    }

}
