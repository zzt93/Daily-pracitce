package layered;

import call.MainUI;
import layered.view.SaleUI;

import java.util.Scanner;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class Layered {

    public static void startSaleUI(Scanner scanner) {
        SaleUI saleUI = new SaleUI(scanner);
        saleUI.sale();
        saleUI.charge();
        saleUI.bill();
    }

    public static void main(String[] args) {
        MainUI.start(Layered::startSaleUI);
    }
}
