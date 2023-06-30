package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.Cart;
import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.EntityCannotBeDeletedException;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.CartRepository;
import com.druthi.emedicinestore.service.CartItemService;
import com.druthi.emedicinestore.service.CartService;
import com.druthi.emedicinestore.service.MedicineService;
import com.druthi.emedicinestore.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartServiceImpl {

    @Autowired
    private CartService cartService;
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineService medicineService;
    @Autowired
    private CartItemService cartItemService;

    User user1 = User.builder()
            .userId(1L)
            .userName("user")
            .phoneNumber("1234567890")
            .password("password")
            .email("email@gmai.com")
            .build();
    Medicine medicine1 = Medicine.builder()
            .medicineId(1L)
            .medicineName("Dolo")
            .url("www.google.com")
            .price(12.0)
            .quantity(10L)
            .expiryDate(LocalDate.now())
            .manufacturingDate(LocalDate.now())
            .build();
    Medicine medicine2 = Medicine.builder()
            .medicineId(1L)
            .medicineName("Savlon")
            .url("www.google.com")
            .price(12.0)
            .quantity(10L)
            .expiryDate(LocalDate.now())
            .manufacturingDate(LocalDate.now())
            .build();

    List<CartItem> cartItem = (List<CartItem>) CartItem.builder()
            .cartItemId(1L)
            .medicine(medicine1)
            .quantity(10)
            .price(12.0)
            .build();

    CartItem cartItem1 = CartItem.builder()
            .cartItemId(2L)
            .medicine(medicine2)
            .quantity(10)
            .price(12.0)
            .build();

    Cart cart = Cart.builder()
            .cartId(1L)
            .totalPrice(12.0)
            .user(user1)
            .cartItemsSet(cartItem)
            .build();

    @Test
    void getCartByUserId() throws EntityNotFoundException{
        Mockito.when(cartRepository.findByUserUserId(1L)).thenReturn(Optional.of(cart));
        Long userId = 1L;
        Cart found = cartService.getCartByUserId(userId);
        assertEquals(found, cart);
    }

    @Test
    void addToCart() throws EntityNotFoundException{
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        Cart cart1 = cartService.addToCart(cartItem, 1L);
        assertEquals(cart1, cart);
    }

    @Test
    void updateCartToAddItem(){
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        Cart cart1 = cartService.updateCartToAddItem(cartItem1, 2L);
        assertEquals(cart1, cart);
    }

    @Test
    void deleteCart() throws EntityNotFoundException{
        Mockito.when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        String returned = cartService.deleteCart(1L);
        assertEquals(returned, "Cart deleted successfully!");
    }

}
