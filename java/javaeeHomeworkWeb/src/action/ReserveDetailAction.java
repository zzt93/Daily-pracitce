package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Reserve;
import entity.ReserveDetail;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/25/16.
 * <p>
 * Usage:
 */
public class ReserveDetailAction extends ActionSupport {

    public static final String RESERVE_DETAIL = "reserveDetail";
    // for table response
    private List<ReserveDetail> records;
    private String result;
    private String message;

    public List<ReserveDetail> getRecords() {
        return records;
    }

    public void setRecords(List<ReserveDetail> records) {
        this.records = records;
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

    private int rid;

    public String orderList() throws Exception {
        return SUCCESS;
    }

    public String orderAdd() throws Exception {
        HttpSession session = SessionManagement.getSession();
        ArrayList<ReserveDetail> details =
                (ArrayList<ReserveDetail>) session.getAttribute(RESERVE_DETAIL);
        if (details == null) {
            details = new ArrayList<>();
        }
        details.add(new ReserveDetail());
        return SUCCESS;
    }

    public String orderDelete() throws Exception {
        return SUCCESS;
    }
    public String orderUpdate() throws Exception {
        return SUCCESS;
    }
}
