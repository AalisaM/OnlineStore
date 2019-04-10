package jschool.dao;

import jschool.model.Product;

import java.util.List;

public interface ProductDAO {
     List<Product> list();

     Product findById(int id);
     void update(Product p);
     int add(Product p);
     void remove(int id);

     void addImageToProduct(int id, String file);
     List<Product> getProductsByFilter(int price, int minPlayer, int maxPlayer, int categoryId);
}
