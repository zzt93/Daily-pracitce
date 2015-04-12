package milkshakes;

/**
 * Created by zzt on 3/30/15.
 */
public class Order implements Comparable<Order>{
    private boolean removed = false;
    private int flavor;
    private int unmalt;
    private Customer customer;

    public Order(int flavor, int unmalt, Customer customer) {
        this.flavor = flavor;
        this.unmalt = unmalt;
        this.customer = customer;
    }

    public int getFlavor() {
        return flavor;
    }

    public int getUnmalt() {
        return unmalt;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public int compareTo(Order o) {
<<<<<<< HEAD
        if (o == null) {
            throw new IllegalArgumentException("The object to compare is null");
        }
=======
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
        if (flavor < o.flavor) {
            return -1;
        } else if (flavor == o.flavor) {
            if (unmalt < o.unmalt) {
                return -1;
            } else if (unmalt > o.unmalt) {
                return 1;
<<<<<<< HEAD
            } else {
                int a = customer.getOrders().size();
                int b = o.customer.getOrders().size();
                if (a < b) {
                    return -1;
                } else if (a > b) {
                    return 1;
                }
=======
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
            }
            return 0;
        } else {
            return 1;
        }
    }

<<<<<<< HEAD
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Order.class) {
            throw new IllegalArgumentException("The object to compare is null");
        }
        Order o = (Order)obj;
        if (o.flavor == flavor && o.unmalt == unmalt) {
            return true;
        }
        return false;
    }

=======
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
    public void setRemoved(boolean removed) {
        customer.updateRemove(removed);
        this.removed = removed;
    }

    public boolean isRemoved() {
        return removed;
    }
}
