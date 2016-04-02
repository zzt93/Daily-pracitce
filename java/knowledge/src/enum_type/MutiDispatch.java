package enum_type;

import java.util.Random;

import static enum_type.Outcome.*;

/**
 * Created by zzt on 3/30/16.
 * <p>
 * Usage:
 * Java only performs single
 * dispatching. That is, if you are performing an operation on more than one object whose type
 * is unknown, Java can invoke the dynamic binding mechanism on only one of those types.
 * <p>
 * General JVMs only use single dispatch: the runtime type
 * is only considered for the receiver object; for the method's parameters,
 * the static type is considered.
 * <p>
 * <p>
 * In many languages, the special argument is indicated syntactically;
 * for example, a number of programming languages put the special argument
 * before a dot in making a method call: special.method(other, arguments, here)
 * </p>
 * <pre>
 * // public class OOP {
 * //     void prove(Object o){
 * //         System.out.println("object");
 * //     }
 * //
 * //     void prove(Integer i){
 * //         System.out.println("integer");
 * //     }
 * //
 * //     void prove(String s){
 * //         System.out.println("string");
 * //     }
 * //
 * //     void test(){
 * //         Object o = new String("  ");
 * //         this.prove(o); // Prints
 * //     }
 * // }
 * </pre>
 * <p>
 * <a href="https://en.wikipedia.org/wiki/Multiple_dispatch">wiki multiple dispatch</a>
 */
enum Outcome {
    WIN, LOSE, DRAW
}

interface Competitor<T> {

    Outcome compete(T t);
}

enum RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);
    private Outcome vsPAPER, vsSCISSORS, vsROCK;

    RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
        this.vsPAPER = paper;
        this.vsSCISSORS = scissors;
        this.vsROCK = rock;
    }

    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:
                return vsPAPER;
            case SCISSORS:
                return vsSCISSORS;
            case ROCK:
                return vsROCK;
        }
    }

    private static Random random = new Random();

    public static RoShamBo2 randomOne() {
        return values()[random.nextInt(values().length)];
    }

    public static void match(RoShamBo2 f, RoShamBo2 s) {
        System.out.println(f.name() + " vs " + s.name() + ": " +  f.compete(s).name());
    }

    public static void play(int size) {
        for (int i = 0; i < size; i++) {
            match(randomOne(), randomOne());
        }
    }
}

public class MutiDispatch {
    public static void main(String[] args) {
        RoShamBo2.play(20);
    }
}

