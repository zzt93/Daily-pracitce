package mis;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
public enum Gender {
    NOT("未填写"),
    MALE("男"),
    FEMALE("女")
    ;

    private final String des;

    Gender(String des) {
        this.des = des;
    }
}
