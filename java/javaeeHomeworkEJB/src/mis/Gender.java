package mis;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
public enum Gender {
    NOT("Not select", "未填写"),
    MALE("Male", "男"),
    FEMALE("Female", "女");

    private final String des;
    private final String des2;

    public String getDes() {
        return des;
    }

    public String getDes2() {
        return des2;
    }

    Gender(String des, String des2) {
        this.des = des;
        this.des2 = des2;
    }
}
