package vo;

/**
 * Created by zzt on 3/6/16.
 * <p>
 * Usage:
 */
public class DataSet<T> {

    private String label;
    private String backgroundColor;
    private T data;
    private String yAxisID = "";
    private String type = "";

    public DataSet(String label, String backgroundColor, T data) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.data = data;
    }

    public DataSet(String label, String backgroundColor, T data, String yAxisID, String type) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.data = data;
        this.yAxisID = yAxisID;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public T getData() {
        return data;
    }

    public void setyAxisID(String yAxisID) {
        this.yAxisID = yAxisID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getyAxisID() {
        return yAxisID;
    }

    public String getType() {
        return type;
    }
}
