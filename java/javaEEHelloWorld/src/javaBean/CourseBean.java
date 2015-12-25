package javaBean;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class CourseBean {
    private final int cid;
    private final String cname;

    public CourseBean(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    @Override
    public String toString() {
        return "CourseBean{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
