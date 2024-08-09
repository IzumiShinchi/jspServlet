package org.big.prj.service.impl;

import org.big.prj.dto.CartDTO;
import org.big.prj.mapper.CartMapper;
import org.big.prj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addToCart(CartDTO cart) {
        cartMapper.insertCart(cart);
    }

    @Override
    public List<CartDTO> getCartItems(String userId) {
        return cartMapper.getCartItems(userId);
    }

    @Override
    public void removeFromCart(String cartNo) {
        cartMapper.deleteCartByCartNo(cartNo);
    }
    
    @Override
    public int calculateTotalPrice(List<CartDTO> cartItems) {
        int totalPrice = 0;
        for (CartDTO cartItem : cartItems) {
            totalPrice += Integer.parseInt(cartItem.getTotalPrice().replace(",", "").replace("원", ""));
        }
        return totalPrice;
    }

    @Override
    public CartDTO getCartItem(String cartNo) {
        return cartMapper.getCartItem(cartNo);
    }
    @Override
    public void updateCart(CartDTO cart) {
        cartMapper.updateCart(cart);
    }
    
    @Override
    public void updateCartStatus(String userId, String status) {
        cartMapper.updateCartStatus(userId, status);
    }
}
