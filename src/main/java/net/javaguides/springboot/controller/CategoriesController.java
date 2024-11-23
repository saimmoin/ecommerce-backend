package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.dtos.AddCategoryDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.dtos.CategoryDropDownDTO;
import net.javaguides.springboot.model.Categories;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.service.CategoriesService;
import net.javaguides.springboot.service.ProductsService;
import net.javaguides.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/categories")
    public List<Categories> getAllCategories() {

        return categoriesService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        return categoriesService.getCategoryById(id);
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody AddCategoryDTO addCategoryDTO) {
        return categoriesService.updateCategory(id, addCategoryDTO);
    }

    @PostMapping("/categories/add")
    public String addCategory(@RequestBody AddCategoryDTO addCategoryDTO) {
        return categoriesService.addCategory(addCategoryDTO);
    }

    @GetMapping("/categories/dropDown")
    public List<CategoryDropDownDTO> getAllCategoriesForDropDown() {
        return categoriesService.getAllCategoriesForDropDown();
    }

}
