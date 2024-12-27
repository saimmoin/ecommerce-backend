package net.javaguides.springboot.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter

@Table(name = "orderitems")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    private Integer quantity;
    private BigDecimal price;

    public OrderItems() {
    }

    public OrderItems(long orderItemId, Integer orderId, Integer productId, Integer quantity, BigDecimal price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name="order_id", insertable = false, updatable = false)
    @JsonBackReference
    private Orders orderInfo;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Products productInfo;
}
