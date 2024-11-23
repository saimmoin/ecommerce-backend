package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody AddUserDTO addUserDTO) {
        return usersService.updateUser(id, addUserDTO);
    }

    @PostMapping("/users/add")
    public String addUser(@RequestBody AddUserDTO addUserDTO) {
        return usersService.addUser(addUserDTO);
    }

}
