package vo;

/**
 * Created by zzt on 3/8/16.
 * <p>
 * Usage:
 */
public class JTableOption {

    private String displayText;
    private String value;

    public JTableOption(String value, String displayText) {
        this.displayText = displayText;
        this.value = value;
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getValue() {
        return value;
    }
}
