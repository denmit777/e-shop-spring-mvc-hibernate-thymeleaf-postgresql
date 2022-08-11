package com.training.eshop.converter.impl;

import com.training.eshop.converter.OrderConverter;
import com.training.eshop.dto.OrderDto;
import com.training.eshop.model.Order;
import com.training.eshop.model.User;
import com.training.eshop.service.UserService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderConverterImpl implements OrderConverter {

    private final UserService userService;

    public OrderConverterImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Order fromOrderDto(OrderDto orderDto, String login, BigDecimal totalPrice) {
        User user = userService.getByLogin(login);

        orderDto.setUser(user);
        orderDto.setTotalPrice(totalPrice);

        Order order = new Order();

        order.setUser(orderDto.getUser());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setGoods(orderDto.getGoods());

        return order;
    }
}
