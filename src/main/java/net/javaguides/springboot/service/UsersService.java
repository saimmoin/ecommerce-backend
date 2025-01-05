package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers(){

        return usersRepository.findAll();
    }

    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return ResponseEntity.ok(users);
    }

    public String addUser(@RequestBody AddUserDTO addUserDTO) {
        Users users = new Users();
        users.setName(addUserDTO.getName());
        users.setEmail(addUserDTO.getEmail());
        users.setRole(addUserDTO.getRole());
        users.setCreatedAt(addUserDTO.getCreatedAt());
        users.setPasswordHash(addUserDTO.getPasswordHash());

        usersRepository.save(users);
        return "User saved successfully!";
    }

    public String updateUser(@PathVariable Long id, @RequestBody AddUserDTO addUserDTO){
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        users.setName(addUserDTO.getName());
        users.setEmail(addUserDTO.getEmail());
        users.setRole(addUserDTO.getRole());
        users.setPasswordHash(addUserDTO.getPasswordHash());
        users.setCreatedAt(addUserDTO.getCreatedAt());

        usersRepository.save(users);
        return "User updated successfully!";
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists!";
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setCreatedAt(new Date());
        usersRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(Users user) {
        Optional<Users> existingUser = usersRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPasswordHash(), existingUser.get().getPasswordHash())) {
            return "Login successful!";
        }
        return "Invalid email or password";
    }

}
