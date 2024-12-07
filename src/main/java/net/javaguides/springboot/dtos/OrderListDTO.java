package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderListDTO {

    private long orderId;
    private String name;
    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;
}
