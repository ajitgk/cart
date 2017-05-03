package com.ak.cart.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ak on 01/05/2017.
 */
@Entity
@Table (name = "T_ORDER")
public class Order {

    public Order() {
        items = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    private Long id;

    private boolean paid = false;

    @OneToMany(mappedBy = "order")
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
