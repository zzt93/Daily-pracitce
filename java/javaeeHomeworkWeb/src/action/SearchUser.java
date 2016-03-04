package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Consume;
import mis.Rank;
import remote.JNDIFactory;
import service.AccountService;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class SearchUser extends ActionSupport {
//    TODO change to ajax
//    public String searchUser() throws Exception {
//
//        try {
//            AccountService accountService =
//                    (AccountService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.AccountService");
//            user = accountService.getUser(userId);
//            account = user.getAccount();
//            Consume consume = user.getConsume();
//            userRank = Rank.values()[consume.getRank()].getDes();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ERROR;
//        }
//        return SUCCESS;
//    }
}
