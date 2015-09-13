import java.util.ArrayList;

/**
 * Created by zzt on 9/5/15.
 * <p>
 * Description:
 */
public class practice_shortcut {
    public static void main(String[] args) {
        System.out.format("the turth is %s\n", "turth ");
        System.out.format("the turth is %s\n", "th is ");
        System.out.format("the turth is %s\n", " is %s");
        final ArrayList<String> strings = new ArrayList<>();
        strings.stream().filter(s ->  s.equals("1")).count();
    }
}
