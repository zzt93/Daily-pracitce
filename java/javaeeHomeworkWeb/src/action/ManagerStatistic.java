package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Branch;
import mis.*;
import remote.JNDIFactory;
import service.AccountService;
import service.BranchService;
import service.ReserveService;
import vo.ChartData;
import vo.DataSet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzt on 3/5/16.
 * <p>
 * Usage:
 */
public class ManagerStatistic extends ActionSupport {

    private ChartData ageChartData;

    public ChartData getAgeChartData() {
        return ageChartData;
    }

    private ChartData profitChartData;

    public ChartData getProfitChartData() {
        return profitChartData;
    }

    private ChartData dessertChartData;

    public ChartData getDessertChartData() {
        return dessertChartData;
    }

    private void prepareDessertChart() {
        ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
        assert reserveService != null;
        ChartDataWrapper chartDataWrapper = reserveService.saleSumByDessert();
        String[] dessertLabels = new String[Default.CAKE_ORDRE_LIMIT];
        int[] labels = chartDataWrapper.getLabels();
        for (int i = 0; i < labels.length; i++) {
            dessertLabels[i] = "dessert " + labels[i];
        }

        HashMap<Integer, double[]> hashMap = chartDataWrapper.getRes();
        SaleStatisticType[] values = SaleStatisticType.values();
        DataSet[] dataSets = new DataSet[values.length];
        for (int i = 0; i < dataSets.length; i++) {
            SaleStatisticType value = values[i];
            dataSets[i] = new DataSet<>(value.getDes(), getColor(i), hashMap.get(value.ordinal()));
        }
        dataSets[0].setType("bar");
        dataSets[0].setyAxisID("y-left");
        dataSets[1].setType("line");
        dataSets[1].setyAxisID("y-right");
        dessertChartData = new ChartData(dessertLabels, dataSets);
    }

    private void prepareBranchChart() {
        BranchService branchService =
                (BranchService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//BranchEJB!service.BranchService");
        assert branchService != null;
        ArrayList<Branch> branches = branchService.allBranch();
        String[] branchLabels = new String[branches.size()];
        for (int i = 0; i < branches.size(); i++) {
            branchLabels[i] = "Branch " + branches.get(i).getBid();
        }

        ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
        assert reserveService != null;
        HashMap<Boolean, long[]> countByBranch = reserveService.reserveCountByBranch();
        ReservationState[] values = ReservationState.values();
        DataSet[] dataSets = new DataSet[values.length];
        for (int i = 0; i < dataSets.length; i++) {
            ReservationState value = values[i];
            dataSets[i] = new DataSet<>(value.getDes(), getColor(i), countByBranch.get(value.getState()));
        }
        profitChartData = new ChartData(branchLabels, dataSets);
    }


    private String getColor(int i) {
        return "rgba(" + (101 + i * 50) +
                "," + (220 - i * 50) +
                "," + (220 - i * 50) +
                ",0.5)";
    }

    public String prepareChart() throws Exception {
        prepareAgeChart();
        prepareBranchChart();
        prepareDessertChart();
        return "success";
    }

    private void prepareAgeChart() {
        short[] ages = {0, 1, 21, 41, 61, Short.MAX_VALUE};
        String[] ageLabels = new String[ages.length - 1];
        {
            int i = 0;
            ageLabels[i] = "Age not set";
            for (i = 1; i < ages.length - 2; i++) {
                ageLabels[i] = ages[i] + "-" + (ages[i + 1] - 1);
            }
            ageLabels[i] = ages[i] + "~";
        }
        AccountService accountService =
                (AccountService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.AccountService");
        assert accountService != null;
        HashMap<Byte, long[]> byteHashMap = accountService.accountCountByGenderAndAge(ages);
        Gender[] genders = Gender.values();
        DataSet[] dataSets = new DataSet[genders.length];
        for (int j = 0; j < dataSets.length; j++) {
            Gender gender = genders[j];
            dataSets[j] = new DataSet<>(gender.getDes(), getColor(j), byteHashMap.get((byte) gender.ordinal()));
        }
        ageChartData = new ChartData(ageLabels, dataSets);
    }
}
