package myProblem;

/**
 * Created by zzt on 12/2/17.
 * <p>
 * <h3></h3>
 */
public class ValidHtml {

    public boolean isValid(String s) {
        return isValidTag(s.toCharArray(), 0, s.length());
    }

    private boolean isValidTag(char[] cs, int s, int e) {
        int l = tagName(cs, s, e);
        return l != 0 && isValidContent(cs, s + l + 2, e - l - 3);
    }

    private int tagName(char[] cs, int s, int e) {
        if (s >= e) return 0;

        assert cs[s] == '<' && cs[e - 1] == '>';
        int i = s + 1, ri = e - 1;
        while (cs[i] != '>') {
            if (!Character.isUpperCase(cs[i])) return 0;
            i++;
            ri--;
        }
        int count = i - s - 1;
        if (count > 0 &&
                new String(cs, s + 1, count).equals(new String(cs, ri, count))
                && ri - 2 > i && cs[ri-1] == '/' && cs[ri - 2] == '<') {
            return count;
        }
        return 0;
    }

    private boolean isValidContent(char[] cs, int s, int e) {
        if (s >= e) return true;

        boolean res = true;
        if (res) {
            int i;
            for (i = s; i < e; i++) {
                if (cs[i] == '<') {
                    if (i + 1 < e && cs[i + 1] != '!') break;
                }
            }
            if (i != e) {
                int j;
                for (j = e - 1; j >= s; j--) {
                    if (cs[j] == '>') {
                        if (j - 1 >= s && cs[j - 1] != '-') break;
                    }
                }
                if (j >= s) {
                    res = isValidTag(cs, i, j+1);
                } else {
                    res = false;
                }
            }
        }
        if (res) {
            String tmp = new String(cs, s, e - s);
            int newS = tmp.indexOf('(');
            int newE = tmp.lastIndexOf(')');
            if (newS != -1 && newE != -1) {
                res = isValidParen(cs, s + newS, s + newE + 1);
            } else if (newS * newS < 0) {
                res = false;
            }
        }
        if (res) {
            int i;
            boolean start = false, end = false;
            for (i = s; i < e; i++) {
                if (!start) {
                    if (cs[i] == '<') {
                        if (i + 3 < e && cs[i + 1] == '!' && cs[i + 2] == '-' && cs[i + 3] == '-') {
                            start = true;
                        }
                    }
                } else {
                    if (cs[i] == '-') {
                        if (i + 2 < e && cs[i + 1] == '-' && cs[i + 2] == '>' ) {
                            end = true;
                        }
                    }
                }
            }
            res = start == end;
        }
        return res;
    }

    private boolean isValidParen(char[] cs, int s, int e) {
        return isParenInPair(cs, s, e) && isValidContent(cs, s + 1, e - 1);
    }

    private boolean isParenInPair(char[] cs, int s, int e) {
        return cs[s] == '(' && cs[e - 1] == ')';
    }


    public static void main(String[] args) {
        ValidHtml v = new ValidHtml();
        System.out.println(v.isValid("<A>TEST<BC>HELLO(<test))</BC>TEST</A>"));
        System.out.println(v.isValid("<A></A>"));
        System.out.println(v.isValid("<></>"));
        //        System.out.println(v.isValid(""));
                System.out.println(v.isValid("<A>(HELLO)<B><!--TEST( <b></b> ) --</ < <!---><CD><!-hello--></CD></B></A>"));
                System.out.println(v.isValid("<A>(HELLO)<B><!--TEST( <b></b> ) --</ < <!---><CD><!--hello--></CD></B></A>"));
        System.out.println(v.isValid("<A>(TEST)<DIV()>Hello World!<!--example<div>--></DIV()></A>"));
        System.out.println(v.isValid("<DIV>(Test)<Div>Hello</Div></DIV>"));
        System.out.println(v.isValid("<A><DIVDIVDIVDIV>HELLO<!--(((>>>>--></DIVDIVDIVDIV></A>"));
        System.out.println(v.isValid("<A><!--sdf--></A>"));
        System.out.println(v.isValid("<A><!--sdf--</A>"));
        System.out.println(v.isValid("<A><!-sdf--></A>"));
        System.out.println(v.isValid("<A><!-sdf-></A>"));
        System.out.println(v.isValid("<A><!--sdf-->asd--></A>"));
        System.out.println(v.isValid("<A>asf<B>hjkl</B>sd</A>"));
        System.out.println(v.isValid("<a></a>"));
        System.out.println(v.isValid("<A><DIV>Hello world<A></B></DIV></A>"));
        System.out.println(v.isValid("<DIV>(ab)<!--Hello <bs>(--></DIV>"));
        System.out.println(v.isValid("<aa>"));
    }
}
