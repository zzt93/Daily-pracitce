package hw2l;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Created by zzt on 3/16/15.
 */
public class FilterText extends JTextField {
    private static final int ALL = -1;
    TableRowSorter<TableModel> sorter;
    private int index;

    public FilterText(TableRowSorter<TableModel> tableRowSorter, int index) {
        this.index = index;
        addListener();
        sorter = tableRowSorter;
    }

    public FilterText(TableRowSorter<TableModel> tableRowSorter) {
        this(tableRowSorter, ALL);
    }

    private void addListener() {
        //Whenever filterText changes, invoke newFilter.
        this.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                }
        );
    }

    private void newFilter() {

        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            if (index == ALL) {

            }
            if (index == ALL) {
                rf = RowFilter.regexFilter(getText());
            } else {
                rf = RowFilter.regexFilter(getText(), index);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
