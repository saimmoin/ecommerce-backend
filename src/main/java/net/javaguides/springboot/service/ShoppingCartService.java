package net.javaguides.springboot.service;

import net.javaguides.springboot.dtos.AddShoppingCartDTO;
import net.javaguides.springboot.dtos.AddUserDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.ShoppingCart;
import net.javaguides.springboot.model.Users;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.ShoppingCartRepository;
import net.javaguides.springboot.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getAllShoppingCart(){

        return shoppingCartRepository.findAll();
    }

    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not exist with id :" + id));
        return ResponseEntity.ok(shoppingCart);
    }

    public String addShoppingCart(@RequestBody AddShoppingCartDTO addShoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setAddedAt(addShoppingCartDTO.getAddedAt());
        shoppingCart.setQuantity(addShoppingCartDTO.getQuantity());
        shoppingCart.setUserId(addShoppingCartDTO.getUserId());
        shoppingCart.setProductId(addShoppingCartDTO.getProductId());
        shoppingCart.setPrice(addShoppingCartDTO.getPrice());
        shoppingCart.setProductName(addShoppingCartDTO.getProductName());

        shoppingCartRepository.save(shoppingCart);
        return "Shopping Cart saved successfully!";
    }

    public String updateShoppingCart(@PathVariable Long id, @RequestBody AddShoppingCartDTO addShoppingCartDTO){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not exist with id :" + id));

        shoppingCart.setProductId(addShoppingCartDTO.getProductId());
        shoppingCart.setAddedAt(addShoppingCartDTO.getAddedAt());
        shoppingCart.setQuantity(addShoppingCartDTO.getQuantity());


        shoppingCartRepository.save(shoppingCart);
        return "Shopping cart updated successfully!";
    }

    public String deleteShoppingCart(Long id){
        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not exist with id :" + id));

            shoppingCartRepository.delete(shoppingCart);
            return "Item removed successfully!";
        } catch (Exception e) {
            return "Unable remove item";
        }
    }
}
