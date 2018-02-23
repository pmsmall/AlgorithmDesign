package com.common.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;

import com.common.UI.theme.TextFieldTheme;
import com.common.arrayListTools.MyArrayList;
import com.common.arrayListTools.MyStringTool;

public class MyTextField extends JTextField {

	private String hint;
	private Color fontColor, hintColor = Color.GRAY;
	private TextListener l;
	private boolean isHintVisible = false;

	private static final int KEY = 0;
	private static final int ACCOUNT = 1;
	private static final int TEXT = 2;
	private int type = TEXT;
	private MyArrayList<Character> keyByChars;
	private String key;
	private TextFieldTheme theme;

	private Color borderColor = Color.black;

	private Insets inset = new Insets(4, 4, 4, 4);

	private float borderWidth = 1;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617579948193800249L;

	/**
	 * Constructs a new <code>MyTextField</code>. A default model is created,
	 * the initial string is <code>null</code>, and the number of columns is set
	 * to 0.
	 */
	public MyTextField() {
		this(null, null, 0);
	}

	/**
	 * Constructs a new <code>MyTextField</code> initialized with the specified
	 * text. A default model is created and the number of columns is 0.
	 *
	 * @param text
	 *            the text to be displayed, or <code>null</code>
	 */
	public MyTextField(String text) {
		this(null, text, 0);
	}

	/**
	 * Constructs a new empty <code>MyTextField</code> with the specified number
	 * of columns. A default model is created and the initial string is set to
	 * <code>null</code>.
	 *
	 * @param columns
	 *            the number of columns to use to calculate the preferred width;
	 *            if columns is set to zero, the preferred width will be
	 *            whatever naturally results from the component implementation
	 */
	public MyTextField(int columns) {
		this(null, null, columns);
	}

	/**
	 * Constructs a new <code>MyTextField</code> initialized with the specified
	 * text and columns. A default model is created.
	 *
	 * @param text
	 *            the text to be displayed, or <code>null</code>
	 * @param columns
	 *            the number of columns to use to calculate the preferred width;
	 *            if columns is set to zero, the preferred width will be
	 *            whatever naturally results from the component implementation
	 */
	public MyTextField(String text, int columns) {
		this(null, text, columns);
	}

	/**
	 * Constructs a new <code>MyTextField</code> that uses the given text
	 * storage model and the given number of columns. This is the constructor
	 * through which the other constructors feed. If the document is
	 * <code>null</code>, a default model is created.
	 *
	 * @param doc
	 *            the text storage to use; if this is <code>null</code>, a
	 *            default will be provided by calling the
	 *            <code>createDefaultModel</code> method
	 * @param text
	 *            the initial string to display, or <code>null</code>
	 * @param columns
	 *            the number of columns to use to calculate the preferred width
	 *            &gt;= 0; if <code>columns</code> is set to zero, the preferred
	 *            width will be whatever naturally results from the component
	 *            implementation
	 * @exception IllegalArgumentException
	 *                if <code>columns</code> &lt; 0
	 */
	public MyTextField(Document doc, String text, int columns) {
		this(doc, text, columns, null);
	}

	/**
	 * Constructs a new <code>MyTextField</code> that uses the given text
	 * storage model and the given number of columns,and the theme. This is the
	 * constructor through which the other constructors feed. If the document is
	 * <code>null</code>, a default model is created.
	 *
	 * @param doc
	 *            the text storage to use; if this is <code>null</code>, a
	 *            default will be provided by calling the
	 *            <code>createDefaultModel</code> method
	 * @param text
	 *            the initial string to display, or <code>null</code>
	 * @param columns
	 *            the number of columns to use to calculate the preferred width
	 *            &gt;= 0; if <code>columns</code> is set to zero, the preferred
	 *            width will be whatever naturally results from the component
	 *            implementation
	 * @param theme
	 *            the theme of this {@code MyTextField} ,and may be
	 *            {@code <code>null</code>}
	 * @exception IllegalArgumentException
	 *                if <code>columns</code> &lt; 0
	 */
	public MyTextField(Document doc, String text, int columns, TextFieldTheme theme) {
		super(doc, text, columns);
		if (theme != null) {
			this.theme = theme;
			theme.init(this);
		}
		l = new TextListener();
		addFocusListener(l);
		addMouseMotionListener(l);
		addMouseListener(l);
		addKeyListener(l);
	}

