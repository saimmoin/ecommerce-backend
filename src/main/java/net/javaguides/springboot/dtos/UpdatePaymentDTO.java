package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePaymentDTO {

    private long paymentId;
    private BigDecimal amountReceived;

}
