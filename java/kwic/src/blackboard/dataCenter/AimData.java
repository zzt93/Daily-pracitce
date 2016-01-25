package blackboard.dataCenter;

import java.util.ArrayList;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class AimData {

    private ArrayList<String> strings = new ArrayList<>();

    public AimData(String input) {
        strings.add(input);
    }

    public AimData(ArrayList<String> strings) {
        this.strings = strings;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }


}
