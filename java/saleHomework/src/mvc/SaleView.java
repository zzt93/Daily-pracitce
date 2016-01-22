package mvc;


import java.util.Scanner;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class SaleView {
    private Controller controller;
    private Scanner in;

    public SaleView(Scanner scanner) {
        controller = new Controller(this);
        in = scanner;
    }

    public void sale() {
        controller.sale();
    }

    public void bill() {
        controller.bill();
    }

    public void charge() {
        controller.charge();
    }

    public Scanner getIn() {
        return in;
    }
}
