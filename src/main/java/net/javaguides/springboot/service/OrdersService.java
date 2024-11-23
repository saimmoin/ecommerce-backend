package net.javaguides.springboot.service;

import jakarta.persistence.criteria.Order;
import net.javaguides.springboot.dtos.AddOrderDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Orders;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.OrdersRepository;
import net.javaguides.springboot.repository.ProductsRepository;
import net.javaguides.springboot.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public List<Orders> getAllOrders(){

        return ordersRepository.findAll();
    }

    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));
        return ResponseEntity.ok(orders);
    }

    public String addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        Orders orders = new Orders();
        orders.setUserId(addOrderDTO.getUserId());
        orders.setTotalAmount(addOrderDTO.getTotalAmount());
        orders.setStatus(addOrderDTO.getStatus());
        orders.setCreatedAt(addOrderDTO.getCreatedAt());

        ordersRepository.save(orders);
        return "Order saved successfully!";
    }

}
