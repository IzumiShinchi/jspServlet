package org.big.prj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.OrderDTO;

@Mapper
public interface OrderMapper {
	List<OrderDTO> getAllOrders(@Param("search") String search, @Param("size") int size, @Param("offset") int offset);

	long countOrders(@Param("search") String search);

	List<OrderDTO> getOrdersByUserId(@Param("userId") String userId, @Param("orderNo") String orderNo,
			@Param("kind") String kind, @Param("color") String color, @Param("startRow") int startRow,
			@Param("endRow") int endRow, @Param("pageSize") int pageSize);

	void insertOrder(OrderDTO order);

	List<OrderDTO> getOrdersByUserId(@Param("userId") String userId);

	void placeOrder(OrderDTO order);

	OrderDTO getOrderDetails(@Param("orderNo") String orderNo);

	String getLastOrderNo();

	long countOrdersByUserId(@Param("userId") String userId);
}
