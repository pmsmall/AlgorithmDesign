package com.common.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.common.UI.theme.ButtonTheme;

public class MyButton extends JPanel implements MyUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2871473941726514083L;

	protected ButtonTheme theme;

	/**
	 * Creates a new MyButton with the specified layout manager and buffering
	 * strategy.
	 * 
	 * @param layout
	 *            the LayoutManager to use
	 * @param isDoubleBuffered
	 *            a boolean, true for double-buffering, which uses additional
	 *            memory space to achieve fast, flicker-free updates
	 */
	public MyButton(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * Create a new buffered MyButton with the specified layout manager
	 * 
	 * @param layout
	 *            the LayoutManager to use
	 */
	public MyButton(LayoutManager layout) {
		this(layout, true);
	}

	/**
	 * Creates a new <code>MyButton</code> with <code>FlowLayout</code> and the
	 * specified buffering strategy. If <code>isDoubleBuffered</code> is true,
	 * the <code>MyButton</code> will use a double buffer.
	 * 
	 * @param isDoubleBuffered
	 *            a boolean, true for double-buffering, which uses additional
	 *            memory space to achieve fast, flicker-free updates
	 */
	public MyButton(boolean isDoubleBuffered) {
		this(new FlowLayout(), isDoubleBuffered);
	}

	/**
	 * Creates a new <code>MyButton</code> with a double buffer and a flow
	 * layout.
	 */
	public MyButton() {
		this(true);
	}

	/**
	 * Creates a new <code>MyButton</code> with a double buffer and a flow
	 * layout.
	 * 
	 * @param theme
	 *            按钮主题
	 */
	public MyButton(ButtonTheme theme) {
		this(true);
		init(theme);
		theme.init(this);
	}

	public void init() {
		if (theme != null)
			theme.setSize();
		setOpaque(true);
	}

	/**
	 * 初始化theme
	 * 
	 * @param theme
	 *            按钮的主题
	 */
	public void init(ButtonTheme theme) {
		this.theme = theme;
		init();
	}

	public void paint(Graphics g) {
		super.paint(g);
		theme.setBackgroudImage(g);
	}

	public Graphics2D getGraphics2D() {
		return (Graphics2D) (this.getGraphics());
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
		if (!d.equals(theme.getDimension())) {
			theme.setSize(d);
		}
	}

	/**
	 * Resizes this component so that it has width <code>d.width</code> and
	 * height <code>d.height</code>.
	 * <p>
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy.
	 *
	 * @param width
	 *            the new width of this component in pixels
	 * @param height
	 *            the new height of this component in pixels
	 * @throws NullPointerException
	 *             if {@code d} is {@code null}
	 * @see #setSize
	 * @see #setBounds
	 * @see #invalidate
	 * @since JDK1.1
	 */
	public void setSize(int width, int height) {
		super.setSize(width, height);
		Dimension d = new Dimension(width, height);
		if (!d.equals(theme.getDimension())) {
			theme.setSize(d);
		}
	}

	/**
	 * Makes the component visible or invisible. Overrides
	 * <code>Component.setVisible</code>.
	 *
	 * @param aFlag
	 *            true to make the component visible; false to make it invisible
	 *
	 * @beaninfo attribute: visualUpdate true
	 */
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		theme.setLisener();
	}

	public void dispose() {
		clearMouseListener();
		theme.dispose();
		theme = null;
	}

	void clearMouseListener() {
		MouseListener[] mousel = getMouseListeners();
		for (int i = 0; i < mousel.length; i++) {
			removeMouseListener(mousel[i]);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		// System.out.println("system is destroying this " + this);
		super.finalize();
	}

	/**
	 * Sets the background color of this component. The background color is used
	 * only if the component is opaque, and only by subclasses of
	 * <code>JComponent</code> or <code>ComponentUI</code> implementations.
	 * Direct subclasses of <code>JComponent</code> must override
	 * <code>paintComponent</code> to honor this property.
	 * <p>
	 * It is up to the look and feel to honor this property, some may choose to
	 * ignore it.
	 *
	 * @param bg
	 *            the desired background <code>Color</code>
	 * @see java.awt.Component#getBackground
	 * @see #setOpaque
	 *
	 * @beaninfo preferred: true bound: true attribute: visualUpdate true
	 *           description: The background color of the component.
	 */
	public void setBackground(Color bg) {
		Color oldBg = getBackground();
		super.setBackground(bg);
		if ((oldBg != null) ? !oldBg.equals(bg) : ((bg != null) && !bg.equals(oldBg))) {
			// background already bound in AWT1.2
			repaint();
			// ((ButtonTheme)theme).setShape();
		}
	}

	public ButtonTheme getTheme() {
		return theme;
	}

}
