package org.big.prj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.OrderDTO;
import org.springframework.data.domain.PageRequest;

public interface OrderService {
    void insertOrder(OrderDTO order);
    List<OrderDTO> getOrdersByUserId(@Param("userId") String userId);
    void placeOrder(OrderDTO order);
    OrderDTO getOrderDetails(@Param("orderNo") String orderNo);
    String getLastOrderNo();
    long countOrdersByUserId(@Param("userId") String userId); // @Param 추가
    List<OrderDTO> getAllOrders(@Param("search") String search, @Param("size") int size, @Param("offset") int offset);
    long countOrders(@Param("search") String search);
}