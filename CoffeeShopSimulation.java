import java.util.LinkedList;
import java.util.Queue;

// Custom Exception for Counter Empty
class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

// Coffee Counter Class
class CoffeeCounter {
    private final int capacity = 3;
    private final Queue<String> counter = new LinkedList<>();

    public synchronized void addCoffee(String coffee, String baristaName) throws InterruptedException {
        while (counter.size() == capacity) {
            System.out.println(baristaName + " is waiting. Counter is full.");
            wait();
        }
        counter.add(coffee);
        System.out.println(baristaName + " prepared coffee. Counter: " + counter.size());
        notifyAll(); // Notify customers and reviewer
    }

    public synchronized String pickCoffee(String customerName) throws CounterEmptyException, InterruptedException {
        while (counter.isEmpty()) {
            System.out.println(customerName + " is waiting. Counter is empty.");
            wait();
        }
        String coffee = counter.poll();
        System.out.println(customerName + " picked up coffee. Counter: " + counter.size());
        notifyAll(); // Notify baristas and reviewer
        return coffee;
    }

    public synchronized String sampleCoffee(String reviewerName) throws CounterEmptyException, InterruptedException {
        while (counter.isEmpty()) {
            System.out.println(reviewerName + " is waiting. Counter is empty.");
            wait();
        }
        String coffee = counter.peek(); // Review without removing
        System.out.println(reviewerName + " sampled coffee. Counter: " + counter.size());
        notifyAll(); // Notify baristas
        return coffee;
    }
}

// Barista Thread
class Barista implements Runnable {
    private final CoffeeCounter counter;
    private final int taskCount;
    private final String baristaName;

    public Barista(CoffeeCounter counter, int taskCount, String baristaName) {
        this.counter = counter;
        this.taskCount = taskCount;
        this.baristaName = baristaName;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < taskCount; i++) {
                counter.addCoffee("Coffee-" + (i + 1), baristaName);
                Thread.sleep(500); // Simulate coffee preparation time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Customer Thread
class Customer implements Runnable {
    private final CoffeeCounter counter;
    private final int coffeeCount;
    private final String customerName;

    public Customer(CoffeeCounter counter, int coffeeCount, String customerName) {
        this.counter = counter;
        this.coffeeCount = coffeeCount;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeeCount; i++) {
                counter.pickCoffee(customerName);
                Thread.sleep(500); // Simulate time between pickups
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (CounterEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}

// Reviewer Thread
class Reviewer implements Runnable {
    private final CoffeeCounter counter;
    private final String reviewerName;

    public Reviewer(CoffeeCounter counter, String reviewerName) {
        this.counter = counter;
        this.reviewerName = reviewerName;
    }

    @Override
    public void run() {
        try {
            counter.sampleCoffee(reviewerName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (CounterEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}

// Main Class
public class CoffeeShopSimulation {
    public static void main(String[] args) {
        CoffeeCounter counter = new CoffeeCounter();

        // Create threads with tasks
        Thread barista1 = new Thread(new Barista(counter, 2, "Barista 1"));
        Thread barista2 = new Thread(new Barista(counter, 3, "Barista 2"));

        Thread customer1 = new Thread(new Customer(counter, 1, "Customer 1"));
        Thread customer2 = new Thread(new Customer(counter, 2, "Customer 2"));
        Thread customer3 = new Thread(new Customer(counter, 1, "Customer 3"));

        Thread reviewer = new Thread(new Reviewer(counter, "Reviewer"));

        // Start threads
        barista1.start();
        barista2.start();

        customer1.start();
        customer2.start();
        customer3.start();

        reviewer.start();
    }
}

