package jschool.controller;

import jschool.dto.OrderDTO;
import jschool.service.*;
import jschool.validator.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

/**
 * Controller is responsible for cart page,
 * creating and approving orders by users.
 * Did not moved orders in another controller because it strongly tied with carts.
 * */
@Controller
public class CartController {
    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);
    private OrderService orderService;
    private CartService cartService;
    private CartItemService cartItemService;

    @Autowired
    CartController(OrderService orderService, CartService cartService, CartItemService cartItemService){
        this.orderService = orderService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }


    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String currentUserCart(@CookieValue(value = "cartItem", required = false) String fooCookie, Model model) {
        model.addAttribute("cartExtendedDTO", this.orderService.getDTOForCart(fooCookie));
        return "cart";
    }

    @RequestMapping(value= "/cart/makeOrder", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView makeOrder(@RequestBody OrderDTO p, ModelMap model){
        log.info("in  make order;");
        Message message = this.orderService.add(p);
        model.addAttribute("message", message);
        if (message.getErrors().size() > 0){
            model.addAttribute("cartExtendedDTO", this.orderService.getDTOForCart());
            return new ModelAndView("cart", model);
        }else{
            this.cartService.clearCartForCurUser();
            model.addAttribute("orderdto",p);
            return new ModelAndView("orderApproval", model);
        }
    }

    @RequestMapping(value= "/cart/cancelOrder", method = RequestMethod.POST)
    public String cancelOrder(@RequestBody OrderDTO p, ModelMap model){
        log.info(p.toString());
        Message message = this.orderService.cancelOrder(p);
        model.addAttribute("message", message);
        return "redirect:/cart";
    }

    @RequestMapping(value= "/cart/editOrder", method = RequestMethod.POST)
    public String approveOrder(@RequestBody OrderDTO p, Model model, RedirectAttributes redir){
        Message message = this.orderService.updateOrder(p);
        redir.addFlashAttribute("message", message);
        return "redirect:/";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String ordersUserList(@CookieValue(value = "cartItem", required = false) String fooCookie,Model model) {
        Integer id = this.orderService.getCurUserId();
        if (!Objects.isNull(id)) {
            model.addAttribute("categorizedOrders", this.orderService.getCategorizedOrdersDTO(true));
            if (id < 0){
                model.addAttribute("nextOrderStatus", this.orderService.getStatusGraph());
            }
            return "notProcessedOrdersTemplate";
        }else {
            Message m = new Message();
            try {
                model.addAttribute("cartAnon", this.cartService.formAnonymousCartByJSONString(new String(Base64.getDecoder().decode(fooCookie.getBytes()))));
                m.getErrors().add("please login first");
            }catch (Exception e){
                m.getErrors().add("as anonymous user you have no orders. Please collect cart and login.");
            }
            model.addAttribute("message", m);
            return "cart";
        }
    }
    @RequestMapping(value = "/userOrder", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView userOrder(ModelMap model) {
        Integer id = this.orderService.getCurUserId();
        if (!Objects.isNull(id)) {
            model.addAttribute("categorizedOrders", this.orderService.getCategorizedOrdersDTO(false));
        }
        return new ModelAndView("usersOrdersHistory",model);
    }

    @RequestMapping(value = "/paidorders", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView paidOrdersList(ModelMap model) {
        Integer id = this.orderService.getCurUserId();
        if (!Objects.isNull(id)) {
            model.addAttribute("orders", this.orderService.getCategorizedOrdersDTO(true).getPaidOrders());
            if (id < 0){
                model.addAttribute("nextOrderStatus", this.orderService.getStatusGraph());
            }
        }
        return new ModelAndView("notProcessedOrdersTemplate",model);
    }

    @RequestMapping(value = "/unpaidorders", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView unpaidOrdersList(ModelMap model) {
        Integer id = this.orderService.getCurUserId();
        if (!Objects.isNull(id)) {
            model.addAttribute("orders", this.orderService.getCategorizedOrdersDTO(true).getUnpaidOrders());
            if (id < 0){
                model.addAttribute("nextOrderStatus", this.orderService.getStatusGraph());
            }
        }
        return  new ModelAndView("notProcessedOrdersTemplate",model);
    }

    @RequestMapping(value = "/deliveredorders", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView processedOrdersList(ModelMap model) {
        Integer id = this.orderService.getCurUserId();
        if (!Objects.isNull(id)) {
            model.addAttribute("orders", this.orderService.getCategorizedOrdersDTO(true).getProcessedOrders());
            if (id < 0){
                model.addAttribute("nextOrderStatus", this.orderService.getStatusGraph());
            }
        }
        return  new ModelAndView("notProcessedOrdersTemplate",model);
    }

    //For add and update person both
    @RequestMapping(value= "/cart/addToCart", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView addToCart(@RequestBody String json, ModelMap model) throws IOException {
        Message m = new Message();
        cartItemService.addProductToCart(m, json);
        model.addAttribute("message", m);
        model.addAttribute("curCart", this.cartService.getCurUserCart());
        return new ModelAndView("cartIcon", model);
    }

    //For add and update person both
    @RequestMapping(value= "/cart/removeFromCart", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView removeFromCart(@RequestBody String json, ModelMap model) throws IOException {
        Message m = new Message();
        cartItemService.removeProductFromCart(m, json);
        model.addAttribute("message", m);
        model.addAttribute("curCart", this.cartService.getCurUserCart());
        return new ModelAndView("cartIcon", model);
    }

    @RequestMapping(value= "/cart/processAnonymousCart", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView showCartPage(@CookieValue(value = "cartItem", required = false) String fooCookie, ModelMap model){
        model.addAttribute("cartExtendedDTO", this.orderService.getDTOForCart(fooCookie));
        return new ModelAndView("cartTemplate", model);
    }


}
