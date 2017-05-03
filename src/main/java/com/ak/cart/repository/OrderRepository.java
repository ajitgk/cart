package com.ak.cart.repository;

import com.ak.cart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ak on 01/05/2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
