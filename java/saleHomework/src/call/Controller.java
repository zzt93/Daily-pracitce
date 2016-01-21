package call;

import layered.view.SaleUI;

import java.util.Scanner;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class Controller {

    public static void start() {
        System.out.println("-------------------------------------");
        System.out.println("Welcome to sale system");
        System.out.println("-------------------------------------");

        choice();
    }

    public static void choice() {
        System.out.println("choice:");
        System.out.println("1: sale");
        System.out.println("2: quit");
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        while (scanner.hasNext() && i != 2) {
            i = scanner.nextInt();
            switch (i) {
                case 1:
                    SaleUI saleUI = new SaleUI(scanner);
                    Sale sale = saleUI.sale();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("no such choice! input again");
            }
        }
    }
}
