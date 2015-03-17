package jtable;

/**
 * Created by zzt on 3/8/15.
 */

import javax.swing.*;
import javax.swing.table.TableModel;

public class Sorter extends JPanel {
    public static boolean DEBUG = true;

    public Sorter() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        TableModel tableModel = new PlayerModel();
        Sort_table table = new Sort_table(tableModel);
        InputPanel form = new InputPanel(table);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        add(form);
    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableSort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Sorter newContentPane = new Sorter();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

