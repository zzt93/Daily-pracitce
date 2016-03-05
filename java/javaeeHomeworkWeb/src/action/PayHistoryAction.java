package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Reserve;
import entity.ReserveDetail;
import interceptor.SessionManagement;
import remote.JNDIFactory;
import service.ReserveService;
import vo.PayVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class PayHistoryAction extends ActionSupport {

    private final ReserveService reserveService;
    // for table response
    private List<PayVO> records = new ArrayList<>();
    private String result;
    private String message;

    private long totalRecordCount;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

    private int rid;

    public List<PayVO> getRecords() {
        return records;
    }

    public void setRecords(List<PayVO> records) {
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

    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
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

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public PayHistoryAction() {
        reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");

    }

    public String payHistoryDelete() throws Exception {
        reserveService.reserveDelete(rid);
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String payHistoryList() throws Exception {
        HttpSession session = SessionManagement.getSession();
        Integer uid = (Integer) session.getAttribute(UserLogin.UID);
        ArrayList<Reserve> reserves = reserveService.userPayment(uid, jtStartIndex, jtPageSize);
        for (Reserve reserve : reserves) {
            List<ReserveDetail> reserveDetails = reserveService.reserveDetailGet(reserve.getRid());
            double sum = reserveDetails.stream().mapToDouble(ReserveDetail::toPayReservation).sum();
            records.add(new PayVO(reserve, sum));
        }
        totalRecordCount = reserveService.countUserPayment(uid);
        result = JTableHelper.OK;
        return SUCCESS;
    }
}
