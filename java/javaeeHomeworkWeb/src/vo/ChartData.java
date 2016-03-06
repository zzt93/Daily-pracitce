package vo;

/**
 * Created by zzt on 3/6/16.
 * <p>
 * Usage:
 */
public class ChartData {

    private String[] labels;
    private DataSet[] datasets;

    public ChartData(String[] labels, DataSet[] dataSets) {
        this.labels = labels;
        this.datasets = dataSets;
    }

    public String[] getLabels() {
        return labels;
    }

    public DataSet[] getDatasets() {
        return datasets;
    }
}
