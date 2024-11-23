package net.javaguides.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter

@Table(name = "products")
public class Products {
    @Id
    private long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    @Column(name = "category_id")
    private Integer categoryId;
    private String imageUrl;
    private Date createdAt;

    public Products() {
    }

    public Products(long productId, String name, String description, BigDecimal price, Integer stock, Integer categoryId, String imageUrl, Date createdAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name="category_id", insertable = false, updatable = false)
    private Categories categoryInfo;
}
