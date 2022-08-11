package com.training.eshop.controller;

import com.training.eshop.dto.OrderDto;
import com.training.eshop.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String saveOrder(Model model, HttpSession session, Authentication authentication, OrderDto orderDto) {
        String login = authentication.getName();

        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        String orderList = (String) session.getAttribute("order");
        String orderResult = orderService.orderResult(totalPrice);

        model.addAttribute("login", login);
        model.addAttribute("orderList", orderList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderResult", orderResult);

        orderService.save(orderDto, login, totalPrice);

        orderService.updateData(session, orderDto);

        return "order";
    }
}
