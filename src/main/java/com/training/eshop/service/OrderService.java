package com.training.eshop.service;

import com.training.eshop.dto.OrderDto;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public interface OrderService {

    OrderDto getOrderDto(HttpSession session);

    void updateData(HttpSession session, OrderDto order);

    void save(OrderDto orderDto, String login, BigDecimal totalPrice);

    void addGoodToOrder(String option, OrderDto orderDto);

    void deleteGoodFromOrder(String option, OrderDto orderDto);

    String printChosenGoods(OrderDto orderDto);

    String printOrder(OrderDto orderDto);

    BigDecimal getTotalPrice(OrderDto orderDto);

    String orderResult(BigDecimal totalPrice);
}

