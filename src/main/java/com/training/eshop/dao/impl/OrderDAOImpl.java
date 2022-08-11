package com.training.eshop.dao.impl;

import com.training.eshop.dao.OrderDAO;
import com.training.eshop.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
}
