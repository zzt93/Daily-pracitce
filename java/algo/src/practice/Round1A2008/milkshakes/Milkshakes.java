package practice.Round1A2008.milkshakes;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zzt on 3/19/15.
 * <p/>
 * Description of problem:
 * You want to make N batches of practice.Round1A2008.milkshakes, so that:
 * <p/>
 * There is exactly one batch for each flavor of milkshake, and it is either malted or unmalted.
 * For each customer, you make at least one milkshake type that they like.
 * The minimum possible practice.round1A2008.number of batches are malted.
 * <p/>
 * Find whether it is possible to satisfy all your customers given these constraints, and if it is, what milkshake types you should make.
 * <p/>
 * If it is possible to satisfy all your customers, there will be only one answer which minimizes the practice.round1A2008.number of malted batches.
 * <p/>
 * Input
 * <p/>
 * One line containing an integer C, the practice.round1A2008.number of test cases in the input file.
 * <p/>
 * For each test case, there will be:
 * <p/>
 * One line containing the integer N, the practice.round1A2008.number of milkshake flavors.
 * One line containing the integer M, the practice.round1A2008.number of customers.
 * M lines, one for each customer, each containing:
 * An integer T >= 1, the practice.round1A2008.number of milkshake types the customer likes, followed by
 * T pairs of integers "X Y", one for each type the customer likes, where X is the milkshake flavor between 1 and N inclusive, and Y is either 0 to indicate unmalted, or 1 to indicated malted. Note that:
 * No pair will occur more than once for a single customer.
 * Each customer will have at least one flavor that they like (T >= 1).
 * Each customer will like at most one malted flavor. (At most one pair for each customer has Y = 1).
 * All of these numbers are separated by single spaces.
 * <p/>
 * Output
 * <p/>
 * C lines, one for each test case in the order they occur in the input file, each containing the string "Case #X: " where X is the practice.round1A2008.number of the test case, starting from 1, followed by:
 * The string "IMPOSSIBLE", if the customers' preferences cannot be satisfied; OR
 * N space-separated integers, one for each flavor from 1 to N, which are 0 if the corresponding flavor should be prepared unmalted, and 1 if it should be malted.
 */
public class Milkshakes {

    public static final String IMPOSSIBLE = " IMPOSSIBLE";
    private static HashSet<Integer> optionOne;


    public static String milk(int flavor, ArrayList<Order> orders) {
        if (conflict(orders, 0)) {
            return IMPOSSIBLE;
        }

        // get minimum
        StringBuilder res = new StringBuilder(2 * flavor);
        int flavorCount = 1;
        for (Order order : orders) {
            if (order.getUnmalt() == 1 && !order.isRemoved()) {
                solved(order);
            }
            if (order.isRemoved() || order.getFlavor() < flavorCount) {
                continue;
            }
            for (; flavorCount < order.getFlavor(); flavorCount++) {
                res.append(" 0");
            }
            res.append(" ").append(order.getUnmalt());
            flavorCount++;
        }
        for (int i = flavorCount; i < flavor + 1; i++) {
            res.append(" 0");
        }


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

            int removeCount = remove(con, orders, true);

            boolean conflict = false;
            for (int i = 0; i < removeCount; i++) {
                // satisfy all the related customers
                Order order = orders.get(i + con);
                Customer customer = order.getCustomer();

                if (!customer.canSatisfyForNow()) {
                    customer.getRemoved(optionOne);
                    conflict = true;
                    break;
                }
            }

            if (!conflict && !conflict(orders, removeCount + con)) { // if delete the latter can solve the problem
                return false;
            }


            if (!optionOne.contains(orders.get(con).getFlavor())) {
                recover(con, orders, true);
                return true;
            }
            optionOne.clear();
            conflict = false;
            int recover = recover(con, orders, true);
            assert removeCount == recover && recover >= 1;
            removeCount = remove(con, orders, false);
            for (int count = removeCount; count > 0; count--) {
                // satisfy all the related customers
                Order order = orders.get(con - count);
                Customer customer = order.getCustomer();

                if (!customer.canSatisfyForNow()) {
                    customer.getRemoved(optionOne);
                    conflict = true;
                    break;
                }
            }


            if (conflict || conflict(orders, con + recover)) {
                recover(con, orders, false);
                return true;
            }
        }
        return false;
    }


    /**
     * Invoke when this order is malted
     *
     * @param order this order
     * @return whether this can be replaced by other order -- for one customer only have
     * one order that is malted, so if it exist, it must be better
     */
    private static boolean solved(Order order) {
        Customer customer = order.getCustomer();
        if (customer.canSatisfyWithout()) {//have other satisfied order
            order.setRemoved(true);
            return true;
        } else {
            List<Order> orders = customer.getOrders();
            if (orders.size() == 1) {
                return false;
            } /*else {// has more order, but have been removed
                I don't know why, but without it can also solve the test case
            }*/
        }
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
     * @param index  -- starting (inclusive) or ending (exclusive) index depending on @parm after
     * @param orders -- collections of order
     * @param after  -- mark it as removed after this index or before
     *
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

                if (!order.equals(aim)) {
                    break;
                }
                order.setRemoved(remove);

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

                if (!order.equals(aim)) {
                    break;
                }
                order.setRemoved(remove);
                j--;

            }
            return index - 1 - j;
        }
    }

    /**
     * Judge whether orders has conflict
     * <<<<<<< HEAD
     *
     * @param orders -- collections of order
     * @param start  -- start index to check
     * @return the index where conflict happen
     */
    private static int hasConflict(ArrayList<Order> orders, int start) {
        int last = orders.get(start).getFlavor();
        int unmalt = orders.get(start).getUnmalt();
        for (int i = start + 1; i < orders.size(); i++) {
            Order t = orders.get(i);

            if (t.isRemoved()) {
                continue;
            }
            if (t.getFlavor() == last && t.getUnmalt() != unmalt) {
                return i;
            }
            last = t.getFlavor();

            unmalt = t.getUnmalt();

        }
        return orders.size();
    }

    public static void main(String[] args) {

//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/Milk-large-practice.in");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            int flavor = in.nextInt();

            optionOne = new HashSet<Integer>(flavor);

            int customerCount = in.nextInt();
            Customer[] customers = new Customer[customerCount];
            ArrayList<Order> orders = new ArrayList<Order>(flavor);
            for (int i1 = 0; i1 < customerCount; i1++) {
                customers[i1] = new Customer(in);
                orders.addAll(customers[i1].getOrders());
            }
            Collections.sort(orders);
            String res = milk(flavor, orders);

            out.println("case #" + (i + 1) + ":" + res);

        }
    }

}
