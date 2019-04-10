package jschool.service;

import jschool.dto.CategoryRawDTO;
import jschool.dto.ProductDTO;
import jschool.model.Category;
import jschool.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    List<Product> list();
    Set<ProductDTO> listDTO();
    void update(ProductDTO p);
    Product findById(int id);
    ProductDTO findByIdFTO(int id);
    void remove(int id);
    void add(ProductDTO p);
    void addWithImage(ProductDTO p, MultipartFile f);
    List<Product> filter(String json) throws IOException;
    Set<ProductDTO> filterDTO(String json) throws IOException;

    List<Category> listCategory();
    List<CategoryRawDTO> listCategoryDTO();


}
