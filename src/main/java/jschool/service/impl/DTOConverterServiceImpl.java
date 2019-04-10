package jschool.service.impl;

import jschool.dto.*;
import jschool.model.*;
import jschool.service.DTOConverterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**This service is used to convert object to dto for proper data manipulations in controller-service link.*/

@Service
public class DTOConverterServiceImpl implements DTOConverterService {

    private final ModelMapper modelMapper;

    @Autowired
    public DTOConverterServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CartExtendedDTO getCartExtendedDto(LinkedHashMap<String,Object> anon, CartDTO cur, List<PaymentTypeDTO> p,
                                              List<ShippingDTO> s, User u){
       CartExtendedDTO dto = new CartExtendedDTO();
       dto.setCartAnon(anon);
       dto.setCurCart(cur);
       dto.setPaymentType(p);
       dto.setShippingType(s);
       dto.setUser(getUserDTO(u));
       return dto;
    }

    @Override
    public UserDTO getUserDTO(User u){
        if (Objects.isNull(u)) return null;
        UserDTO dto = new UserDTO();
        dto.setActiveAddressId(getAddressDTO(u.getActiveAddressId()));
        dto.setAddresses(getAddressDTOList(u.getAddresses()));
        dto.setBirth(u.getBirth());
        dto.setEmail(u.getEmail());
        dto.setFullName(u.getFullName());
        dto.setPassword(u.getPassword());
        dto.setAdmin(u.isAdmin());
        dto.setId(u.getId());
        return dto;
    }

    @Override
    public List<UserDTO> getUserDTOList(List<User> u){
        List<UserDTO> dto = new LinkedList<>();
        for(User ci : u){
            dto.add(getUserDTO(ci));
        }
        return dto;
    }

    @Override
    public OrderProductDTO getOrderProductDTO(OrderProduct o){
        if (Objects.isNull(o)) return null;
        OrderProductDTO dto = new OrderProductDTO();
        dto.setProductName(o.getProduct().getName());
        dto.setProductid(o.getProduct().getId());
        dto.setPrice(o.getPrice());
        dto.setAmount(o.getAmount());
        return dto;
    }
    @Override
    public Set<OrderProductDTO> getOrderProductDTOList(Set<OrderProduct> o){
        Set<OrderProductDTO> dto = new HashSet<>();
        for(OrderProduct ci : o){
            dto.add(getOrderProductDTO(ci));
        }
        return dto;
    }

    @Override
    public OrderDTO getOrderDTO(Order o){
        if (Objects.isNull(o)) return null;
        OrderDTO odto = new OrderDTO();
        Address a = o.getUser().getActiveAddressId();
        if (!Objects.isNull(a)){
            odto.setAddress(a.getAddress());
        }
        odto.setAmount(odto.getAmount());
        odto.setEmail(o.getUser().getEmail());
        odto.setOrderStatus(getOrderStatusDTO(o.getOrderStatus()));
        odto.setPaymentStatus(getPaymentStatusDTO(o.getPaymentStatus()));
        odto.setShippingType(getShippingDTO(o.getShippingType()));
        odto.setPaymentType(getPaymentTypeDTO(o.getPaymentType()));
        odto.setId(o.getId());
        List<OrderProductDTO> op = new LinkedList<>();
        for (OrderProduct p: o.getOrderProducts()){
            OrderProductDTO opdto = new OrderProductDTO();
            opdto.setAmount(p.getAmount());
            opdto.setPrice(p.getPrice());
            opdto.setProductid(p.getId());
            opdto.setProductName(p.getProduct().getName());
            odto.setTotalPrice(p.getPrice() + odto.getTotalPrice());
            odto.setAmount(p.getAmount() + odto.getAmount());
            op.add(opdto);
        }
        odto.setOrderProducts(op);
        return odto;
     }

    @Override
    public  List<OrderDTO> getOrderDTOList(List<Order> o){
        List<OrderDTO> dto = new LinkedList<>();
        for(Order ci : o){
            dto.add(getOrderDTO(ci));
        }
        return dto;
    }
    @Override
    public CartDTO getCartDTO(Cart c){
        if (Objects.isNull(c)) return null;
        CartDTO dto = new CartDTO();
        dto.setCartItem(getCartItemDTOList(c.getCartItem()));
        dto.setTotalAmount(c.getTotalAmount());
        dto.setTotalPrice(c.getTotalPrice());
        dto.setUser(getUserDTO(c.getUser()));
        dto.setUser_id(c.getUser_id());
        return dto;
    }

    @Override
    public List<CartItemDTO> getCartItemDTOList(Set<CartItem> list){
        List<CartItemDTO> dto = new LinkedList<>();
        for(CartItem ci : list){
            dto.add(getCartItemDTO(ci));
        }
        return dto;
    }

    @Override
    public AddressDTO getAddressDTO(Address a){
        if (Objects.isNull(a)) return null;
        return modelMapper.map(a, AddressDTO.class);
    }

    @Override
    public Set<AddressDTO> getAddressDTOList(Set<Address> a){
        Set<AddressDTO> dto = new HashSet<>();
        for(Address address : a){
            dto.add(getAddressDTO(address));
        }
        return  dto;
    }

    @Override
    public PaymentTypeDTO getPaymentTypeDTO(PaymentType p){
        if (Objects.isNull(p)) return null;
        return modelMapper.map(p, PaymentTypeDTO.class);
    }

    @Override
    public List<PaymentTypeDTO> getPaymentTypeDTOList(List<PaymentType> list){
        List<PaymentTypeDTO> dto = new LinkedList<>();
        for(PaymentType p : list){
            dto.add(getPaymentTypeDTO(p));
        }
        return dto;
    }

