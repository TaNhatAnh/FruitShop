
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class FruitShop {

    public Database db = new Database();
    Validation check = new Validation();

    public void createFruit() {

        int id;
        do {
            id = Validation.inputInt("Enter ID: ", 1, Integer.MAX_VALUE);
            if (checkDuplicateFruitID(id)) {
                System.err.println("Duplicate ID, please re-enter!");

                continue;
            } else {

                break;
            }
        } while (true);

        String name = Validation.inputString("Fruit name: ");
        int quantity = Validation.inputInt("Enter the total quantity: ", 0, Integer.MAX_VALUE);
        double price = Validation.inputDouble("Enter price: ", 0, Double.MAX_VALUE);
        String origin = Validation.inputString("Origin: ");
        db.getWarehouse().add(new Fruit(id, name, price, quantity, origin));
        System.err.println("Added successfully!\n");

    }

    public void updateFruit() {
        Scanner sc = new Scanner(System.in);
        while (true) {
//            try {
                int flag = 0;
                System.out.print("ID: ");
                int idRm = Integer.parseInt(sc.nextLine());
                for (Fruit fruit : db.getWarehouse()) {
                    if (fruit.getId() == idRm) {
                        String name = Validation.inputString("Fruit name: ");
                        fruit.setName(name);
                        int quantity = Validation.inputInt("Enter the total quantity: ", 0, Integer.MAX_VALUE);
                        fruit.setQuantity(quantity);
                        double price = Validation.inputDouble("Enter price: ", 0, Double.MAX_VALUE);
                        fruit.setPrice(price);
                        String origin = Validation.inputString("Origin: ");
                        fruit.setOrigin(origin);
                        System.out.println("Successfull");
                        flag = 1;
                    }

                }
                if (flag == 0) {
                    boolean check = Validation.inputYN("Don't have id do you want create? (y/n): ");
                    if (check == true){
                           createFruit();
                    }
                    else break;
                }
                break;
//            } catch (Exception e) {
//                System.out.println("ID does not exist");
//                System.out.println("Please enter again");
//            }
        }

    }

    public void displayOrderList(ArrayList<Fruit> order) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Fruit fruit : order) {
            System.out.printf("%15s%15d%15.0f%15.0f\n", fruit.getName(), fruit.getQuantity(),
                    fruit.getPrice(), fruit.getPrice() * fruit.getQuantity());
            total += fruit.getPrice() * fruit.getQuantity();
        }
        System.out.println("Total: " + total);
    }

    public void viewOrder() {
        if (db.getOrders().isEmpty()) {
            System.out.println("There is no orders!\n");
            return;
        }
        for (Customer customer : db.getOrders().keySet()) {
            System.out.println("Customer: " + customer.getName());
            displayOrderList(db.getOrders().get(customer));
        }
    }

    public void displayAvailableFruit(ArrayList<Fruit> availables) {
        int item = 1;
        System.out.printf("%-10s%-20s%-20s%-10s%-15s\n", "Item", "Fruit name", "Origin", "Price", "Quantity");
        for (Fruit fruit : availables) {
            System.out.printf("%-10d%-20s%-20s%-10.0f%-15s\n", item++, fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity());
        }
    }

 
    public boolean checkExistItem(ArrayList<Fruit> order, int id) {
        for (Fruit selectedFruit : order) {
            if (selectedFruit.getId() == (id)) {
                return true;
            }
        }
        return false;
    }

    public void shopping() {
        if (db.getWarehouse().isEmpty()) {
            System.out.println("There is nothing to buy! Thanks for choosing us. See you later!\n");
            return;
        }
        ArrayList<Fruit> order = new ArrayList<>();
        ArrayList<Fruit> availables = new ArrayList<>();
        do {
            availables.clear(); 
            for (Fruit fruit : db.getWarehouse()) {
                if (fruit.getQuantity() > 0) {
                    availables.add(fruit);
                }
            }
            if (availables.isEmpty()) {
                System.out.println("All products were sold out!");
                if (!order.isEmpty()) { 
                    displayOrderList(order);
                    Customer customer = new Customer(Validation.inputString("Enter your name: "));
                    db.getOrders().put(customer, order);
                }
                return;
            }
            displayAvailableFruit(availables);
            int choosingItem = Validation.inputInt("Choose an item: ", 1, availables.size());
            Fruit choosingFruit = availables.get(choosingItem - 1);
            System.out.println("You selected: " + choosingFruit.getName());
            int choosingQuantity = Validation.inputInt("Please enter quantity: ", 1, choosingFruit.getQuantity());
            if (checkExistItem(order, choosingFruit.getId())) {
                if (Validation.inputYN("This item has been selected. Enter Y to update quantity, N if you dont want to update: ")) {
                    for (Fruit selectedFruit : order) {
                        if (selectedFruit.getId() == choosingFruit.getId()) {
                            int diffQuan = choosingQuantity - selectedFruit.getQuantity();
                            getFruitByID(choosingFruit.getId()).setQuantity(choosingFruit.getQuantity() - diffQuan);//set newQuan
                            selectedFruit.setQuantity(choosingQuantity);
                        }
                    }
                }
                else {
                }
            } else {
                order.add(new Fruit(choosingFruit.getId(), choosingFruit.getName(), choosingFruit.getPrice(), choosingQuantity));
                getFruitByID(choosingFruit.getId()).setQuantity(choosingFruit.getQuantity() - choosingQuantity);
            }

            if (Validation.inputYN("Do you want to order now? (y/n): ")) {
                displayOrderList(order);
                Customer customer = new Customer(Validation.inputString("Enter your name: "));
                db.getOrders().put(customer, order);
                return;
            }
        } while (true);
    }

    public  boolean checkDuplicateFruitID(int id) {
        for (Fruit fruit : db.getWarehouse()) {
            if (fruit.getId() == id && !db.getWarehouse().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Fruit getFruitByID(int ID) {
        for (Fruit fruit : db.getWarehouse()) {
            if (fruit.getId() == ID) {
                return fruit;
            }
        }
        return null;
    }

}
