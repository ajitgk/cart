package com.ak.cart.service;

import com.ak.cart.model.Item;
import com.ak.cart.model.Order;
import com.ak.cart.repository.ItemRepository;
import com.ak.cart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ak on 01/05/2017.
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Long id) {
        return orderRepository.findOne(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void addItem(Long id, Long itemId, BigDecimal quantity) {

        Order order = orderRepository.findOne(id);
        final Item item = itemRepository.findOne(itemId);

        if (order.getItems().contains(item)) {
            order.getItems().stream()
                    .filter(t -> t.getId() == item.getId())
                    .forEach(t -> t.setQuantity(item.getQuantity().add(t.getQuantity())));
        } else {
            order.getItems().add(item);
            item.setOrder(order);
        }

        orderRepository.save(order);
    }

    public BigDecimal getTotalCost(Long id) {
        Order order = orderRepository.findOne(id);

        if (!order.getItems().isEmpty()) {
            return order.getItems().stream()
                    .map(t -> t.getPrice().multiply(t.getQuantity()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        } else {
            return BigDecimal.ZERO;
        }
    }
}
