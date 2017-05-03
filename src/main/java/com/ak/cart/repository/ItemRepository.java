package com.ak.cart.repository;

import com.ak.cart.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ak on 01/05/2017.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
