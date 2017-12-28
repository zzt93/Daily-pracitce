package competition.leetcode.week25;

/**
 * Created by zzt on 3/26/17.
 * <p>
 * <h3></h3>
 */
public class ComplexNumMul {

    private class ComplexNum {
        private int a;
        private int b;

        private ComplexNum(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public ComplexNum(String s) {
            int i = s.indexOf('+');
            a = Integer.parseInt(s.substring(0, i));
            b = Integer.parseInt(s.substring(i + 1, s.length() - 1));
        }

        public ComplexNum multiply(ComplexNum num) {
            int ra = a * num.a - b * num.b;
            int rb = a * num.b + b * num.a;
            a = ra;
            b = rb;
            return this;
        }

        @Override
        public String toString() {
            return "" + a + "+" + b + "i";
        }
    }

    public String complexNumberMultiply(String a, String b) {
        return new ComplexNum(a).multiply(new ComplexNum(b)).toString();
    }

    public static void main(String[] args) {
        ComplexNumMul complexNumMul = new ComplexNumMul();
        System.out.println(complexNumMul.complexNumberMultiply("1+0i", "1+0i"));
    }
}
