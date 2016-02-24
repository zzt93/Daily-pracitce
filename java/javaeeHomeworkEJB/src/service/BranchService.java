package service;

import entity.Branch;

import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
public interface BranchService {

    void addBranch(String addr);
    void deleteBranch(int bid);
    void updateBranch(Branch branch);
    ArrayList<Branch> allBranch();
}
