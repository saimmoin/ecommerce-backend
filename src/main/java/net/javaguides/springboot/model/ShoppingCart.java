package net.javaguides.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter

@Table(name = "shoppingcart")
public class ShoppingCart {
    @Id
    private long cartId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    private Integer quantity;
    private Date addedAt;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product_name")
    private String productName;

    public ShoppingCart() {
    }

    public ShoppingCart(long cartId, Integer userId, Integer productId, Integer quantity, Date addedAt, BigDecimal price, String productName) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedAt = addedAt;
        this.price = price;
        this.productName = productName;
    }

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private Users userInfo;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Products productInfo;
}
