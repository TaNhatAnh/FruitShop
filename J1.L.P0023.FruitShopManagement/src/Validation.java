
import java.util.Scanner;

public class Validation {
        //allow user to input an integer in range [min..max]
    public static int inputInt(String mess, int min, int max) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mess);
        int number = 0;
        do {
            try {
                number = Integer.parseInt(sc.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.print("Out of range! Please enter in range " + min + " to " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.err.print("Invalid! Please enter an integer number: ");
            }
        } while (true);
    }

    //allow user to input a double in range [min..max]
    public static double inputDouble(String mess, double min, double max) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mess);
        double number;
        do {
            try {
                number = Double.parseDouble(sc.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.print("Out of range! Please enter in range " + min + " to " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.err.print("Invalid! Please enter a double number: ");
            }
        } while (true);
    }

    //allow user to input a not null String
    public static String inputString(String mess) {
        System.out.println(mess);
        Scanner sc = new Scanner(System.in);
        String Regex ="[A-Za-z]+";
        String text;
        do {
            text = sc.nextLine();
            if (text.matches(Regex)) {
                break;
            }
            System.out.println("nhap : ' " + Regex + " ' ");

        } while (true);
        return text;
    }

    //allow user to input Y or N
    public static boolean inputYN(String mess) {
        Scanner sc = new Scanner(System.in);
        String option;
        do {
            System.out.print(mess);
            option = sc.nextLine().trim();
            //end selecting process and order.
            if (option.equalsIgnoreCase("y")) {
                return true;
            } //continue to select
            else if (option.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.err.println("Please enter Y for yes or N for no!");
            }
        } while (true);
    }
}
