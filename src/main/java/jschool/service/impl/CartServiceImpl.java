package jschool.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jschool.dao.CartDAO;
import jschool.dao.CartItemDAO;
import jschool.dao.ProductDAO;
import jschool.dto.CartDTO;
import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.Product;
import jschool.model.User;
import jschool.service.CartService;
import jschool.service.DTOConverterService;
import jschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    private final DTOConverterService dtoConverterService;
    private final UserService userService;
    private final CartDAO cartDAO;
    private final ProductDAO productDAO;
    private final CartItemDAO cartItemDAO;

    @Autowired
    public CartServiceImpl(UserService userService, DTOConverterService dtoConverterService, CartDAO cartDAO, ProductDAO productDAO, CartItemDAO cartItemDAO) {
        this.userService = userService;
        this.dtoConverterService = dtoConverterService;
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
        this.cartItemDAO = cartItemDAO;
    }


    @Override
    @Transactional
    public Cart findById(int id) {
        return  this.cartDAO.findById(id);
    }


    /**accepts anon json cart from cookie and transfer it into hashmap object,
     * which can be easily parsed in jsp pages */
    @Override
    public LinkedHashMap<String,Object> formAnonymousCartByJSONString(String json){
        LinkedHashMap<String,Object> cart = new LinkedHashMap<>();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            cart.put("totalAmount",jsonNode.get("totalAmount").asInt());
            cart.put("totalPrice",jsonNode.get("totalPrice").asInt());
            LinkedList<LinkedHashMap> cartItemList = new LinkedList<>();
            JsonNode cartItemArr = jsonNode.get("cartItemArr");
            for (final JsonNode objNode : cartItemArr) {
                LinkedHashMap<String,Object> cartItemObj = new LinkedHashMap();
                System.out.println(objNode);
                cartItemObj.put("product_id",Integer.valueOf(objNode.get("product_id").asText()));
                cartItemObj.put("product_name",objNode.get("product_name").asText());
                cartItemObj.put("amount",Integer.valueOf(objNode.get("amount").asText()));
                cartItemObj.put("price",Integer.valueOf(objNode.get("price").asText()));
                cartItemList.add(cartItemObj);
            }
            cart.put("cartItem", cartItemList);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(cart.keySet().toArray()));
        return cart;

    }

    /**gets cart for user or sets new cart in case there was no cart at all*/
    @Override
    @Transactional
    public CartDTO getCurUserCart(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("in get cut user caart");
        System.out.println(auth);
        User curU = this.userService.getUserByEmail(auth.getName());

        if (!Objects.isNull(curU)){
            System.out.println(curU.getId());
            Cart cart =  findById(curU.getId());
            System.out.println("CCCART");
            System.out.println(Objects.isNull(cart));
            if (!Objects.isNull(cart)){
                System.out.println(cart.getUser_id());
                return dtoConverterService.getCartDTO(cart);
            }else{
                System.out.println("NUUL");
                Cart curC = setNewCartForUser(curU);
                System.out.println(curC.getUser_id());

                return dtoConverterService.getCartDTO(curC);
            }
        }
        return null;
    }
    @Override
    @Transactional
    public Cart setNewCartForUser(User curU){
        Cart curC = new Cart();
        curC.setUser(curU);
        curC.setTotalPrice(0);
        curC.setTotalAmount(0);
        curC.setCartItem(new HashSet<>());
        System.out.println(curC.getUser_id());
        this.cartDAO.add(curC);
        return curC;
    }

    /**merges anon and notanon carts into one cart object for logged in user*/
    @Override
    @Transactional
    public void mergeUserCartWithAnon(String json){
        LinkedHashMap<String,Object> anonCart = formAnonymousCartByJSONString(json);
        Cart curC = userService.loggedIn().getCart();
        if (Objects.isNull(curC)){
            curC = setNewCartForUser(userService.loggedIn());
        }
        Set<CartItem> userItems = (Objects.isNull(curC) || curC.getCartItem().isEmpty()) ? new LinkedHashSet<>() : curC.getCartItem();
        LinkedList<LinkedHashMap> cartItemList = (LinkedList<LinkedHashMap>) anonCart.get("cartItem");
        for ( LinkedHashMap obj : cartItemList) {
            Integer product_id  = (Integer) obj.get("product_id");
            String pn = (String) obj.get("product_name");
            Integer amount = (Integer) obj.get("amount");
            Integer price = (Integer) obj.get("price");
            CartItem ci = userItems.stream().filter(cartItem -> product_id.equals(cartItem.getProduct().getId())).findFirst().orElse(null);

            curC.setTotalAmount(curC.getTotalAmount() + amount);
            curC.setTotalPrice(curC.getTotalPrice() + price);

            if (!Objects.isNull(ci)){
                //CHECK IF NAMES SAME
                ci.setPrice(ci.getPrice() + price);
                ci.setAmount(ci.getAmount() + amount);
                cartItemDAO.updateCartItem(ci);
            }else {
                CartItem newCartItem = new CartItem();
                newCartItem.setAmount(amount);
                newCartItem.setPrice(price);
                Product newP= productDAO.findById(product_id);
                if (!Objects.isNull(newP)){
                    newCartItem.setProduct(newP);
                }else{continue;}
                newCartItem.setCart(curC);
                curC.getCartItem().add(newCartItem);
                cartItemDAO.addCartItem(newCartItem);
            }
        }
        cartDAO.update(curC);
    }

    /**removes cart objec for logged user when order was made*/
    @Override
    @Transactional
    public void clearCartForCurUser(){
        System.out.println("in clear cart");
        cartDAO.remove( userService.loggedIn().getCart());
        System.out.println("removed");
    }

}
