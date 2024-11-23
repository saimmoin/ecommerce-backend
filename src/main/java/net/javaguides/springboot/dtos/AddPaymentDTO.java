package net.javaguides.springboot.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AddPaymentDTO {

    private Integer orderId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private Date paidAt;
}
