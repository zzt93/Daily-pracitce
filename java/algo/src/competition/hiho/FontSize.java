package competition.hiho;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zzt on 4/6/16.
 * <p>
 * Usage:
 */
public class FontSize {

    private static int font(ArrayList<Integer> ints, ArrayList<Integer> count) {
        double n = ints.get(0), p = ints.get(1), w = ints.get(2), h = ints.get(3);
        int sum = 0;
        for (Integer integer : count) {
            sum += integer;
        }
        int est = (int) Math.floor(Math.sqrt(p / sum * h * w)) + 1;
        // try put in page
        boolean lastIsLarge;
        boolean tooLarge;

        tooLarge = false;
        int times = 0;
        do {
            times++;
            double countInLine = (int) Math.floor(w / est);
            double lineInPage = (int) Math.floor(h / est);
            lastIsLarge = tooLarge;
            tooLarge = false;
            int pages = 0;
            int lineC = 0;
            if (lineInPage == 0
                    || countInLine == 0) {
                tooLarge = true;
            } else {
                for (Integer ai : count) {
                    int lines = (int) Math.ceil(ai / countInLine);
                    pages += Math.floor(lines / lineInPage);
                    lineC += lines % lineInPage;
                    if (lineC >= lineInPage) {
                        lineC -= lineInPage;
                        pages ++;
                    }
                    if (pages > p) {
                        tooLarge = true;
                        break;
                    }
                }
            }
            if (lineC != 0) {
                pages++;
                if (pages > p) {
                    tooLarge = true;
                }
            }
            if (tooLarge) {
                est--;
            } else {
                est++;
            }
        } while (times == 1 || lastIsLarge == tooLarge);

        return tooLarge ? est : est - 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        for (int i = 0; i < testCase; i++) {
            ArrayList<Integer> ints = oneLineToInt(in, " ");
            System.out.println(font(ints, oneLineToInt(in, " ")));
        }
    }

    public static ArrayList<Integer> oneLineToInt(Scanner in, String del) {
        String line = in.nextLine();
        if (line.isEmpty()) {
            line = in.nextLine();
        }

        ArrayList<Integer> res = new ArrayList<Integer>();
        for (String s : line.split(del)) {
            if (s.equals("")) {
                continue;
            }
            res.add(Integer.parseInt(s));
        }
        return res;
    }
}
