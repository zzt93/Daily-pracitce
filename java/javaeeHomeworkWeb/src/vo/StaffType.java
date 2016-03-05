package mis;

/**
 * Created by zzt on 2/26/16.
 * <p>
 * Usage:
 */
public enum StaffType {
    MANAGER("manager", "ManagerSession"),
    WAITER0("main branch waiter", "Waiter0Session"),
    WAITER1("other branch waiter", "OtherWaiterSession"),
    UNKNOWN("input", ""),
    ;

    private final String name;
    private final String action;

    public String getName() {
        return name;
    }

    StaffType(String s, String s1) {
        this.name = s;
        this.action = s1;
    }

    public String getAction() {
        return action;
    }

    public static StaffType whatType(String des) throws Exception {
        for (StaffType staffType : values()) {
            if (staffType.getName().equals(des)) {
                return staffType;
            }
        }
        throw new Exception("no such type");
    }
}
