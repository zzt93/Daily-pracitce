package mis;

/**
 * Created by zzt on 2/26/16.
 * <p>
 * Usage:
 */
public enum StaffType {
    MANAGER("manager"),
    WAITER0("main branch waiter"),
    WAITER1("other branch waiter"),
    UNKNOWN("input"),
    ;

    private final String des;

    public String getDes() {
        return des;
    }

    StaffType(String s) {
        this.des = s;
    }

    public static StaffType whatType(String des) throws Exception {
        for (StaffType staffType : values()) {
            if (staffType.getDes().equals(des)) {
                return staffType;
            }
        }
        throw new Exception("no such type");
    }
}
