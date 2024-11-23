package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.AddPaymentDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Payments;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.PaymentsRepository;
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
public class PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    public List<Payments> getAllPayments(){

        return paymentsRepository.findAll();
    }

    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        Payments payments = paymentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not exist with id :" + id));
        return ResponseEntity.ok(payments);
    }

    public String addPayment(@RequestBody AddPaymentDTO addPaymentDTO) {
        Payments payments = new Payments();
        payments.setOrderId(addPaymentDTO.getOrderId());
        payments.setAmount(addPaymentDTO.getAmount());
        payments.setPaymentMethod(addPaymentDTO.getPaymentMethod());
        payments.setStatus(addPaymentDTO.getStatus());
        payments.setPaidAt(addPaymentDTO.getPaidAt());

        paymentsRepository.save(payments);
        return "Payment saved successfully!";
    }

}
