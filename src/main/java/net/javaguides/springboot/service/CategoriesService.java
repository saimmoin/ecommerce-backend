package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.AddCategoryDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.dtos.CategoryDropDownDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Categories;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.CategoriesRepository;
import net.javaguides.springboot.repository.ProductsRepository;
import net.javaguides.springboot.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories(){

        return categoriesRepository.findAll();
    }

    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
        return ResponseEntity.ok(categories);
    }

    public String addCategory(@RequestBody AddCategoryDTO addCategoryDTO) {
        Categories categories = new Categories();
        categories.setName(addCategoryDTO.getName());

        categoriesRepository.save(categories);
        return "Category saved successfully!";
    }

    public String updateCategory(@PathVariable Long id, @RequestBody AddCategoryDTO addCategoryDTO){
        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));

        categories.setName(addCategoryDTO.getName());

        categoriesRepository.save(categories);
        return "Category updated successfully!";
    }

    public List<CategoryDropDownDTO> getAllCategoriesForDropDown() {
        List<Categories> categories = categoriesRepository.findAll();
        List<CategoryDropDownDTO> categoryDropDownDTOS = new ArrayList<>();
        for(Categories categories1 : categories) {
            CategoryDropDownDTO categoryDropDownDTO = new CategoryDropDownDTO();
            categoryDropDownDTO.setCategoryId(categories1.getCategoryId());
            categoryDropDownDTO.setName(categories1.getName());
            categoryDropDownDTOS.add(categoryDropDownDTO);
        }
        return categoryDropDownDTOS;
    }

}
