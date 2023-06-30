package com.druthi.emedicinestore.controller;

import com.druthi.emedicinestore.entity.Cart;
import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.service.CartItemService;
import com.druthi.emedicinestore.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    private CartItemService cartItemService;

    @GetMapping("getCartByUserId/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Cart getCartByUserId(@PathVariable Long userId) throws EntityNotFoundException {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("addToCart/{userId}")
    //@PreAuthorize("hasRole('USER')")
    public Cart addToCart(@RequestBody List<CartItem> cartItems, @PathVariable Long userId) throws EntityNotFoundException {
        return cartService.addToCart(cartItems, userId);
    }

    @PutMapping("updateCartToAddItem/{cartId}")
    @PreAuthorize("hasRole('USER')")
    public Cart updateCartToAddItem(@RequestBody CartItem cartItem, @PathVariable Long cartId) {
        return cartService.updateCartToAddItem(cartItem, cartId);
    }

    @PutMapping("updateCartToDeleteItem/{cartId}")
    @PreAuthorize("hasRole('USER')")
    public String updateCartToDeleteItem(@RequestBody CartItem cartItem, @PathVariable Long cartId) throws EntityNotFoundException {
        return cartService.updateCartToDeleteItem(cartItem, cartId);
    }

    @DeleteMapping("deleteCart/{cartId}")
    @PreAuthorize("hasRole('USER')")
    public String deleteCart(@PathVariable Long cartId) throws EntityNotFoundException {
        return cartService.deleteCart(cartId);
    }

}
