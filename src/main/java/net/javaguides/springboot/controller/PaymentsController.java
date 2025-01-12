package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.dtos.AddPaymentDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.dtos.PaymentsListDTO;
import net.javaguides.springboot.dtos.UpdatePaymentDTO;
import net.javaguides.springboot.model.Payments;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.service.PaymentsService;
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

public class PaymentsController {
    @Autowired
    private PaymentsService paymentsService;

    @GetMapping("/payments")
    public List<PaymentsListDTO> getAllPayments() {

        return paymentsService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        return paymentsService.getPaymentById(id);
    }

    @PostMapping("/payments/add")
    public String addPayment(@RequestBody AddPaymentDTO addPaymentDTO) {
        return paymentsService.addPayment(addPaymentDTO);
    }

    @PostMapping("/payments/update")
    public String updatePayment(@RequestBody UpdatePaymentDTO updatePaymentDTO) {
        return paymentsService.updatePayment(updatePaymentDTO);
    }

    @GetMapping("/byOrderId/{orderId}")
    public ResponseEntity<Long> getPaymentIdByOrderId(@PathVariable Integer orderId) {
        Long paymentId = paymentsService.getPaymentIdByOrderId(orderId);
        if (paymentId != null) {
            return ResponseEntity.ok(paymentId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
