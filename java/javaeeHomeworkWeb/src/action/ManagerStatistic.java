package action;

import com.opensymphony.xwork2.ActionSupport;
import mis.Gender;
import remote.JNDIFactory;
import service.AccountService;
import vo.ChartData;
import vo.DataSet;

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

    }

    private void prepareBranchChart() {

    }


    public String getColor(int i) {
        return "rgba(" + (101 + i * 50) +
                ",220," + (220 - i * 50) +
                ",0.5)";
    }

    public String prepareAgeChart() throws Exception {
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
        HashMap<Byte, long[]> byteHashMap = accountService.accountCountByGenderAndAge(ages);
        Gender[] genders = Gender.values();
        DataSet[] dataSets = new DataSet[genders.length];
        for (int j = 0; j < dataSets.length; j++) {
            Gender gender = genders[j];
            dataSets[j] = new DataSet(gender.getDes(), getColor(j), byteHashMap.get((byte) gender.ordinal()));
        }
        ageChartData = new ChartData(ageLabels, dataSets);
        return "success";
    }
}
