package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PaymentsListDTO {

    private long paymentId;
    private long orderId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private Date paidAt;

}
