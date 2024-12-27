package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDTO {

    private long orderId;
    private String status;
    private String remarks;

}
