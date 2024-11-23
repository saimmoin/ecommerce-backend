package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.dtos.AddShoppingCartDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.service.ShoppingCartService;
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

public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingCarts")
    public List<ShoppingCart> getAllShoppingCarts() {

        return shoppingCartService.getAllShoppingCart();
    }

    @GetMapping("/shoppingCarts/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartById(id);
    }

    @PutMapping("/shoppingCarts/{id}")
    public String updateShoppingCart(@PathVariable Long id, @RequestBody AddShoppingCartDTO addShoppingCartDTO) {
        return shoppingCartService.updateShoppingCart(id, addShoppingCartDTO);
    }


    @PostMapping("/shoppingCarts/add")
    public String addShoppingCart(@RequestBody AddShoppingCartDTO addShoppingCartDTO) {
        return shoppingCartService.addShoppingCart(addShoppingCartDTO);
    }

}