package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.AddProductDTO;
import net.javaguides.springboot.dtos.ProductListDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
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
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> getAllProducts(){

        return productsRepository.findAll();
    }

    public List<ProductListDTO> getAllProductsList() {
        List<Products> products = productsRepository.findAll();
        List<ProductListDTO> productListDTOS = new ArrayList<>();

        for(Products product: products) {
            ProductListDTO productListDTO = new ProductListDTO();
            productListDTO.setProductId(product.getProductId());
            productListDTO.setCategory(product.getCategoryInfo().getName());
            productListDTO.setName(product.getName());
            productListDTO.setDescription(product.getDescription());
            productListDTO.setPrice(product.getPrice());
            productListDTO.setStock(product.getStock());
            productListDTO.setImageUrl(product.getImageUrl());
            productListDTO.setCreatedAt(product.getCreatedAt());
            productListDTOS.add(productListDTO);
        }
        return productListDTOS;
    }

    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
        return ResponseEntity.ok(products);
    }

    public String addProduct(@RequestBody AddProductDTO addProductDTO) {
        Products products = new Products();
        products.setName(addProductDTO.getName());
        products.setPrice(addProductDTO.getPrice());
        products.setDescription(addProductDTO.getDescription());
        products.setImageUrl(addProductDTO.getImageUrl());
        products.setCategoryId(addProductDTO.getCategoryId());
        products.setStock(addProductDTO.getStock());
        products.setCreatedAt(addProductDTO.getCreatedAt());


        productsRepository.save(products);
        return "Products saved successfully!";
    }

    public String updateProduct(@PathVariable Long id, @RequestBody AddProductDTO addProductDTO){
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));

        products.setName(addProductDTO.getName());
        products.setDescription((addProductDTO.getDescription()));
        products.setPrice(addProductDTO.getPrice());
        products.setStock(addProductDTO.getStock());
        products.setCategoryId(addProductDTO.getCategoryId());
        products.setImageUrl(addProductDTO.getImageUrl());
        products.setCreatedAt(addProductDTO.getCreatedAt());

        productsRepository.save(products);
        return "Product updated successfully!";
    }

}