	/**
	 * Constructs a new <code>MyTextField</code> that uses the given theme. If
	 * the document is <code>null</code>, a default model is created.
	 *
	 * @param theme
	 *            the theme of this {@code MyTextField} ,and may be
	 *            {@code <code>null</code>}
	 * @exception IllegalArgumentException
	 *                if <code>columns</code> &lt; 0
	 */
	public MyTextField(TextFieldTheme theme) {
		this(null, null, 0, theme);
	}

	public void setType(int type) {
		this.type = type;
		switch (type) {
		case ACCOUNT:
			break;
		case KEY:
			keyByChars = new MyArrayList<>();
			break;
		case TEXT:
			break;
		default:

		}
	}

	public void setHint(String hint) {
		this.hint = hint;
		if (!isFocusOwner()) {
			if (getText().equals(new String())) {
				setIsHintVisible(true);
			}
		}
	}

	public boolean isHintVisible() {
		return isHintVisible;
	}

	public void setIsHintVisible(boolean isHintVisible) {
		this.isHintVisible = isHintVisible;
		if (isHintVisible) {
			super.setForeground(hintColor);
			setText(hint);
		}
	}

	public void clearHint() {
		super.setForeground(fontColor);
		setText(new String());
	}

	public void setHintground(Color hg) {
		this.hintColor = hg;
	}

	public void setForeground(Color fg) {
		this.fontColor = fg;
		super.setForeground(fg);
	}

	public String getHint() {
		return hint;
	}

	/**
	 * Resizes this component so that it has width <code>d.width</code> and
	 * height <code>d.height</code>.
	 * <p>
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy.
	 *
	 * @param d
	 *            the dimension specifying the new size of this component
	 * @throws NullPointerException
	 *             if {@code d} is {@code null}
	 * @see #setSize
	 * @see #setBounds
	 * @see #invalidate
	 * @since JDK1.1
	 */
	public void setSize(Dimension d) {
		super.setSize(d);
		if (theme != null)
			if (!d.equals(theme.getDimension())) {
				theme.setSize(d);
			}
	}

	public String getText() {
		if (type == KEY) {
			if (keyByChars.getSize() > 0)
				key = MyStringTool.CharArrayToString(keyByChars);
			else
				key = "";
			return key;
		} else
			return super.getText();
	}

	public void dispose() {
		theme.dispose();
		hint = null;
		fontColor = null;
		hintColor = null;
		l = null;
		keyByChars.destroy();
		keyByChars = null;
		key = null;
		theme = null;
	}

	private class TextListener implements MouseMotionListener, MouseListener, FocusListener, KeyListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (isHintVisible) {
				clearHint();
				setIsHintVisible(false);
				setText(new String());
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (getText().equals(new String())) {
				setIsHintVisible(true);
				setCaretPosition(0);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (type == KEY) {
				int length = keyByChars.getSize();
				if (!e.isActionKey()) {
					if (e.getKeyChar() == '\b') {
						keyByChars.delete(keyByChars.getSize() - 1);
					} else
						keyByChars.addElement(e.getKeyChar());
				}
				String text = "";
				for (int i = 0; i < length; i++) {
					text += "*";
				}
				setText(text);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (type == KEY) {
				String text = "";
				for (int i = 0; i < keyByChars.getSize(); i++) {
					text += "*";
				}
				setText(text);
			}
		}

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
			return inset;
		}

		@Override
		public boolean isBorderOpaque() {
			return true;
		}

		public void setColor(Color c) {
			color = c;
		}

		public Color getColor() {
			return color;
		}
	}

	public void useDefaultBorder() {
		init();
	}
}
