package pipeFilter;

import utility.MyIn;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class Input implements Runnable{
    MyIn in;

    public Input() throws FileNotFoundException {
        in = new MyIn("test");
    }


    @Override
    public void run() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
        }
    }
}
