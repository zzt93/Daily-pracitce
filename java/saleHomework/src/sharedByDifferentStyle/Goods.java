package sharedByDifferentStyle;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public enum Goods {
    A("a", 10),
    B("b", 20),
    C("c", 30),
    D("d", 40);

    private String name;
    private double price;

    Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
