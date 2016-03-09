package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Account;
import entity.Consume;
import entity.User;
import interceptor.SessionManagement;
import mis.Gender;
import mis.Rank;
import remote.JNDIFactory;
import service.AccountService;
import service.ConsumeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/13/16.
 * <p>
 * Usage:
 * Struts 2 Action objects are instantiated for each request, so there are no thread-safety issues.
 */
public class AccountAction extends ActionSupport {

    User user;
    Account account;
    Consume consume;
    private List<String> gender = new ArrayList<>();
    private String userGender;
    private String userRank;

    public User getUser() {
        return user;
    }

    public List<String> getGender() {
        return gender;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Consume getConsume() {
        return consume;
    }

    public void setConsume(Consume consume) {
        this.consume = consume;
    }

    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public AccountAction() {
    }

    public String account() throws Exception {
        try {
            AccountService accountService =
                    (AccountService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.AccountService");
            int uid = SessionManagement.getUid();
            assert accountService != null;
            user = accountService.getUser(uid);
            account = user.getAccount();
            consume = user.getConsume();

            // handle gender
            for (Gender gender1 : Gender.values()) {
                gender.add(gender1.getDes());
            }
            userGender = Gender.values()[account.getGender()].getDes();

            // handle rank
            userRank = Rank.values()[consume.getRank()].getDes();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    private List<Consume> records;
    private String result;
    private String message;

    private long totalRecordCount;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

    public List<Consume> getRecords() {
        return records;
    }

    public void setRecords(List<Consume> records) {
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

    public void setTotalRecordCount(int totalRecordCount) {
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

    public String userList() throws Exception {
        try {
            ConsumeService consumeService
                    = (ConsumeService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.ConsumeService");
            records = consumeService.userBalanceList();
            totalRecordCount = consumeService.countUserBalanceList();
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    public String UserCardList() throws Exception {
        try {
            ConsumeService consumeService
                    = (ConsumeService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.ConsumeService");
            assert consumeService != null;
            records = consumeService.userCardList();
            totalRecordCount = consumeService.countuserCardList();
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    public String accountDelete() throws Exception {
        try {
            ConsumeService consumeService
                    = (ConsumeService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.ConsumeService");
            assert consumeService != null;
            consumeService.deleteAccount(SessionManagement.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return INPUT;
    }
}

