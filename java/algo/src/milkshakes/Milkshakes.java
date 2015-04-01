package milkshakes;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zzt on 3/19/15.
 */
public class Milkshakes {

    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static String milk(int flavor, ArrayList<Order> orders) {
        if (conflict(orders, 0)) {
            return IMPOSSIBLE;
        }

        // get minimum
        StringBuilder res = new StringBuilder(2 * flavor);
        int flavorCount = 1;
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.isRemoved()) {
                continue;
            }
            for (; flavorCount < order.getFlavor(); flavorCount++) {
                res.append("0 ");
            }
            res.append(order.getUnmalt() + " ");
            flavorCount++;
        }
        for (int i = flavorCount; i < flavor; i++) {
            res.append("0 ");
        }
//        for (int i = 0; i < flavor; i++) {
//            while (flavorCount < orders.size() && orders.get(flavorCount).getFlavor() < i + 1) {
//                flavorCount++;
//            }
//
//            if (flavorCount >= orders.size()) {
//                res.append("0 ");
//                continue;
//            }
//            Order order = orders.get(flavorCount);
//            if (order.getFlavor() != i + 1) {
//                assert order.getFlavor() > i + 1;
//                res.append("0 ");
//                continue;
//            }
//            if (!order.isRemoved() && order.getUnmalt() == 1) {
//                if (solved(order)) {
//                    res.append("0 ");
//                } else {
//                    res.append("1 ");
//                }
//            } else if (!order.isRemoved()) {
//                res.append("0 ");
//            }
//        }
        return res.toString();
    }

    private static boolean conflict(ArrayList<Order> orders, int start) {
        int con;
        if (start >= orders.size()) {
            return false;
        }
        if ((con = hasConflict(orders, start)) < orders.size()) {
            int removeCount = remove(con, orders, true);//TODO check count
            boolean conflict = false;
            for (int i = 0; i < removeCount; i++) {
                // satisfy all the related customers
                Order order = orders.get(i + con);
                Customer customer = order.getCustomer();
                if (!customer.canSatisfyWithout()) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict && !conflict(orders, removeCount + con)) { // if delete the latter can solve the problem
                return false;
            }

            conflict = false;
            int recover = recover(con, orders, true);
            assert removeCount == recover && recover >= 1;
            removeCount = remove(con, orders, false);
            for (int count = removeCount; count > 0; count--) {
                // satisfy all the related customers
                Order order = orders.get(con - count);
                Customer customer = order.getCustomer();
                if (!customer.canSatisfyWithout()) {
                    conflict = true;
                    break;
                }
            }

            if (conflict || conflict(orders, con + recover)) {//TODO check start
                return true;
            }
        }
        return false;
    }

    private static boolean solved(Order order) {
        //
        return false;
    }


    private static int remove(int i, ArrayList<Order> orders, boolean after) {
        return removeOrRecover(i, orders, after, true);
    }
    private static int recover(int i, ArrayList<Order> orders, boolean after) {
        return removeOrRecover(i, orders, after, false);
    }

    /**
     *
     * @param index -- starting (inclusive) or ending (exclusive) index depending on @parm after
     * @param orders
     * @param after -- mark it as removed after this index or before
     * @param remove -- whether set it as removed or ...
     * @return count of removed/recover item
     */
    private static int removeOrRecover(int index, ArrayList<Order> orders, boolean after, boolean remove) {
        if (after) {
            Order aim = orders.get(index);
            aim.setRemoved(remove);
            int i1 = index + 1;

            while (i1 < orders.size()) {
                Order order = orders.get(i1);
                if (order.compareTo(aim) != 0) {
                    break;
                }
                i1++;
            }
            return i1 - index;
        } else {
            assert index > 0;
            Order aim = orders.get(index - 1);
            aim.setRemoved(remove);

            int j = index - 2;
            while (j >= 0) {
                Order order = orders.get(j);
                if (order.compareTo(aim) != 0) {
                    break;
                }
                j++;
            }
            return index - 1 - j;
        }
    }

    /**
     * Judge whether orders has conflict
     * @param orders
     * @param start
     * @return the index where conflict happen
     */
    private static int hasConflict(ArrayList<Order> orders, int start) {
        int last = orders.get(start).getFlavor();
        int unmalt = orders.get(start).getUnmalt();
        for (int i = start + 1; i < orders.size(); i++) {
            Order t = orders.get(i);
            if (t.getFlavor() == last && t.getUnmalt() != unmalt) {
                return i;
            }
            last = t.getFlavor();
        }
        return orders.size();
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("Milk-minor-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            int flavor = in.nextInt();
            int customerCount = in.nextInt();
            Customer[] customers = new Customer[customerCount];
            ArrayList<Order> orders = new ArrayList<Order>(flavor);
            for (int i1 = 0; i1 < customerCount; i1++) {
                customers[i1] = new Customer(in);
                orders.addAll(customers[i1].getOrders());
            }
            Collections.sort(orders);
            String res = milk(flavor, orders);
            out.println("case #" + (i+1) + ": " + res);
        }
    }
}
