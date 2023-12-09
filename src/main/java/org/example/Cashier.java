package org.example;

import com.github.javafaker.Faker;



public class Cashier implements Runnable {

    private final String cashierName;

    public Cashier(String cashierName) {
        this.cashierName = cashierName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Order order = generateRandomOrder();
                System.out.println(Thread.currentThread().getName() +
                        " " + cashierName + ": Order id " + order.getId() +
                        " (" + order.getCoffeeType() + ") is accepted for " + order.getName());
                order.addToOrderQueue();
                Thread.sleep(2000); //
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Order generateRandomOrder() {
        Faker faker = new Faker();
        String customerName = faker.name().firstName();
        CoffeeType coffeeType = CoffeeType.values()[(int) (Math.random() * CoffeeType.values().length)];
        return new Order(customerName, coffeeType);
    }
}
