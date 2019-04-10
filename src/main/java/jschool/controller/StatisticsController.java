package jschool.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jschool.dto.ProductDTO;
import jschool.dto.ProductRawDTO;
import jschool.dto.ShippingDTO;
import jschool.dto.UserDTO;
import jschool.service.DTOConverterService;
import jschool.service.StatisticsService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/statistics")
public class StatisticsController {

  private final StatisticsService statisticsService;

  @Autowired
  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView basicStatistics(ModelMap model) {
    model.addAttribute("revenueWeek", this.getRevenueForLastWeek());
    model.addAttribute("revenueMonth", this.getRevenueForLastMonth());
    model.addAttribute("userTop", this.statisticsService.getMostValuableClientForSelectedPeriod(LocalDate.now(),LocalDate.now().minusMonths(1), 10));
    model.addAttribute("productTop",  this.statisticsService.getMostOrderedProductsForSelectedPeriod(LocalDate.now(), LocalDate.now().minusMonths(1), 10));
    return new ModelAndView("statistics",model);
  }

  @RequestMapping(value = "/revenueForSelectedPeriod", method = RequestMethod.POST)
  public int getRevenueForSelectedPeriod(@RequestBody String json){
    Message m  = new Message();
    return statisticsService.getRevenueForSelectedPeriod(m, json);
  }

  @RequestMapping(value = "/revenueForLastMonth",method = RequestMethod.GET)
  public int getRevenueForLastMonth() {
    return statisticsService.getRevenueForSelectedPeriod(LocalDate.now().minusMonths(1), LocalDate.now());
  }

  @RequestMapping(value = "/revenueForLastWeek", method = RequestMethod.GET)
  public int getRevenueForLastWeek(){
    return statisticsService.getRevenueForSelectedPeriod(LocalDate.now().minusWeeks(1), LocalDate.now());
  }

  @RequestMapping(value = "/userTopForSelectedPeriod", method = RequestMethod.POST)
  public Map<UserDTO, Integer> getTopUsersForSelectedPeriod(@RequestBody String json){
    Message m = new Message();
    Map<UserDTO, Integer> result = statisticsService.getMostValuableClientForSelectedPeriod(m, json);
    return result;
  }

  @RequestMapping(value = "/productTopForSelectedPeriod", method = RequestMethod.POST)
  public Map<ProductDTO, Integer> getTopProductsForSelectedPeriod(@RequestBody String json){
    Message m = new Message();
    Map<ProductDTO, Integer> result = statisticsService.getMostOrderedProductsForSelectedPeriod(m, json);
    return result;
  }

  @RequestMapping(value = "/restProduct", method = RequestMethod.GET)
  public Set<ProductRawDTO> getRestProds(){
    return statisticsService.getRestProd(LocalDate.now(), LocalDate.now().minusMonths(1),10).keySet();
  }

}
