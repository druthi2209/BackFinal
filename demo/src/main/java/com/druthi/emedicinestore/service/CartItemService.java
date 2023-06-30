package com.druthi.emedicinestore.service;

import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    CartItem findCartItemById(Long cartItemId) throws EntityNotFoundException;

    List<CartItem> findAllCartItems();

    CartItem addToCartItem(CartItem cartItem) throws EntityNotFoundException;

    CartItem updateCartItemById(CartItem cartItem, Long cartItemId) throws EntityNotFoundException;

    String deleteCartItemById(Long cartItemId) throws EntityNotFoundException;

    boolean isCartItemPresent(Long cartItemId);
}
