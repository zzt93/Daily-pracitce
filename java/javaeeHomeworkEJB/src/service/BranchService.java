package service;

import entity.Branch;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 *
 * Add remote annotation and jboss will deploy it by JNDI,
 * otherwise, it will not.
 */
@Remote
public interface BranchService {

    Branch addBranch(String addr);
    void deleteBranch(int bid);
    Branch updateBranch(int bid, String addr);
    ArrayList<Branch> allBranch();
}
