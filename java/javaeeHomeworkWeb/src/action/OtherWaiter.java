package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import mis.PayType;
import mis.Rank;
import remote.JNDIFactory;
import service.AccountService;
import service.ConsumeService;
import service.ReserveService;
import service.StaffInfoService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/20/16.
 * <p>
 * Usage:
 */
public class OtherWaiter extends ActionSupport {

    // search parameter
    private int userId;

    private User user;
    private Account account;
    private int rdid;
    private String userRank;

    public String searchUser() throws Exception {

        try {
            AccountService accountService =
                    (AccountService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.AccountService");
            user = accountService.getUser(userId);
            account = user.getAccount();
            Consume consume = user.getConsume();
            userRank = Rank.values()[consume.getRank()].getDes();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    private double money;
    private String type;

    public User getUser() {
        return user;
    }

    public Account getAccount() {
        return account;
    }

    public double getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRdid() {
        return rdid;
    }

    public void setRdid(int rdid) {
        this.rdid = rdid;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String payMoney() throws Exception {
        PayType payType = PayType.valueOf(type);
        try {
            ConsumeService consumeService =
                    (ConsumeService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.ConsumeService");
            payType.pay(SessionManagement.getUid(), money, consumeService);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

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

    public String branchUserReserveDetailList() throws Exception {
        try {
            StaffInfoService staffInfoService =
                    (StaffInfoService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded/StaffEJB!service.StaffInfoService");
            HttpSession session = SessionManagement.getSession();
            int sid = (int) session.getAttribute(InnerLogin.SID);
            assert staffInfoService != null;
            Staff staff = staffInfoService.getStaff(sid);
            ReserveService reserveService =
                    (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
            assert reserveService != null;
            Reserve reserve = reserveService.branchUserReserveDetail(staff.getBranch().getBid(), userId, LocalDateTime.now().toString());
            if (reserve != null) {
                records = reserveService.reserveDetailGet(reserve.getRid());
            } else {
                records = new ArrayList<>();
            }
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
        }
        return SUCCESS;
    }

    public String branchUserReserveDetailDelete() throws Exception {
        try {
            ReserveService reserveService =
                    (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
            assert reserveService != null;
            reserveService.reserveDetailDelete(rdid);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
        }
        return SUCCESS;
    }
}
