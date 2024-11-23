package net.javaguides.springboot.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AddOrderDTO {

    private Integer userId;
    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;
}
