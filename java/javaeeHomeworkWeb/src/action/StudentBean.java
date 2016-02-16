package action;

/**
 * Created by zzt on 2/16/16.
 * <p>
 * Usage:
 * For test display-tag
 * @see Account
 */
public class StudentBean {
    private String rollNo;
    private String studentName;
    private String department;
    private int rank;

    public StudentBean(String rollNo, String studentName, String department, int rank) {
        this.rollNo = rollNo;
        this.studentName = studentName;
        this.department = department;
        this.rank = rank;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
