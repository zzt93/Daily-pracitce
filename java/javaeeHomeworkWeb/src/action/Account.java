package action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/13/16.
 * <p>
 * Usage:
 * Struts 2 Action objects are instantiated for each request, so there are no thread-safety issues.
 */
public class Account extends ActionSupport {

    public static final String OK = "OK";
    //    User user;
    //    Account account;
    //    ArrayList<Reserve> reserves;
    //    ArrayList<Reserve> history;
    private ArrayList<StudentBean> students;

    // for json response
    private List<StudentBean> records;
    private String result;
    private String message;
    private int totalRecordCount;

    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

    public Account() {
        students = new ArrayList<>();
        students.add(new StudentBean("o7bb002", "Gokul", "ECE", 4));
        students.add(new StudentBean("o7bc074", "Muthu Vijay", "CSE", 6));
        students.add(new StudentBean("o7bb040", "Jaya Prakash", "ECE", 10));
        students.add(new StudentBean("o7bc055", "Mohiadeen", "CSE", 7));
        students.add(new StudentBean("o7bd047", "HariPriya", "IT", 1));
        students.add(new StudentBean("o7bd024", "Pavithra", "IT", 3));
        students.add(new StudentBean("o7bb009", "Aswin", "ECE", 8));
        students.add(new StudentBean("o7ba029", "Sharmila", "IT", 11));
        students.add(new StudentBean("o7ba027", "Nilafar", "IT", 2));
        students.add(new StudentBean("o7bd081", "Dinesh Babu", "MECH", 13));
        students.add(new StudentBean("o7ba062", "Lourde", "MECH", 9));
        students.add(new StudentBean("o7bc079", "Nisha", "CSC", 5));
        students.add(new StudentBean("o7bb039", "Guru Prasad", "MECH", 12));
        students.add(new StudentBean("o7bc033", "Gowtham Raj", "CSE", 15));
        students.add(new StudentBean("o7bb039", "Ibrahim Sha", "ECE", 14));
        students.add(new StudentBean("o7bd081", "Dinesh Babu", "MECH", 16));
    }

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }

    public ArrayList<StudentBean> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<StudentBean> students) {
        this.students = students;
    }

    public String reserveList() throws Exception {
        result = OK;
        message = "this fine";
        totalRecordCount = 16;
        records = students.subList(jtStartIndex, jtStartIndex + jtPageSize);
        return execute();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StudentBean> getRecords() {
        return records;
    }

    public void setRecords(List<StudentBean> records) {
        this.records = records;
    }

    public int getJtStartIndex() {
        return jtStartIndex;
    }

    public void setJtStartIndex(int jtStartIndex) {
        this.jtStartIndex = jtStartIndex;
    }

    public int getJtPageSize() {
        return jtPageSize;
    }

    public void setJtPageSize(int jtPageSize) {
        this.jtPageSize = jtPageSize;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public String reserveDelete() throws Exception {
        result = OK;
        return SUCCESS;
    }

    public String payDelete() throws Exception {
        result = OK;
        return SUCCESS;
    }

    public String payList() throws Exception {
        return reserveList();
    }

}

