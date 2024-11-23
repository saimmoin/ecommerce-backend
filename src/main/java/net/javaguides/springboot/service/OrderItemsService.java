package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.AddOrderItemDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.OrderItems;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.OrderItemsRepository;
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
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public List<OrderItems> getAllOrderItems(){

        return orderItemsRepository.findAll();
    }

    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Long id) {
        OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not exist with id :" + id));
        return ResponseEntity.ok(orderItems);
    }

    public String addOrderItem(@RequestBody AddOrderItemDTO addOrderItemDTO) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(addOrderItemDTO.getOrderId());
        orderItems.setProductId(addOrderItemDTO.getProductId());
        orderItems.setQuantity(addOrderItemDTO.getQuantity());
        orderItems.setPrice(addOrderItemDTO.getPrice());

        orderItemsRepository.save(orderItems);
        return "Order Item saved successfully!";
    }

}
