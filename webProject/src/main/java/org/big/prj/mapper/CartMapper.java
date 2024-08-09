package org.big.prj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.CartDTO;

import java.util.List;

@Mapper
public interface CartMapper {
	public List<CartDTO> getCartItems(@Param("userId") String userId);  
    void insertCart(CartDTO cart);
    void deleteCartByCartNo(@Param("cartNo") String cartNo);
	public List<CartDTO> getCartByUserId(@Param("userId") String userId);
	CartDTO getCartItem(@Param("cartNo") String cartNo);
	void updateCart(CartDTO cart);
	void updateCartStatus(@Param("userId") String userId, @Param("status") String status);
}
