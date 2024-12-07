package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequest {

    private Integer productId;
    private Integer quantity;
    private BigDecimal price;

}
