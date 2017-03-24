package enum_type;

import java.util.Scanner;

/**
 * Created by zzt on 3/30/16.
 * <p>
 * Usage:
 * <h3>A simple state machine</h3>
 * <p>
 * Representing state machine of regular expression: xy*xz+y
 * </p>
 */
public class StateMachine {

    public static final String X = "x";
    public static final String Y = "y";
    public static final String Z = "z";

    enum State {
        SUCC("succeed") {
            @Override
            public State next(String s) {
                return null;
            }
        },
        FAIL("fail") {
            @Override
            public State next(String s) {
                return null;
            }
        },
        Start("start") {
            @Override
            public State next(String s) {
                String tmp = s.toLowerCase();
                switch (tmp) {
                    case X:
                        return A;
                    default:
                        return FAIL;
                }
            }
        },
        A("a") {
            @Override
            public State next(String s) {
                String tmp = s.toLowerCase();
                switch (tmp) {
                    case X:
                        return B;
                    case Y:
                        return A;
                    default:
                        return FAIL;
                }
            }
        },
        B("b") {
            @Override
            public State next(String s) {
                String tmp = s.toLowerCase();
                switch (tmp) {
                    case Z:
                        return C;
                    default:
                        return FAIL;
                }
            }
        },
        C("c") {
            @Override
            public State next(String s) {
                String tmp = s.toLowerCase();
                switch (tmp) {
                    case Y:
                        return SUCC;
                    case Z:
                        return C;
                    default:
                        return FAIL;
                }
            }
        },;

        private String des;

        State(String s) {
            this.des = s;
        }

        public String getDes() {
            return des;
        }

        public abstract State next(String s);
    }

    private Scanner scanner = new Scanner(System.in);

    public void test() {
        while (scanner.hasNext()) {
            State start = State.Start;
            String next = scanner.next();
            for (char c : next.toCharArray()) {
                start = start.next("" + c);
                if (start == State.SUCC || start == State.FAIL) {
                    break;
                }
            }
            System.out.println(start.getDes() + ":" + next);
        }
    }

    public static void main(String[] args) {
        new StateMachine().test();
    }
}
