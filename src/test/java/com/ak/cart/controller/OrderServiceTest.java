package com.ak.cart.controller;

import com.ak.cart.model.Item;
import com.ak.cart.model.Order;
import com.ak.cart.repository.ItemRepository;
import com.ak.cart.repository.OrderRepository;
import com.ak.cart.service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by ak on 01/05/2017.
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrderService.class)
//@EnableAutoConfiguration
//@WebMvcTest(OrderController.class)
public class OrderServiceTest {

    @Autowired
    private OrderService service;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ItemRepository itemRepository;

    private Order order;
    private Item item;

    @Before
    public void setUp() {
        order = new Order();
        order.setId(1L);

        item = new Item();
        item.setId(1L);
        item.setPrice(new BigDecimal("5.99"));
        item.setQuantity(BigDecimal.ONE);

        Mockito.when(orderRepository.findOne(order.getId())).thenReturn(order);
        Mockito.when(orderRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(order)));
        Mockito.when(itemRepository.findOne(item.getId())).thenReturn(item);
    }

    @Test
    public void testCreate() {

        service.addItem(order.getId(), item.getId(), BigDecimal.ONE);
        Assert.assertEquals(1, service.getAll().size());

    }

    @Test
    @Ignore
    public void testCalculateTotalCost() {
        service.addItem(order.getId(), item.getId(), BigDecimal.ONE);
        service.addItem(order.getId(), item.getId(), BigDecimal.ONE);

        Assert.assertEquals(1, service.getAll().size());

        Assert.assertEquals(11.98, service.getTotalCost(order.getId()));
    }

}