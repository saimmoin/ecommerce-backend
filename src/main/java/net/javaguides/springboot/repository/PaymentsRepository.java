package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Payments;

import java.util.Optional;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long>{

    Optional<Payments> findByOrderId(Integer orderId);
}
