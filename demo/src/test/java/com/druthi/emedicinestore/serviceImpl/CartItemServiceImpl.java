package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.CartItemRepository;
import com.druthi.emedicinestore.repository.MedicineRepository;
import com.druthi.emedicinestore.service.CartItemService;
import com.druthi.emedicinestore.service.MedicineService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartItemServiceImpl {

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartItemRepository cartItemRepository;

    @Autowired
    private MedicineService medicineService;

    Medicine medicine1 = Medicine.builder()
            .medicineId(1L)
            .medicineName("Dolo")
            .url("www.google.com")
            .price(12.0)
            .quantity(10L)
            .expiryDate(LocalDate.now())
            .manufacturingDate(LocalDate.now())
            .build();

    CartItem cartItem = CartItem.builder()
            .cartItemId(1L)
            .medicine(medicine1)
            .quantity(10)
            .price(12.0)
            .build();
    List<CartItem> cartItemList = List.of(cartItem);

    @Test
    void findCartItemById() throws EntityNotFoundException{
        Mockito.when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));
        Long cartItemId = 1L;
        CartItem found = cartItemService.findCartItemById(cartItemId);
        assertEquals(found, cartItem);
    }

    @Test
    void findAllCartItems(){
        Mockito.when(cartItemRepository.findAll()).thenReturn(cartItemList);
        List<CartItem>  returnedList = cartItemService.findAllCartItems();
        assertEquals(returnedList, cartItemList);
    }

    @Test
    void addToCartItem() throws EntityNotFoundException{
        Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
        CartItem cartItem1 = cartItemService.addToCartItem(cartItem);
        assertEquals(cartItem1, cartItem);
    }

    @Test
    void updateCartItemById() throws EntityNotFoundException{
        Mockito.when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));
        Mockito.when(medicineService.findMedicineById(1L)).thenReturn(medicine1);
        Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
        CartItem cartItem1 = cartItemService.updateCartItemById(cartItem,1L);
        assertEquals(cartItem1, cartItem);
    }

    @Test
    void deleteCartItemById() throws EntityNotFoundException{
        Mockito.when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));
        String returned = cartItemService.deleteCartItemById(1L);
        assertEquals(returned,"Cart Item with specified Id deleted!");
    }


}
