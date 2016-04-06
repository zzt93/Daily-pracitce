package competition.practice.round1A2008.qulification;

/**
 * Created by zzt on 4/11/15.
 * <p/>
 * Description:
 */
public class Chars {
    String f;
    String s;

    public Chars(String f, String s) {
        this.f = f;
        this.s = s;
    }

    @Override
    public int hashCode() {
        return f.charAt(f.length()-1) * 10 + s.charAt(s.length()-1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Chars.class) {
            throw new IllegalArgumentException();
        }
        Chars t = ((Chars) obj);
        return (f.equals(t.f) && s.equals(t.s));
    }
}
