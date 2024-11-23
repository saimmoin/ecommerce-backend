package net.javaguides.springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddUserDTO {

    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private Date createdAt;
}
