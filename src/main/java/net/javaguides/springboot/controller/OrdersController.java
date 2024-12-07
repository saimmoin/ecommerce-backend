package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.Order;
import net.javaguides.springboot.dtos.AddOrderDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.dtos.OrderListDTO;
import net.javaguides.springboot.dtos.OrderRequest;
import net.javaguides.springboot.model.Orders;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.service.OrdersService;
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

public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/orders")
    public List<OrderListDTO> getAllOrders() {

        return ordersService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }

    @PostMapping("/orders/add")
    public String addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        return ordersService.addOrder(addOrderDTO);
    }


    @PostMapping("/orders/save")
    public ResponseEntity<String> saveOrderWithOrderItems(@RequestBody OrderRequest orderRequest)
    {
        return ordersService.saveOrderWithOrderItems(orderRequest);
    }

}
