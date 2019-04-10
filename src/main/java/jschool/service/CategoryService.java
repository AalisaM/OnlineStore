package jschool.service;

import jschool.dto.CategoryDTO;
import jschool.model.Category;
import jschool.model.Product;

import java.util.List;
import java.util.Set;

public interface CategoryService {
     List<CategoryDTO> list();

     void add(CategoryDTO p);
     void update(CategoryDTO p);
     CategoryDTO findById(int id);
     void remove(int id);
}
