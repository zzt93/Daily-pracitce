package reflect.serializeImpl;

import com.google.common.base.Preconditions;

/**
 * Created by zzt on 4/4/17.
 * <p>
 * <h3></h3>
 */
public class Choice<F, S> {

    private F f;
    private S s;

    public void setFirst(F f) {
        this.f = f;
        setSecond(null);
    }

    public void setSecond(S s) {
        this.s = s;
        setFirst(null);
    }

    public F getFirst() {
        Preconditions.checkNotNull(f);
        return f;
    }

    public S getSecond() {
        Preconditions.checkNotNull(s);
        return s;
    }

    public boolean isFirst() {
        return f != null;
    }
}
