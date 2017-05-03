package com.ak.cart.controller;

import com.ak.cart.model.Order;
import com.ak.cart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ak on 01/05/2017.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order get(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order create() {
        return orderService.save(new Order());
    }

    @RequestMapping(value = "/{id}/item/{itemId}", method = RequestMethod.PUT)
    public void addItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId) {
        orderService.addItem(id, itemId, BigDecimal.ONE);
    }

    @RequestMapping(value = "/{id}/item/{itemId}/{quantity}", method = RequestMethod.PUT)
    public void addItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, @PathVariable("quantity") BigDecimal quantity) {
        orderService.addItem(id, itemId, quantity);
    }

    @RequestMapping(value = "/{id}/totalCost", method = RequestMethod.GET)
    public BigDecimal getTotalCost(@PathVariable("id") Long id) {
        return orderService.getTotalCost(id);
    }
}
