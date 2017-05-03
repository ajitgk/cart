package com.ak.cart.service;

import com.ak.cart.model.Item;
import com.ak.cart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ak on 01/05/2017.
 */
@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll () {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
