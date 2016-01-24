package pipeFilter;

/**
 * Created by zzt on 1/24/16.
 * <p>
 * Usage:
 */
public class SimpleStringMessage implements StringMessage {
    private String mes;

    public SimpleStringMessage(String mes) {
        this.mes = mes;
    }

    @Override
    public String getMessage() {
        return mes;
    }
}
