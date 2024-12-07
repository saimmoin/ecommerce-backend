package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequest {


    private Integer userId;
    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;
    private List<OrderItemRequest> orderItems;
}
