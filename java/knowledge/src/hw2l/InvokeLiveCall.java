package hw2l;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by zzt on 5/28/15.
 * <p>
 * Description:
 */
public class InvokeLiveCall implements Callable<ArrayList<String>> {
    private int period;

    public InvokeLiveCall(int period) {
        this.period = period;
    }

    @Override
    public ArrayList<String> call() throws Exception {

        ProcessBuilder pb = new ProcessBuilder("python", "Live.py", "" + period);
        Process p = pb.start();
//            File log = new File("test");
//            pb.redirectErrorStream(true);
//            pb.redirectOutput(ProcessBuilder.Redirect.to(log));
//            pb.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //System.out.println(p.waitFor());
        String line;

        ArrayList<String> lines = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            lines.add(line);
        }
        return lines;
    }
}
