package hw2l;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Created by zzt on 3/16/15.
 */
public class InputPanel extends JPanel {
    private TableRowSorter<TableModel> sort;

    private JTextField filterText;
    private JTextField customizedSorter;
    private JTextField statusText;

    public InputPanel(final Sort_table table) {

        setLayout(new SpringLayout());
        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        this.add(l1);
        filterText = new FilterText(table.getTableRowSorter());

        l1.setLabelFor(filterText);
        this.add(filterText);


        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        if (Sorter.DEBUG) {
                            selectionInfo(table);
                        }
                    }
                }
        );

        JLabel l2 = new JLabel("Status:", SwingConstants.TRAILING);
        this.add(l2);
        statusText = new JTextField();
        l2.setLabelFor(statusText);
        this.add(statusText);
        SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);

        sort = table.getTableRowSorter();
    }


    private void selectionInfo(JTable table) {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            //Selection got filtered away.
            statusText.setText("");
        } else {
            int modelRow =
                    table.convertRowIndexToModel(viewRow);
            statusText.setText(
                    String.format("Selected Row in view: %d. " +
                                    "Selected Row in model: %d.",
                            viewRow, modelRow));
        }
    }

}
