package jschool.service;

import jschool.dto.ProductDTO;
import jschool.dto.ProductRawDTO;
import jschool.dto.UserDTO;
import jschool.model.Product;
import jschool.model.User;
import jschool.validator.Message;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticsService {

  Integer getRevenueForSelectedPeriod(LocalDate from, LocalDate to);

  @Transactional
  Integer getRevenueForSelectedPeriod(Message m, String json);

  @Transactional
  Map<UserDTO, Integer> getMostValuableClientForSelectedPeriod(LocalDate to, LocalDate from, Integer limit);

  @Transactional
  Map<UserDTO, Integer> getMostValuableClientForSelectedPeriod(Message m, String json);

  @Transactional
  Map<ProductDTO, Integer> getMostOrderedProductsForSelectedPeriod(LocalDate to, LocalDate from, Integer limit);

  @Transactional
  Map<ProductDTO, Integer> getMostOrderedProductsForSelectedPeriod(Message m, String json);

  @Transactional
  Map<ProductRawDTO, Integer> getRestProd(LocalDate to, LocalDate from, Integer limit);
}
