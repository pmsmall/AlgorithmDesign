package com.common.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.Document;

public class MyTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5396790100819701399L;

	private Color borderColor = Color.black;

	private Insets inset = new Insets(4, 4, 4, 4);

	private float borderWidth = 1;

	/**
	 * Constructs a new TextArea. A default model is set, the initial string is
	 * null, and rows/columns are set to 0.
	 */
	public MyTextArea() {
		this(null, null, 0, 0);
	}

	/**
	 * Constructs a new TextArea with the specified text displayed. A default
	 * model is created and rows/columns are set to 0.
	 *
	 * @param text
	 *            the text to be displayed, or null
	 */
	public MyTextArea(String text) {
		this(null, text, 0, 0);
	}

	/**
	 * Constructs a new empty TextArea with the specified number of rows and
	 * columns. A default model is created, and the initial string is null.
	 *
	 * @param rows
	 *            the number of rows &gt;= 0
	 * @param columns
	 *            the number of columns &gt;= 0
	 * @exception IllegalArgumentException
	 *                if the rows or columns arguments are negative.
	 */
	public MyTextArea(int rows, int columns) {
		this(null, null, rows, columns);
	}

	/**
	 * Constructs a new TextArea with the specified text and number of rows and
	 * columns. A default model is created.
	 *
	 * @param text
	 *            the text to be displayed, or null
	 * @param rows
	 *            the number of rows &gt;= 0
	 * @param columns
	 *            the number of columns &gt;= 0
	 * @exception IllegalArgumentException
	 *                if the rows or columns arguments are negative.
	 */
	public MyTextArea(String text, int rows, int columns) {
		this(null, text, rows, columns);
	}

	/**
	 * Constructs a new MyTextArea with the given document model, and defaults
	 * for all of the other arguments (null, 0, 0).
	 *
	 * @param doc
	 *            the model to use
	 */
	public MyTextArea(Document doc) {
		this(doc, null, 0, 0);
	}

	/**
	 * Constructs a new MyTextArea with the specified number of rows and
	 * columns, and the given model. All of the constructors feed through this
	 * constructor.
	 *
	 * @param doc
	 *            the model to use, or create a default one if null
	 * @param text
	 *            the text to be displayed, null if none
	 * @param rows
	 *            the number of rows &gt;= 0
	 * @param columns
	 *            the number of columns &gt;= 0
	 * @exception IllegalArgumentException
	 *                if the rows or columns arguments are negative.
	 */
	public MyTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		init();
	}

	private void init() {
		setBorder(new DefaultBorder());
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Insets getInset() {
		return inset;
	}

	public void setInset(Insets inset) {
		this.inset = inset;
	}

	@Override
	public void setBorder(Border border) {
		super.setBorder(border);
		if (border == null) {
			inset = null;
			borderWidth = 0;
		}
	}

	class DefaultBorder implements Border {
		private Color color;

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D gg = (Graphics2D) g.create();
			gg.setStroke(new BasicStroke(borderWidth));
			gg.setColor(borderColor);
			gg.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			// TODO Auto-generated method stub
			return inset;
		}

		@Override
		public boolean isBorderOpaque() {
			// TODO Auto-generated method stub
			return true;
		}

		public void setColor(Color c) {
			color = c;
		}

		public Color getColor() {
			return color;
		}
	}

}
