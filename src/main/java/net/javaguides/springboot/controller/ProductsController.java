package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.dtos.AddProductDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.dtos.ProductListDTO;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
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

public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public List<Products> getAllProducts() {

        return productsService.getAllProducts();
    }

    @GetMapping("/productsList")
    public List<ProductListDTO> getProductList() {

        return productsService.getAllProductsList();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        return productsService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody AddProductDTO addProductDTO) {
        return productsService.updateProduct(id, addProductDTO);
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestBody AddProductDTO addProductDTO) {
        return productsService.addProduct(addProductDTO);
    }

}
