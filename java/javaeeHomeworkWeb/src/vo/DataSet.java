package vo;

/**
 * Created by zzt on 3/6/16.
 * <p>
 * Usage:
 */
public class DataSet {

    private String label;
    private String backgroundColor;
    private long[] data;

    public DataSet(String label, String backgroundColor, long[] data) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public long[] getData() {
        return data;
    }
}