    @Override
    public ShippingDTO getShippingDTO(ShippingType s){
        if (Objects.isNull(s)) return null;
        return modelMapper.map(s, ShippingDTO.class);
    }

    @Override
    public List<ShippingDTO> getShippingDTOList(List<ShippingType> a){
        List<ShippingDTO> dto = new LinkedList<>();
        for(ShippingType type : a){
            dto.add(getShippingDTO(type));
        }
        return  dto;
    }

    @Override
    public PaymentStatusDTO getPaymentStatusDTO(PaymentStatus p){
        if (Objects.isNull(p)) return null;
        return modelMapper.map(p, PaymentStatusDTO.class);
    }

    @Override
    public ProductDTO getProductDTO(Product p){
        if (Objects.isNull(p)) return null;
        ProductDTO dto = new ProductDTO();
        dto.setAmount(p.getAmount());
        dto.setDescription(p.getDescription());
        dto.setWeight(p.getWeight());
        dto.setVolume(p.getVolume());
        dto.setPrice(p.getPrice());
        dto.setName(p.getName());
        dto.setMinPlayerAmount(p.getMinPlayerAmount());
        dto.setMaxPlayerAmount(p.getMaxPlayerAmount());
        dto.setId(p.getId());
        dto.setCartItem(this.getCartItemDTOList(p.getCartItem()));
        dto.setOrderProducts(this.getOrderProductDTOList(p.getOrderProducts()));
        dto.setImageSource(p.getImageSource());
        dto.setCategory(this.getCategoryRAWDTO(p.getCategory()));
        //ProductDTO productDTO = modelMapper.map(p, ProductDTO.class);
       // productDTO.setUploadFile(null);
        return dto;
    }

    @Override
    public ProductRawDTO getProductRawDTO(Product p){
        if (Objects.isNull(p)) return null;
        ProductRawDTO dto = new ProductRawDTO();
        dto.setAmount(p.getAmount());
        dto.setDescription(p.getDescription());
        dto.setWeight(p.getWeight());
        dto.setVolume(p.getVolume());
        dto.setPrice(p.getPrice());
        dto.setName(p.getName());
        dto.setMinPlayerAmount(p.getMinPlayerAmount());
        dto.setMaxPlayerAmount(p.getMaxPlayerAmount());
        dto.setId(p.getId());
        return dto;
    }

    @Override
    public CategoryRawDTO getCategoryRAWDTO(Category c){
        if (Objects.isNull(c)) return null;
        CategoryRawDTO dto = new CategoryRawDTO();
        dto.setId(c.getId());
        dto.setTitle(c.getName());
        dto.setParentId(c.getParentId());
        return dto;
    }

    @Override
    public List<CategoryRawDTO> getCategoryRAWDTOList(List<Category> a){
        List<CategoryRawDTO> dto = new LinkedList<>();
        for(Category type : a){
            dto.add(getCategoryRAWDTO(type));
        }
        return  dto;
    }

    @Override
    public Set<ProductDTO> getProductDTOList(Collection<Product> set){
        Set<ProductDTO> dto = new HashSet<>();
        for(Product p: set){
            dto.add(getProductDTO(p));
        }
        return dto;
    }

    @Override
    public Set<ProductRawDTO> getProductRawDTOList(Collection<Product> set){
        Set<ProductRawDTO> dto = new HashSet<>();
        for(Product p: set){
            dto.add(getProductRawDTO(p));
        }
        return dto;
    }
    @Override
    public CartItemDTO getCartItemDTO(CartItem c){
        if (Objects.isNull(c)) return null;
        CartItemDTO ci = new CartItemDTO();
        ci.setProduct(getProductRawDTO(c.getProduct()));
        ci.setAmount(c.getAmount());
        ci.setPrice(c.getPrice());
        ci.setId(c.getId());
        return ci;
    };

    @Override
    public CategoryDTO getCategoryDTO(Category c){
        if (Objects.isNull(c)) return null;
        CategoryDTO dto = new CategoryDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setProducts(getProductRawDTOList(c.getProducts()));
        dto.setParentId(c.getParentId());
        return dto;
    }

    @Override
    public List<CategoryDTO> getCategoryDTOList(List<Category> a){
        List<CategoryDTO> dto = new LinkedList<>();
        for(Category type : a){
            dto.add(getCategoryDTO(type));
        }
        return  dto;
    }

    @Override
    public OrderStatusDTO getOrderStatusDTO(OrderStatus status){
        if (Objects.isNull(status)) return null;
        return modelMapper.map(status, OrderStatusDTO.class);
    }

    @Override
    public List<OrderStatusDTO> getOrderStatusDTOList(List<OrderStatus> a){
        List<OrderStatusDTO> dto = new LinkedList<>();
        for(OrderStatus type : a){
            dto.add(getOrderStatusDTO(type));
        }
        return  dto;
    }

    @Override
    public CategorizedOrdersDTO getCategorizedOrdersDTO(List<OrderDTO> unpaidOrders,
                                                        List<OrderDTO> paidOrders,
                                                        List<OrderDTO> processedOrders,
                                                        Integer userRoleId){
        CategorizedOrdersDTO dto = new CategorizedOrdersDTO();
        dto.setPaidOrders(paidOrders);
        dto.setUnpaidOrders(unpaidOrders);
        dto.setProcessedOrders(processedOrders);
        dto.setUserRoleId(userRoleId);
        return dto;
    }

}
