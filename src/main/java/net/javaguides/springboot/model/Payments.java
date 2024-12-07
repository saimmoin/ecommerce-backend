package net.javaguides.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter

@Table(name = "payments")
public class Payments {

    @Id
    private long paymentId;

    @Column(name = "order_id")
    private Integer orderId;

    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private Date paidAt;

    public Payments() {
    }

    public Payments(long paymentId, Integer orderId, BigDecimal amount, String paymentMethod, String status, Date paidAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paidAt = paidAt;
    }

//    @ManyToOne
//    @JoinColumn(name="order_id", insertable = false, updatable = false)
//    private Orders orderInfo;


}
