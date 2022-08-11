package com.training.eshop.dao.impl;

import com.training.eshop.dao.GoodDAO;
import com.training.eshop.exception.ProductNotFoundException;
import com.training.eshop.model.Good;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodDAOImpl implements GoodDAO {

    private static final String QUERY_SELECT_FROM_GOOD = "from Good";

    private final SessionFactory sessionFactory;

    public GoodDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Good> getAll() {
        return sessionFactory.getCurrentSession().createQuery(QUERY_SELECT_FROM_GOOD).list();
    }

    @Override
    public Good getByTitleAndPrice(String title, String price) {
        return getAll().stream()
                .filter(good -> title.equals(good.getTitle())
                        && price.equals(String.valueOf(good.getPrice())))
                .findAny()
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with title %s and price %s not found", title, price)));
    }
}
