package com.druthi.emedicinestore.controller;

import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("findCartItemById/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public CartItem findCartItemById(@PathVariable Long cartItemId) throws EntityNotFoundException {
        return cartItemService.findCartItemById(cartItemId);
    }

    @GetMapping("findAllCartItems")
//    @PreAuthorize("hasRole('USER')")
    public List<CartItem> findAllCartItems(){
        return cartItemService.findAllCartItems();
    }

    @PostMapping("addToCartItem")
    @PreAuthorize("hasRole('USER')")
    public CartItem addToCartItem(@RequestBody CartItem cartItem) throws EntityNotFoundException {
        return cartItemService.addToCartItem(cartItem);
    }

    @PutMapping("updateCartItemById/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public CartItem updateCartItemById(@RequestBody CartItem cartItem, @PathVariable Long cartItemId) throws EntityNotFoundException {
        return cartItemService.updateCartItemById(cartItem,  cartItemId);
    }

    @DeleteMapping("deleteCartItemById/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public String deleteCartItemById(@PathVariable Long cartItemId) throws EntityNotFoundException {
        return cartItemService.deleteCartItemById(cartItemId);
    }

    public boolean isCartItemPresent(@PathVariable Long cartItemId){
        return cartItemService.isCartItemPresent(cartItemId);
    }
}
