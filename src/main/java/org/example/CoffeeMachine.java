package org.example;

public class CoffeeMachine implements Runnable {

    public final String machineName;

    public CoffeeMachine(String machineName) {
        this.machineName = machineName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Order order = Order.takeFromOrderQueue();
                brewCoffee(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void brewCoffee(Order order) {
        // Add brewing logic here according to the coffee type in the order
        // Use different Thread.sleep time for each type of coffee
        try {
            Thread.sleep(getBrewingTime(order.getCoffeeType()));
            System.out.println(Thread.currentThread().getName() +
                    " " + machineName + " order id " + order.getId() +
                    " (" + order.getCoffeeType() + ") for " + order.getName() +
                    " is ready for pickup");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getBrewingTime(CoffeeType coffeeType) {
        switch (coffeeType) {
            case ESPRESSO:
                return 2000;
            case LATTE:
                return 4000;
            case MOCHA:
                return 16000; // probably is the most complicated made timing well off so it is easy to see the difference
            default:
                throw new IllegalArgumentException("Unknown coffee type"); //should never come up but good practice
        }
    }
}
