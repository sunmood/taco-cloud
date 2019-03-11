package com.example.demo.repository;

import com.example.demo.domain.Order;

/**
 * Created by sunmood on 2019/3/7.
 */
public interface OrderRepository {

    Order save(Order order);

}
