
import java.util.ArrayList;
import java.util.Scanner;

/**
 */
public class Main {

    public static void main(String[] args) {

        FruitShop fs = new FruitShop();

        do {
            System.out.println("FRUIT SHOP SYSTEM");
            System.out.println("1. Create Fruit");
            System.out.println("2. Update Fruit");
            System.out.println("3. View orders");
            System.out.println("4. Shopping (for buyer)");
            System.out.println("5. Exit");

            int selection = Validation.inputInt("Your selection: ", 1, 5);
            switch (selection) {
                case 1:
                    while (true) {
                        fs.createFruit();
                        if (!Validation.inputYN("Do you want to create again? (y/n): ")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    fs.updateFruit();
                case 3:
                    fs.viewOrder();
                    break;
                case 4:
                    fs.shopping();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

}
