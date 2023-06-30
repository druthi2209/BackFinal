package com.druthi.emedicinestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Data
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItemsSet;

    public Long getCartId() {
        return cartId;
    }

    public User getUser() {
        return user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public List<CartItem> getCartItemsSet() {
        return cartItemsSet;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setUser(User user) {
        user.setCart(this);
        this.user = user;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCartItemsSet(List<CartItem> cartItemsSet) {
        this.cartItemsSet = cartItemsSet;
    }
}