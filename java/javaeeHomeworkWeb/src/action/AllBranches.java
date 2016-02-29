package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Branch;
import remote.JNDIFactory;
import service.BranchService;

import java.util.ArrayList;

/**
 * Created by zzt on 2/25/16.
 * <p>
 * Usage:
 */
public class AllBranches extends ActionSupport {

    private ArrayList<Branch> branches;

    public AllBranches() {
        BranchService branchService =
                (BranchService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//BranchEJB!service.BranchService");
        assert branchService != null;
        branches = branchService.allBranch();
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    @Override
    public String execute() throws Exception {
        if (branches == null) {
            return ERROR;
        }
        return SUCCESS;
    }
}
