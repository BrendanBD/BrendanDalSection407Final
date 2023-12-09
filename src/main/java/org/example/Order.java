package org.example;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;  //needed for concurenccy
import java.util.concurrent.LinkedBlockingQueue;

public class Order {

    private static final BlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    private String id;
    private String name;
    private CoffeeType coffeeType;

    public Order(String name, CoffeeType coffeeType) {
        this.id = generateID();
        this.name = name;
        this.coffeeType = coffeeType;
    }

    private String generateID() {
        String shortUuid = UUID.randomUUID().toString();
        return shortUuid.substring(0, 5); // only want order id to be 5 char
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void addToOrderQueue() {
        try {
            queue.put(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Order takeFromOrderQueue() throws InterruptedException {
        return queue.take();
    }
}
