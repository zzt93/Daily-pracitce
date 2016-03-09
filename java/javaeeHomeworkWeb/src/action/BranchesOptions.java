package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Branch;
import remote.JNDIFactory;
import service.BranchService;
import vo.JTableOption;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzt on 3/8/16.
 * <p>
 * Usage:
 */
public class BranchesOptions extends ActionSupport {

    private ArrayList<JTableOption> options = new ArrayList<>();
    private String result;

    public ArrayList<JTableOption> getOptions() {
        return options;
    }

    public String getResult() {
        return result;
    }

    public String branches() throws Exception {
        BranchService branchService =
                (BranchService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//BranchEJB!service.BranchService");
        assert branchService != null;
        ArrayList<Branch> branches = branchService.allBranch();
        for (Branch branch : branches) {
            options.add(new JTableOption("" + branch.getBid(), branch.getAddr()));
        }
        result = JTableHelper.OK;
        return SUCCESS;
    }
}
