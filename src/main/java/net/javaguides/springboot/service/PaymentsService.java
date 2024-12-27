package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.*;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.OrderItems;
import net.javaguides.springboot.model.Payments;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentsService {
    private final PaymentsRepository paymentsRepository;
    private final OrderItemsRepository orderItemsRepository;

    private final ProductsRepository productsRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository, OrderItemsRepository orderItemsRepository,ProductsRepository productsRepository) {
        this.paymentsRepository = paymentsRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.productsRepository = productsRepository;
    }

    public List<PaymentsListDTO> getAllPayments(){

        List<Payments> payments = paymentsRepository.findAll();

        // Map the orders to OrderListDTO and return the list
        return payments.stream().map(payment -> {
           PaymentsListDTO dto = new PaymentsListDTO();
            dto.setPaymentId(payment.getPaymentId());
            dto.setOrderId(payment.getOrderId());
            dto.setAmount(payment.getAmount());
            dto.setPaymentMethod(payment.getPaymentMethod());
            dto.setStatus(payment.getStatus());
            dto.setPaidAt(payment.getPaidAt());
            return dto;
        }).collect(Collectors.toList());
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

    public String updatePayment(UpdatePaymentDTO updatePaymentDTO) {
        Optional<Payments> payments = paymentsRepository.findById(updatePaymentDTO.getPaymentId());

        Payments payment = payments.get();

        if(payment.getAmount().equals(updatePaymentDTO.getAmountReceived())){
            payment.setStatus("completed");

            List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(payment.getOrderId());

            for(OrderItems orderItems : orderItemsList){

                Optional<Products> products = productsRepository.findById(orderItems.getProductId().longValue());
                Products product = products.get();

                Integer oldStock = product.getStock();


                product.setStock(oldStock - orderItems.getQuantity());

                productsRepository.save(product);

            }


            return "Payment Successful";
        }

        else{
            payment.setStatus("failed");
            paymentsRepository.save(payment);
            return "Payment Failed";
        }

    }

}
