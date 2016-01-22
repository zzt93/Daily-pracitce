package mvc;

import call.MainUI;

import java.util.Scanner;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class MVC {

    public static void startSaleView(Scanner scanner) {
        SaleView saleView = new SaleView(scanner);
        saleView.sale();
        saleView.charge();
        saleView.bill();
    }

    public static void main(String[] args) {
        MainUI.start(MVC::startSaleView);
    }
}
