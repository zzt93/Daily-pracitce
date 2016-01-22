package call;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class MainUI {

    public static void start(Consumer<Scanner> consumer) {
        String welcome = "Welcome to sale system";
        saySome(welcome);

        choice(consumer);
    }

    public static void exit() {
        String goodbye = "see you again";
        saySome(goodbye);
    }

    private static void saySome(String str) {
        System.out.println("-------------------------------------");
        System.out.println(str);
        System.out.println("-------------------------------------");
    }

    public static void choice(Consumer<Scanner> consumer) {
        System.out.println("choice:");
        Scanner scanner = new Scanner(System.in);
        int i;
        do {
            System.out.println("1: sale");
            System.out.println("2: quit");
            i = scanner.nextInt();
            switch (i) {
                case 1:
                    consumer.accept(scanner);
                    break;
                case 2:
                    exit();
                    break;
                default:
                    System.out.println("no such choice! input again");
            }
        } while (i != 2);
    }

    private static void callProcedure(Scanner scanner) {
        SalePrompt saleUI = new SalePrompt(scanner);
        Sale sale = saleUI.sale();
        saleUI.charge(sale);
        sale.printBill();
    }

    public static void main(String[] args) {
        MainUI.start(MainUI::callProcedure);
    }
}
