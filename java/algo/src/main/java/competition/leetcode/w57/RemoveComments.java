package competition.leetcode.w57;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 11/5/17.
 * <p>
 * <h3></h3>
 */
public class RemoveComments {
    private static final int NORMAL = 0;
    private static final int LINE = 1;
    private static final int BLOCK = 2;

    public List<String> removeComments(String[] source) {
        String collect = Arrays.stream(source).collect(Collectors.joining("\n"));
        char[] cs = collect.toCharArray();
        int l = cs.length;
        StringBuilder res = new StringBuilder(l);
        int status = NORMAL;
        for (int i = 0; i < l; i++) {
            char c = cs[i];
            switch (status) {
                case NORMAL:
                    if (i + 1 < l) {
                        char c2 = cs[i + 1];
                        if (c == '/') {
                            if (c2 == '/') {
                                status = LINE;
                                i++;
                            } else if (c2 == '*') {
                                status = BLOCK;
                                i++;
                            } else {
                                res.append(c);
                            }
                        } else {
                            res.append(c);
                        }
                    } else {
                        res.append(c);
                    }
                    break;
                case LINE:
                    if (c == '\n') {
                        res.append(c);
                        status = NORMAL;
                    }
                    break;
                case BLOCK:
                    if (i + 1 < l) {
                        char c2 = cs[i + 1];
                        if (c == '*' && c2 == '/') {
                            i++;
                            status = NORMAL;
                        }
                    } else {
                        assert false;
                    }
                    break;
            }
        }
        return Arrays.stream(res.toString().split("\n")).filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        RemoveComments r = new RemoveComments();
        System.out.println(r.removeComments(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"}));
        System.out.println(r.removeComments(new String[]{"a/*/b//*c", "blank", "d/*/e*//f"}));
        System.out.println(r.removeComments(new String[]{"struct Node{", "    /*/ declare " +
                "members;/**/", "    int size;", "    /**/int val;", "};"}));
        System.out.println(r.removeComments(new String[]{"main() {", "   func(1);", "   /** / " +
                "more comments here", "   float f = 2.0", "   f += f;", "   cout << f; */", "}"}));
        System.out.println(r.removeComments(new String[]{"void func(int k) {", "// this function " +
                "does nothing /*", "   k = k*2/4;", "   k = k/2;*/", "}"}));
        System.out.println(r.removeComments(new String[]{"a/*comment", "line", "more_comment*/b"}));
        System.out.println(r.removeComments(new String[]{"/*Test program */", "int main()", "{ ",
                "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline " +
                " ", "   comment for ", "   testing */", "a = b + c;", "}"}));
        System.out.println(r.removeComments(new String[]{"//*Test program */", "int main()", "{ " +
                "", "  // variable declaration ", "int a, b, c;", "/* This is a test*", "   " +
                "multiline  ", "   comment for /", " //  testing /*/", "a = b * c;", "a * b /",
                "c;", "}"}));
        System.out.println(r.removeComments(new String[]{}));
    }
}
