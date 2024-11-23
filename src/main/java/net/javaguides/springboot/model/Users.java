package net.javaguides.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter

@Table(name = "users")
public class Users {
    @Id
    private long userId;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private Date createdAt;

    public Users(long userId, String name, String email, String passwordHash, String role, Date createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Users() {
    }
}
