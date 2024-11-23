package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

}
