package org.big.prj.service;

import org.big.prj.dto.CartDTO;

import java.util.List;

public interface CartService {
	void addToCart(CartDTO cart);
	 List<CartDTO> getCartItems(String userId);
	 CartDTO getCartItem(String cartNo);
    void removeFromCart(String cartNo);
    int calculateTotalPrice(List<CartDTO> cartItems);
    void updateCart(CartDTO cart);
    void updateCartStatus(String userId, String status); 
}

