package net.javaguides.springboot.service;

import jakarta.persistence.criteria.Order;
import net.javaguides.springboot.dtos.AddOrderDTO;
import net.javaguides.springboot.dtos.OrderListDTO;
import net.javaguides.springboot.dtos.OrderRequest;
import net.javaguides.springboot.dtos.UpdateOrderDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.OrderItems;
import net.javaguides.springboot.model.Orders;
import net.javaguides.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public List<OrderListDTO> getAllOrders(){
        // Retrieve all orders from the repository
        List<Orders> orders = ordersRepository.findAll();

        // Map the orders to OrderListDTO and return the list
        return orders.stream().map(order -> {
            OrderListDTO dto = new OrderListDTO();
            dto.setOrderId(order.getOrderId());
            dto.setName(order.getUserInfo().getName());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setStatus(order.getStatus());
            dto.setCreatedAt(order.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
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

    public ResponseEntity<String> saveOrderWithOrderItems(OrderRequest orderRequest) {
        // Map OrderRequest to Orders entity
        Orders order = new Orders();
        order.setUserId(orderRequest.getUserId());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setStatus(orderRequest.getStatus());
        order.setCreatedAt(orderRequest.getCreatedAt());

        // Save order to get its persistence
        Orders savedOrder = ordersRepository.save(order);

        // Map and save order items
        List<OrderItems> orderItemsList = orderRequest.getOrderItems().stream().map(itemRequest -> {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrderId((int) savedOrder.getOrderId()); // Link with the order
            orderItem.setProductId(itemRequest.getProductId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        orderItemsRepository.saveAll(orderItemsList);

        return ResponseEntity.ok("Order and Order Items added successfully!");
    }


    public String updateOrder(UpdateOrderDTO updateOrderDTO) {
        Optional<Orders> orders = ordersRepository.findById(updateOrderDTO.getOrderId());
        if (orders.isPresent()) {
            Orders order = orders.get();

            order.setStatus(updateOrderDTO.getStatus());
            order.setRemarks(updateOrderDTO.getRemarks());

            ordersRepository.save(order);
            return "Order updated successfully!";
        }
        else {
            return "Order with Id Not found";
        }
    }

}
