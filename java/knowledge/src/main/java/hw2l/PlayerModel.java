package hw2l;

import javax.swing.table.DefaultTableModel;

/**
 * Created by zzt on 3/8/15.
 * <p>
 * Your model might hold its data in an array, vector, or hash map,
 * or it might get the data from an outside source such as a database.
 * It might even generate the data at execution time.
 * <p>
 * <p>
 * hold it in primitive array to jtable.sort it quickly
 */
public class PlayerModel extends DefaultTableModel {
    /*
    When DefaultTableModel is used with a TableRowSorter this will result in extensive use of toString,
    which for non-String data types is expensive.
    If you use DefaultTableModel with a TableRowSorter
    you are strongly encouraged to override getColumnClass to return the appropriate type.
     */

    public PlayerModel() {
        setDataVector(data, columnNames);
    }

    private String[] columnNames = {"Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
    private Object[][] data = {
            {"Kathy Smith",
                    "Snowboarding", new Integer(5), new Boolean(false)},
            {"John Doe",
                    "Rowing", new Integer(3), new Boolean(true)},
            {"Sue Black",
                    "Knitting", new Integer(2), new Boolean(false)},
            {"Jane White",
                    "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe Brown",
                    "Pool", new Integer(10), new Boolean(false)}
    };

//    public int getColumnCount() {
//        return columnNames.length;
//    }
//
//    public int getRowCount() {
//        return data.length;
//    }

//    public String getColumnName(int col) {
//        return columnNames[col];
//    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     *
     *
     *  DefaultTableModel returns a column class of Object.
     *  When DefaultTableModel is used with a TableRowSorter this will result in extensive use of toString,
     *  which for non-String data types is expensive. If you use DefaultTableModel with a TableRowSorter
     *  you are strongly encouraged to override getColumnClass to return the appropriate type.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }


    public void setValueAt(Object value, int row, int col) {
        if (Sorter.DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

        data[row][col] = value;
        // Normally, one should call fireTableCellUpdated() when
        // a value is changed.  However, doing so in this demo
        // causes a problem with TableSorter.  The tableChanged()
        // call on TableSorter that results from calling
        // fireTableCellUpdated() causes the indices to be regenerated
        // when they shouldn't be.  Ideally, TableSorter should be
        // given a more intelligent tableChanged() implementation,
        // and then the following line can be uncommented.
        // fireTableCellUpdated(row, col);

        if (Sorter.DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
