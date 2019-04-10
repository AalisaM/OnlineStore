package jschool.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import jschool.dao.OrderHistoryDAO;
import jschool.dao.OrderProductDAO;
import jschool.dao.UserDAO;
import jschool.dto.ProductDTO;
import jschool.dto.ProductRawDTO;
import jschool.dto.UserDTO;
import jschool.model.OrderHistory;
import jschool.model.OrderProduct;
import jschool.model.Product;
import jschool.model.User;
import jschool.service.DTOConverterService;
import jschool.service.StatisticsService;
import jschool.validator.Message;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

  private final OrderHistoryDAO orderHistoryDAO;
  private final UserDAO userDAO;
  private final OrderProductDAO orderProductDAO;
  private final DTOConverterService dtoConverterService;

  @Autowired
  public StatisticsServiceImpl(OrderHistoryDAO orderHistoryDAO, UserDAO userDAO,
                               OrderProductDAO orderProductDAO, DTOConverterService dtoConverterService){
    this.orderHistoryDAO = orderHistoryDAO;
    this.userDAO = userDAO;
    this.orderProductDAO = orderProductDAO;
    this.dtoConverterService = dtoConverterService;
  }

  @Override
  @Transactional
  public Integer getRevenueForSelectedPeriod(LocalDate from, LocalDate to) {
    System.out.println(orderHistoryDAO.getOrdersByDatePeriod(from, to));
    return orderHistoryDAO.getOrdersByDatePeriod(from, to)
            .stream()
            .filter(a -> a.getPaymentStatus().equals("paid"))
            .mapToInt(OrderHistory::getTotalPrice).sum();  }


  /**
   * accepts json {from : "", to: ""} and calculates revenue for period
   * */
  @Override
  @Transactional
  public Integer getRevenueForSelectedPeriod(Message m, String json) {
    JsonNode jsonNode = UtilService.parseJsonInput(json);
    if (Objects.isNull(jsonNode)){
      m.getErrors().add("wrong json for adding");
      return 0;
    };
    String fromJ = jsonNode.get("from").asText();
    String toJ = jsonNode.get("to").asText();
    LocalDate from, to;

    System.out.println(fromJ);
    System.out.println(toJ);

    from = LocalDate.parse(fromJ);
    to = LocalDate.parse(toJ);

    System.out.println(orderHistoryDAO.getOrdersByDatePeriod(from, to));
    return getRevenueForSelectedPeriod(from, to);
  }


  /**
   * Find n users, brought most revenue for given period, where n<= limit
   * */
  @Override
  @Transactional
  public Map<UserDTO, Integer> getMostValuableClientForSelectedPeriod(LocalDate to, LocalDate from, Integer limit){
    Timestamp dbFrom = Timestamp.valueOf(from.atStartOfDay());
    Timestamp dbTo = Timestamp.valueOf(to.atStartOfDay());

    //Temporary realisation until DB schema is changed, after it will be transferred to DAO layer

    Map<String, Integer> emailsToPrice  = orderHistoryDAO.list().stream()
            .filter(a -> a.getPaymentStatus().equals("paid"))
            .filter(a -> a.getDate().after(dbFrom) && a.getDate().before(dbTo))
            .filter(distinctByKey(OrderHistory::getOrder))
            .collect(Collectors.groupingBy(OrderHistory::getEmail,
                    Collectors.summingInt(OrderHistory::getTotalPrice)));

    System.out.println(emailsToPrice);

    Map<User,Integer> top =
            emailsToPrice.entrySet().stream()
                    .sorted(Entry.comparingByValue())
                    .limit(limit)
                    .peek(a -> System.out.println(a.getKey()))
                    .collect(Collectors.toMap(
                            // Would be really nice to add bulk get method to DAO, if this logic won't be transferred to DAO layer
                            a -> userDAO.findByEmail(a.getKey()), Entry::getValue
                    ));


    top.forEach((key, value) -> Hibernate.initialize(key.getAddresses()));
    top.forEach((key, value) -> Hibernate.initialize(key.getRoles()));
    return top.entrySet()
            .stream()
            .collect(Collectors.toMap( a -> dtoConverterService.getUserDTO(a.getKey()), Map.Entry::getValue));
  };

  /**
   * accepts json {from : "", to: "", limit : ""} and find n users, brought most revenue for given period, where n<= limit
   * */
  @Override
  @Transactional
  public Map<UserDTO, Integer> getMostValuableClientForSelectedPeriod(Message m, String json){
    //LocalDate from, LocalDate to, Integer limit) {
    JsonNode jsonNode = UtilService.parseJsonInput(json);
    if (Objects.isNull(jsonNode)){
      m.getErrors().add("wrong json for client period");
      return null;
    };
    String fromJ = jsonNode.get("from").asText();
    String toJ = jsonNode.get("to").asText();
    Integer limit = jsonNode.get("limit").asInt();

    LocalDate from, to;
    from = LocalDate.parse(fromJ);
    to = LocalDate.parse(toJ);

    return getMostValuableClientForSelectedPeriod(to,from,limit);
  }

  /**
   * find n products, brought most revenue for given period, where n<= limit
   * */
  @Override
  @Transactional
  public Map<ProductDTO, Integer> getMostOrderedProductsForSelectedPeriod(LocalDate to, LocalDate from, Integer limit){
    Timestamp dbFrom = Timestamp.valueOf(from.atStartOfDay());
    Timestamp dbTo = Timestamp.valueOf(to.atStartOfDay());

    Map<Product, Integer> productToAmount = orderProductDAO.list().stream()
            .filter(a -> a.getOrder().getDate().before(dbTo))
            .filter(a -> a.getOrder().getDate().after(dbFrom))
            .filter(a -> a.getOrder().isPaid())
            .collect(Collectors.groupingBy(OrderProduct::getProduct,
                    Collectors.summingInt(OrderProduct::getAmount)));

    Map<Product, Integer> result = productToAmount.entrySet().stream()
            .sorted(Entry.comparingByValue())
            .limit(limit)
            .collect(Collectors.toMap(
                    Entry::getKey, Entry::getValue
            ));

    System.out.println(result);

    result.forEach((key, value) -> Hibernate.initialize(key.getCartItem()));
    result.forEach((key, value) -> Hibernate.initialize(key.getOrderProducts()));

    return result.entrySet()
            .stream()
            .collect(Collectors.toMap( a -> dtoConverterService.getProductDTO(a.getKey()), Map.Entry::getValue));
  };

  /**
   * accepts json {from : "", to: "", limit : ""} and find n products, brought most revenue for given period, where n<= limit
   * */
  @Override
  @Transactional
  public Map<ProductDTO, Integer> getMostOrderedProductsForSelectedPeriod(Message m, String json) {
    JsonNode jsonNode = UtilService.parseJsonInput(json);
    if (Objects.isNull(jsonNode)){
      m.getErrors().add("wrong json for product period");
      return null;
    };

    String fromJ = jsonNode.get("from").asText();
    String toJ = jsonNode.get("to").asText();
    Integer limit = jsonNode.get("limit").asInt();

    LocalDate from = LocalDate.parse(fromJ);
    LocalDate to = LocalDate.parse(toJ);

    return  getMostOrderedProductsForSelectedPeriod(to,from,limit);
  }

  private  <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

  @Override
  @Transactional
  public Map<ProductRawDTO, Integer> getRestProd(LocalDate to, LocalDate from, Integer limit){
    Timestamp dbFrom = Timestamp.valueOf(from.atStartOfDay());
    Timestamp dbTo = Timestamp.valueOf(to.atStartOfDay());

    Map<Product, Integer> productToAmount = orderProductDAO.list().stream()
            .filter(a -> a.getOrder().getDate().before(dbTo))
            .filter(a -> a.getOrder().getDate().after(dbFrom))
            .filter(a -> a.getOrder().isPaid())
            .collect(Collectors.groupingBy(OrderProduct::getProduct,
                    Collectors.summingInt(OrderProduct::getAmount)));

    Map<Product, Integer> result = productToAmount.entrySet().stream()
            .sorted(Entry.comparingByValue())
            .limit(limit)
            .collect(Collectors.toMap(
                    Entry::getKey, Entry::getValue
            ));

    System.out.println(result);

    result.forEach((key, value) -> Hibernate.initialize(key.getCartItem()));
    result.forEach((key, value) -> Hibernate.initialize(key.getOrderProducts()));

    return result.entrySet()
            .stream()
            .collect(Collectors.toMap( a -> dtoConverterService.getProductRawDTO(a.getKey()), Map.Entry::getValue));
  };
}
