package com.training.eshop.dto;

import com.training.eshop.model.Good;
import com.training.eshop.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private BigDecimal totalPrice;

    private User user;

    private List<Good> goods;

    public OrderDto() {
        this.goods = new ArrayList();
    }
}
