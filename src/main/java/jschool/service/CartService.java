package jschool.service;

import jschool.dto.CartDTO;
import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface CartService {
     Cart findById(int id);
     LinkedHashMap<String,Object> formAnonymousCartByJSONString(String json);
     Cart setNewCartForUser(User curU);
     void mergeUserCartWithAnon(String json);
     CartDTO getCurUserCart();
     void clearCartForCurUser();
}
