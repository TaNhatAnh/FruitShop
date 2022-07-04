
import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Database {

    private ArrayList<Fruit> warehouse = new ArrayList<>();
    private Hashtable<Customer, ArrayList<Fruit>> orders = new Hashtable<>();

    public Database() {
        warehouse.add(new Fruit(1, "Coconut", 2.0, 10, "Vietnam"));
        warehouse.add(new Fruit(2, "Orange", 3.0, 10, "US"));
        warehouse.add(new Fruit(3, "Apple", 4.0, 10, "Thailand"));
        warehouse.add(new Fruit(4, "Grape", 6.0, 10, "France"));
    }

    public ArrayList<Fruit> getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(ArrayList<Fruit> warehouse) {
        this.warehouse = warehouse;
    }

    public Hashtable<Customer, ArrayList<Fruit>> getOrders() {
        return orders;
    }

    public void setOrders(Hashtable<Customer, ArrayList<Fruit>> orders) {
        this.orders = orders;
    }

    public void addFruit(Fruit f) {
        warehouse.add(f);
    }

    public void addOrder(Customer c, ArrayList<Fruit> o) {
        orders.put(c, o);
    }
    

}
