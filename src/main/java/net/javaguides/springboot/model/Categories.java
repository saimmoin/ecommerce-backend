package net.javaguides.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

@Table(name = "Categories")
public class Categories {
    @Id
    private long categoryId;
    private String name;

    public Categories(long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Categories() {
    }
}
