package com.ak.cart.controller;

import com.ak.cart.model.Item;
import com.ak.cart.repository.ItemRepository;
import com.ak.cart.service.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Created by ak on 01/05/2017.
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ItemService.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemServiceTest {

    @Autowired
    private ItemService service;

    @Autowired
    private ItemRepository itemRepository;

    private Item item1;
    private Item item2;

    private static final BigDecimal NEW_PRICE = new BigDecimal("2.79");


    @Before
    public void setUp() {
        item1 = createItem(1, "Burger", new BigDecimal(1.99));
        item2 = createItem(2, "Fries", new BigDecimal(0.99));

        service.save(item1);

    }

    private Item createItem(long id, String name, BigDecimal price) {
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);

        return item;
    }

    @Test
    public void testSaveAndGet() throws Exception {

        Assert.assertEquals(1, service.getAll().size());

        service.save(item2);
        Assert.assertEquals(2, service.getAll().size());
    }

}