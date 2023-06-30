package com.druthi.emedicinestore.repository;

import com.druthi.emedicinestore.entity.Cart;
import com.druthi.emedicinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserUserId(Long userId);
}
