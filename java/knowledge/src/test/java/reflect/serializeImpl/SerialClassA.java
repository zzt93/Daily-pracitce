package reflect.serializeImpl;

import java.io.Serializable;

/**
 * Created by zzt on 17/3/31.
 */
public class SerialClassA implements Serializable {

    private int a = 1;
    private SerialB serialB, serialB2;

    public SerialClassA() {
        serialB = new SerialB("first", this);
        serialB2 = new SerialB("second", this);
    }

    private static class SerialB implements Serializable {

        private String str;
        private int i = 2;
        private transient char c = 'c';
        private SerialClassA a;

        public SerialB(String str, SerialClassA a) {
            this.str = str;
            this.a = a;
        }
    }
}

