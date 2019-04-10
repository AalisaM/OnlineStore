package jschool.service.impl;

import jschool.dao.CategoryDAO;
import jschool.dto.CategoryDTO;
import jschool.model.Category;
import jschool.service.CategoryService;
import jschool.service.DTOConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final DTOConverterService dtoConverterService;
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(DTOConverterService dtoConverterService, CategoryDAO categoryDAO) {
        this.dtoConverterService = dtoConverterService;
        this.categoryDAO = categoryDAO;
    }


    @Override
    @Transactional
    public List<CategoryDTO> list() {
        return  dtoConverterService.getCategoryDTOList(this.categoryDAO.listCategory());
    }

    @Override
    @Transactional
    public void add(CategoryDTO p) {
        Category  c = new Category();
        c.setName(p.getName());
        c.setId(p.getId());
        c.setParentId(p.getParentId());
        //products
        this.categoryDAO.add(c);
    }

    @Override
    @Transactional
    public void update(CategoryDTO p) {
        Category  c = this.categoryDAO.findById(p.getId());
        c.setName(p.getName());
        c.setId(p.getId());
        c.setParentId(p.getParentId());
        //products
        this.categoryDAO.update(c);
    }

    @Override
    @Transactional
    public CategoryDTO findById(int id) {
        return dtoConverterService.getCategoryDTO(this.categoryDAO.findById(id));
    }


    @Override
    @Transactional
    public void remove(int id) {
        this.categoryDAO.remove(id);
    }

}
