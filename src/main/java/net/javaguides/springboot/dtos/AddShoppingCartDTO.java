package net.javaguides.springboot.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddShoppingCartDTO {

    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Date addedAt;
}
