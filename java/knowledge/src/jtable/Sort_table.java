package jtable;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zzt on 3/9/15.
 */
public class Sort_table extends JTable {
    public TableRowSorter<TableModel> getTableRowSorter() {
        return sorter;
    }

    TableRowSorter<TableModel> sorter;

    public Sort_table(TableModel tableModel) {
        super(tableModel);
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
        // TODO: may need change
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        sorter = new TableRowSorter<TableModel>(tableModel);
        setRowSorter(sorter);

        // add specific comparator for some column
        sorter.setComparator(0, ComparatorFactory.lastName());

        // specify the sort order and sort precedence for columns
        ArrayList <SortKey> sortKeys
                = new ArrayList<SortKey>();
        sortKeys.add(new SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }


}
