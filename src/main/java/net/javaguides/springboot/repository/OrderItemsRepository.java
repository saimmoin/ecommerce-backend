package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.OrderItems;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{

    List<OrderItems> findByOrderId(Integer orderId);
}
