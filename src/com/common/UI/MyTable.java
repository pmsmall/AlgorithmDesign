package com.common.UI;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class MyTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4504324300300978972L;

	/**
	 * Constructs a default <code>MyTable</code> that is initialized with a
	 * default data model, a default column model, and a default selection
	 * model.
	 *
	 * @see #createDefaultDataModel
	 * @see #createDefaultColumnModel
	 * @see #createDefaultSelectionModel
	 */
	public MyTable() {
		this(null, null, null);
	}

	/**
	 * Constructs a <code>MyTable</code> that is initialized with
	 * <code>dm</code> as the data model, a default column model, and a default
	 * selection model.
	 *
	 * @param dm
	 *            the data model for the table
	 * @see #createDefaultColumnModel
	 * @see #createDefaultSelectionModel
	 */
	public MyTable(TableModel dm) {
		this(dm, null, null);
	}

	/**
	 * Constructs a <code>MyTable</code> that is initialized with
	 * <code>dm</code> as the data model, <code>cm</code> as the column model,
	 * and a default selection model.
	 *
	 * @param dm
	 *            the data model for the table
	 * @param cm
	 *            the column model for the table
	 * @see #createDefaultSelectionModel
	 */
	public MyTable(TableModel dm, TableColumnModel cm) {
		this(dm, cm, null);
	}

	/**
	 * Constructs a <code>MyTable</code> that is initialized with
	 * <code>dm</code> as the data model, <code>cm</code> as the column model,
	 * and <code>sm</code> as the selection model. If any of the parameters are
	 * <code>null</code> this method will initialize the table with the
	 * corresponding default model. The <code>autoCreateColumnsFromModel</code>
	 * flag is set to false if <code>cm</code> is non-null, otherwise it is set
	 * to true and the column model is populated with suitable
	 * <code>TableColumns</code> for the columns in <code>dm</code>.
	 *
	 * @param dm
	 *            the data model for the table
	 * @param cm
	 *            the column model for the table
	 * @param sm
	 *            the row selection model for the table
	 * @see #createDefaultDataModel
	 * @see #createDefaultColumnModel
	 * @see #createDefaultSelectionModel
	 */
	public MyTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
	}

	/**
	 * Constructs a <code>MyTable</code> with <code>numRows</code> and
	 * <code>numColumns</code> of empty cells using
	 * <code>DefaultTableModel</code>. The columns will have names of the form
	 * "A", "B", "C", etc.
	 *
	 * @param numRows
	 *            the number of rows the table holds
	 * @param numColumns
	 *            the number of columns the table holds
	 * @see javax.swing.table.DefaultTableModel
	 */
	public MyTable(int numRows, int numColumns) {
		this(new DefaultTableModel(numRows, numColumns));
	}

	/**
	 * Constructs a <code>MyTable</code> to display the values in the
	 * <code>Vector</code> of <code>Vectors</code>, <code>rowData</code>, with
	 * column names, <code>columnNames</code>. The <code>Vectors</code>
	 * contained in <code>rowData</code> should contain the values for that row.
	 * In other words, the value of the cell at row 1, column 5 can be obtained
	 * with the following code:
	 *
	 * <pre>
	 * ((Vector) rowData.elementAt(1)).elementAt(5);
	 * </pre>
	 * <p>
	 * 
	 * @param rowData
	 *            the data for the new table
	 * @param columnNames
	 *            names of each column
	 */
	public MyTable(Vector<?> rowData, Vector<?> columnNames) {
		this(new DefaultTableModel(rowData, columnNames));
	}

	/**
	 * Constructs a <code>MyTable</code> to display the values in the two
	 * dimensional array, <code>rowData</code>, with column names,
	 * <code>columnNames</code>. <code>rowData</code> is an array of rows, so
	 * the value of the cell at row 1, column 5 can be obtained with the
	 * following code:
	 *
	 * <pre>
	 *  rowData[1][5];
	 * </pre>
	 * <p>
	 * All rows must be of the same length as <code>columnNames</code>.
	 * <p>
	 * 
	 * @param rowData
	 *            the data for the new table
	 * @param columnNames
	 *            names of each column
	 */
	public MyTable(final Object[][] rowData, final Object[] columnNames) {
		super(rowData, columnNames);
	}
}
