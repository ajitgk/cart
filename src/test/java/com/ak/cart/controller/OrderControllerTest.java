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
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ak on 01/05/2017.
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrderController.class)
public class OrderControllerTest {

    @MockBean
    private OrderService service;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderController orderController;


    private Order order;


    @Before
    public void setUp() {
        Item item1 = createItem(1, "Jeans", new BigDecimal(10.99));
        Item item2 = createItem(2, "Shirt", new BigDecimal(5.99));

        order = new Order();
        order.setId(1L);

        order.setItems(new ArrayList<Item>(Arrays.asList(item1, item2)));

        Mockito.when(orderRepository.findOne(order.getId())).thenReturn(order);
        Mockito.when(itemRepository.findOne(item1.getId())).thenReturn(item1);
        Mockito.when(service.getAll()).thenReturn(new ArrayList<Order>(Arrays.asList(order)));
        Mockito.when(service.save(order)).thenReturn(order);
    }

    private Item createItem(long id, String name, BigDecimal price) {
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);


        return item;
    }

    private MockMvc mockMvc;


    @Test
    public void testCreate() {

        orderController.addItem(order.getId(), 1l, BigDecimal.ONE);

        Assert.assertEquals(1, orderController.getAll().size());
    }

    @Test
    @Ignore
    public void testGetTotalCost() {
        orderController.addItem(order.getId(), 1l, BigDecimal.ONE);
        orderController.addItem(order.getId(), 1l, BigDecimal.ONE);


        Assert.assertEquals(11.98, orderController.getTotalCost(order.getId()));
    }

}