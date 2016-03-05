package mis;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public enum CardState {
    LET_ACTIVATE("let to be activated") {
        @Override
        public CardState movePrevious() {
            return null;
        }

        @Override
        public CardState moveNext() {
            return ACTIVATED;
        }
    },
    ACTIVATED("activated") {
        @Override
        public CardState movePrevious() {
            return ACTIVATED;
        }

        @Override
        public CardState moveNext() {
            return SUSPEND;
        }
    },
    SUSPEND("suspended") {
        @Override
        public CardState movePrevious() {
            return ACTIVATED;
        }

        @Override
        public CardState moveNext() {
            return CANCEL;
        }
    },
    CANCEL("canceled") {
        @Override
        public CardState movePrevious() {
            return ACTIVATED;
        }

        @Override
        public CardState moveNext() {
            return CANCEL;
        }
    },;

    private final String des;

    CardState(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public abstract CardState movePrevious();

    public abstract CardState moveNext();

    public static CardState moveNext(byte i) {
        if (i >= CardState.values().length) {
            throw new IllegalArgumentException("invalid card ordinal");
        }
        return CardState.values()[i].moveNext();
    }

    public static CardState movePrevious(byte state) {
        if (state >= CardState.values().length) {
            throw new IllegalArgumentException("invalid card ordinal");
        }
        return CardState.values()[state].movePrevious();
    }
}
