package org.big.prj.service.impl;

import java.util.List;

import org.big.prj.dto.OrderDTO;
import org.big.prj.mapper.OrderMapper;
import org.big.prj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insertOrder(OrderDTO order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(String userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    public void placeOrder(OrderDTO order) {
        orderMapper.placeOrder(order);
    }

    @Override
    public OrderDTO getOrderDetails(String orderNo) {
        return orderMapper.getOrderDetails(orderNo);
    }

    @Override
    public String getLastOrderNo() {
        return orderMapper.getLastOrderNo();
    }

    @Override
    public long countOrdersByUserId(String userId) {
        return orderMapper.countOrdersByUserId(userId);
    }

    @Override
    public List<OrderDTO> getAllOrders(String search, int size, int offset) {
        return orderMapper.getAllOrders(search, size, offset);
    }

    @Override
    public long countOrders(String search) {
        return orderMapper.countOrders(search);
    }
}
 