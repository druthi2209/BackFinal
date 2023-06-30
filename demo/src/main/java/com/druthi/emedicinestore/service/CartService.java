package com.druthi.emedicinestore.service;

import com.druthi.emedicinestore.entity.Cart;
import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Cart getCartByUserId(Long userId) throws EntityNotFoundException;

    Cart addToCart(List<CartItem> cartItems, Long userId) throws EntityNotFoundException;

    Cart updateCartToAddItem(CartItem cartItem, Long cartId);

    String updateCartToDeleteItem(CartItem cartItem, Long cartId) throws EntityNotFoundException;

    String deleteCart(Long cartId) throws EntityNotFoundException;
}
