import java.util.*;

class Cust {
    private int id;
    private String name;
    private int pts;

    public Cust(int id, String name, int pts) {
        this.id = id;
        this.name = name;
        this.pts = pts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPts() {
        return pts;
    }

    @Override
    public String toString() {
        return "Cust{id=" + id + ", name='" + name + "', pts=" + pts + '}';
    }
}

class Prod {
    private int id;
    private String name;
    private double price;

    public Prod(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Prod{id=" + id + ", name='" + name + "', price=" + price + '}';
    }
}

class Ord {
    private int id;
    private int custId;
    private String date;

    public Ord(int id, int custId, String date) {
        this.id = id;
        this.custId = custId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getCustId() {
        return custId;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ord{id=" + id + ", custId=" + custId + ", date='" + date + "'}";
    }
}

class ProdPriceComparator implements Comparator<Prod> {
    @Override
    public int compare(Prod p1, Prod p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

class ProdNameComparator implements Comparator<Prod> {
    @Override
    public int compare(Prod p1, Prod p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

class OrdDateComparator implements Comparator<Ord> {
    @Override
    public int compare(Ord o1, Ord o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

class CustPointsComparator implements Comparator<Cust> {
    @Override
    public int compare(Cust c1, Cust c2) {
        return Integer.compare(c2.getPts(), c1.getPts());
    }
}

public class AmazonApp {
    public static void main(String[] args) {
        TreeSet<Cust> customers = new TreeSet<>(new CustPointsComparator());
        customers.add(new Cust(1, "Saakshi", 100));
        customers.add(new Cust(2, "Rishabh", 200));
        customers.add(new Cust(3, "Arjun", 150));

        TreeSet<Prod> products = new TreeSet<>(new ProdPriceComparator());
        products.add(new Prod(1, "Iphone", 1500));
        products.add(new Prod(2, "PS5", 800));
        products.add(new Prod(3, "Laptop", 1200));

        TreeSet<Ord> orders = new TreeSet<>(new OrdDateComparator());
        orders.add(new Ord(1, 1, "2024-12-15"));
        orders.add(new Ord(2, 2, "2024-12-16"));
        orders.add(new Ord(3, 3, "2024-12-14"));

        Scanner sc = new Scanner(System.in);
        int ch;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Show Customers sorted by Points");
            System.out.println("2. Show Products sorted by Price");
            System.out.println("3. Show Products sorted by Name");
            System.out.println("4. Show Orders sorted by Date");
            System.out.println("5. Show Customer Details by ID");
            System.out.println("6. Show Product Details by ID");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nCustomers sorted by Points (Descending):");
                    customers.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("\nProducts sorted by Price (Ascending):");
                    products.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("\nProducts sorted by Name:");
                    TreeSet<Prod> productsByName = new TreeSet<>(new ProdNameComparator());
                    productsByName.addAll(products);
                    productsByName.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("\nOrders sorted by Date:");
                    orders.forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Enter Customer ID: ");
                    int custId = sc.nextInt();
                    boolean foundCustomer = false;
                    for (Cust cust : customers) {
                        if (cust.getId() == custId) {
                            System.out.println("\nCustomer Details: " + cust);
                            foundCustomer = true;
                            break;
                        }
                    }
                    if (!foundCustomer) {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Product ID: ");
                    int prodId = sc.nextInt();
                    boolean foundProduct = false;
                    for (Prod prod : products) {
                        if (prod.getId() == prodId) {
                            System.out.println("\nProduct Details: " + prod);
                            foundProduct = true;
                            break;
                        }
                    }
                    if (!foundProduct) {
                        System.out.println("Product not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (ch != 7);

        sc.close();
    }
}
