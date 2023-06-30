package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.CartItem;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.CartItemRepository;
import com.druthi.emedicinestore.service.CartItemService;
import com.druthi.emedicinestore.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MedicineService medicineService;
    @Override
    public CartItem findCartItemById(Long cartItemId) throws EntityNotFoundException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(!cartItem.isPresent()){
            log.error("Cart Item with specified Id not found!");
            throw new EntityNotFoundException("Cart Item with specified Id not found!");
        }
        log.info("Cart Item with specified Id found!");
        return cartItem.get();
    }

    @Override
    public List<CartItem> findAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem addToCartItem(CartItem cartItem) throws EntityNotFoundException {
        try{
            medicineService.findMedicineById(cartItem.getMedicine().getMedicineId());
            cartItem.setPrice(cartItem.getMedicine().getPrice() * cartItem.getQuantity());
            return cartItemRepository.save(cartItem);
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Medicine Not found to add to this cart item");
        }
    }

    @Override
    public CartItem updateCartItemById(CartItem cartItem, Long cartItemId) throws EntityNotFoundException {
        CartItem cartItem1 = cartItemRepository.findById(cartItemId).get();
        if(cartItem1 == null){
            throw new EntityNotFoundException("Cart Item not found with specified Id");
        }
        try{
            medicineService.findMedicineById(cartItem.getMedicine().getMedicineId());
            cartItem.setPrice(cartItem.getMedicine().getPrice() * cartItem.getQuantity());
            return cartItemRepository.save(cartItem);
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Medicine Not found to update to this cart item");
        }
    }

    @Override
    public String deleteCartItemById(Long cartItemId) throws EntityNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        if(cartItem == null){
            log.error("Cart Item with specified Id not found!");
            throw new EntityNotFoundException("Cart Item with specified Id not found!");
        }
        log.info("Cart Item with specified Id found!");
        cartItemRepository.deleteById(cartItemId);
        return "Cart Item with specified Id deleted!";
    }

    @Override
    public boolean isCartItemPresent(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        return cartItem.isPresent();
    }
}
