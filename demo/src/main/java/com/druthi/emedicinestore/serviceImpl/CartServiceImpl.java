package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.Cart;
import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.CartItemRepository;
import com.druthi.emedicinestore.repository.CartRepository;
import com.druthi.emedicinestore.service.CartItemService;
import com.druthi.emedicinestore.service.CartService;
import com.druthi.emedicinestore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart getCartByUserId(Long userId) throws EntityNotFoundException {
        User user = null;
        try {
            user = userService.getUserById(userId);
        }
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User with specified Id not found");
        }
        Cart cart = cartRepository.findByUserUserId(userId).get();
        log.info(cart.getCartItemsSet().toString());
        if(cart==null){
            throw new EntityNotFoundException("No cart found for specified user!");
        }
        return cart;
    }

    @Override
    public Cart addToCart(List<CartItem> cartItems, Long userId) throws EntityNotFoundException {
        User user = null;
        try {
            user = userService.getUserById(userId);
        }
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User with specified Id not found");
        }
        Cart cart = new Cart();
        //List<CartItem> cartItems1 = new ArrayList<>();
        for(CartItem cartItem : cartItems){
            cartItem.setPresent(false);
//            CartItem newCartItem = cartItemService.findCartItemById(cartItem.getCartItemId());
//            cartItems1.add(newCartItem);
        }
        cart.setCartItemsSet(cartItems);
        cart.setTotalPrice(calculateTotalAmount(cartItems));
        cart.setUser(user);
        Cart cart1 = cartRepository.save(cart);
        if(cart1 == null){
            throw new EntityNotFoundException("Cart is not added! Try again later!");
        }
        return cart1;
    }

    @Override
    public Cart updateCartToAddItem(CartItem cartItem, Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.getCartItemsSet().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());
        return cartRepository.save(cart);
    }

    @Override
    public String updateCartToDeleteItem(CartItem cartItem, Long cartId) throws EntityNotFoundException{
        Cart cart = cartRepository.findById(cartId).get();
        int count = 0;
        if(cart == null) {
            throw new EntityNotFoundException("No cart found for specified user!");
        }
        for(CartItem item : cart.getCartItemsSet()){
            if(item.getCartItemId() == cartItem.getCartItemId()){
                count++;
                break;
            }
        }
        if(count == 0){
            throw new EntityNotFoundException("No cart item of specified id found");
        }
        cart.getCartItemsSet().remove(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() - cartItem.getPrice());
        cartItemRepository.delete(cartItem);
        return "Cart Item deleted successfully!";
    }

    private Double calculateTotalAmount(List<CartItem> cartItems) {
        Double total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getPrice());
        }
        return total;
    }

    @Override
    public String deleteCart(Long cartId) throws EntityNotFoundException {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(!cart.isPresent()){
            throw new EntityNotFoundException("Cart cannot be found for specified user");
        }
        cartRepository.delete(cart.get());
        return "Cart deleted successfully!";
    }
}
