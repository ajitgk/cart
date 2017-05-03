package com.ak.cart.controller;

import com.ak.cart.model.Item;
import com.ak.cart.model.Order;
import com.ak.cart.service.ItemService;
import org.junit.Assert;
import org.junit.Before;
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
@ContextConfiguration(classes = ItemController.class)
public class ItemControllerTest {

    @MockBean
    private ItemService service;

    @Autowired
    private ItemController controller;

    private Order order;


    @Before
    public void setUp() {
        Item item1 = createItem(1, "Jeans", new BigDecimal(10.99));
        Item item2 = createItem(2, "Shirt", new BigDecimal(5.99));

        Mockito.when(service.getAll()).thenReturn(new ArrayList<Item>(Arrays.asList(item1, item2)));
    }

    private Item createItem(long id, String name, BigDecimal price) {
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);

        return item;
    }

    @Test
    public void testGetAll() {
        Assert.assertEquals(2, controller.getAll().size());
    }

}