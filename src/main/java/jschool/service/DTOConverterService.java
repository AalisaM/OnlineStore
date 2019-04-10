package jschool.service;

import jschool.dto.*;
import jschool.model.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface DTOConverterService {
    CartExtendedDTO getCartExtendedDto(LinkedHashMap<String,Object> anon, CartDTO cur, List<PaymentTypeDTO> p,
                                       List<ShippingDTO> s, User u);

    UserDTO getUserDTO(User u);

    List<UserDTO> getUserDTOList(List<User> u);

    OrderProductDTO getOrderProductDTO(OrderProduct o);

    Set<OrderProductDTO> getOrderProductDTOList(Set<OrderProduct> o);

    OrderDTO getOrderDTO(Order o);

    List<OrderDTO> getOrderDTOList(List<Order> o);

    CartDTO getCartDTO(Cart c);

    List<CartItemDTO> getCartItemDTOList(Set<CartItem> list);

    AddressDTO getAddressDTO(Address a);

    Set<AddressDTO> getAddressDTOList(Set<Address> a);

    PaymentTypeDTO getPaymentTypeDTO(PaymentType p);

    List<PaymentTypeDTO> getPaymentTypeDTOList(List<PaymentType> list);

    ShippingDTO getShippingDTO(ShippingType s);

    List<ShippingDTO> getShippingDTOList(List<ShippingType> a);

    PaymentStatusDTO getPaymentStatusDTO(PaymentStatus p);

    ProductDTO getProductDTO(Product p);

    ProductRawDTO getProductRawDTO(Product p);

    CategoryRawDTO getCategoryRAWDTO(Category c);

    List<CategoryRawDTO> getCategoryRAWDTOList(List<Category> a);

    Set<ProductDTO> getProductDTOList(Collection<Product> set);

    Set<ProductRawDTO> getProductRawDTOList(Collection<Product> set);

    CartItemDTO getCartItemDTO(CartItem c);

    CategoryDTO getCategoryDTO(Category c);

    List<CategoryDTO> getCategoryDTOList(List<Category> a);

    OrderStatusDTO getOrderStatusDTO(OrderStatus status);

    List<OrderStatusDTO> getOrderStatusDTOList(List<OrderStatus> a);

    CategorizedOrdersDTO getCategorizedOrdersDTO(List<OrderDTO> unpaidOrders,
                                                 List<OrderDTO> paidOrders,
                                                 List<OrderDTO> processedOrders,
                                                 Integer userRoleId);
}
