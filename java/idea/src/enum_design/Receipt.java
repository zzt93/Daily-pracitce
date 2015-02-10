package enum_design;

/**
 * Created by zzt on 2/9/15.
 */
public enum Receipt {
    RepoReceipt() {
        private String operator;
        @Override
        public void show() {

        }
    }, FinaReceipt() {
        @Override
        public void show() {

        }
    }, xxReceipt() {
        @Override
        public void show() {

        }
    };

    String name;
    long num;
    public abstract void show();
}
