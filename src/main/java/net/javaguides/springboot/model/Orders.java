package net.javaguides.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter

@Table(name = "orders")

public class Orders {
    @Id
    private long orderId;

    @Column(name = "user_id")
    private Integer userId;

    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;

    public Orders() {
    }

    public Orders(long orderId, Integer userId, BigDecimal totalAmount, String status, Date createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private Users userInfo;
}
