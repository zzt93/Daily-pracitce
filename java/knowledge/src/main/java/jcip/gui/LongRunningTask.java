package jcip.gui;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 5/6/16.
 * <p>
 * Usage:
 */
public class LongRunningTask {
    private final ExecutorService service = Executors.newCachedThreadPool();
    private final JButton button;

    public LongRunningTask(JButton button) {
        this.button = button;
        button.addActionListener((event) -> {
            button.setEnabled(false);
            service.execute(() -> {
                try {
                    doLongTimeJob();
                } finally {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            button.setEnabled(true);
                        });
                    } catch (InterruptedException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    private void doLongTimeJob() {

    }
}
