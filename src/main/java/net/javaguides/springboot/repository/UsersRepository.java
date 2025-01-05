package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

    Optional<Users> findByEmail(String email);
}
