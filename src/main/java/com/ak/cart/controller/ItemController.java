package com.ak.cart.controller;

import com.ak.cart.model.Item;
import com.ak.cart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ak on 01/05/2017.
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Item create(@RequestBody Item item) {
        return itemService.save(item);
    }
}
