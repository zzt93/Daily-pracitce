package object;

import call.MainUI;

import java.util.Scanner;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class Starter {
    public static void objChoice(Scanner scanner) {
        SaleUI saleUI = new SaleUI(scanner);
        saleUI.sale();
        saleUI.charge();
        saleUI.bill();
    }

    public static void main(String[] args) {
        MainUI.start(Starter::objChoice);
    }
}
