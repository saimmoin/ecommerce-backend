package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.Order;
import net.javaguides.springboot.dtos.AddOrderItemDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.model.OrderItems;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.service.OrderItemsService;
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

public class OrderItemsController {
    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("/orderItems")
    public List<OrderItems> getAllOrderItems() {

        return orderItemsService.getAllOrderItems();
    }

    @GetMapping("/orderItems/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Long id) {
        return orderItemsService.getOrderItemById(id);
    }

    @PostMapping("/orderItems/add")
    public String addOrderItem(@RequestBody AddOrderItemDTO addOrderItemDTO) {
        return orderItemsService.addOrderItem(addOrderItemDTO);
    }

}
