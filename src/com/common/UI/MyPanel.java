package com.common.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import com.common.UI.theme.PanelTheme;

public class MyPanel extends JPanel implements MyUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6955513175011347036L;

	protected PanelTheme theme;
	private int width = -1, height = -1;

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
	public MyPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * Create a new buffered MyButton with the specified layout manager
	 * 
	 * @param layout
	 *            the LayoutManager to use
	 */
	public MyPanel(LayoutManager layout) {
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
	public MyPanel(boolean isDoubleBuffered) {
		this(null, isDoubleBuffered);
	}

	/**
	 * Creates a new <code>MyButton</code> with a double buffer and a flow
	 * layout.
	 */
	public MyPanel() {
		this(true);
		init(new DefaultPanelTheme());
	}

	/**
	 * Creates a new <code>MyButton</code> with a double buffer and a flow
	 * layout.
	 * 
	 * @param theme
	 *            按钮主题
	 */
	public MyPanel(PanelTheme theme) {
		this(true);
		init(theme);

	}

	public void init() {
		if (theme != null) {
			theme.init(this);
			if (theme == null)
				theme.setSize(new Dimension());
			else if (!theme.getDimension().equals(new Dimension())) {
				if (!(theme.getDimension().width == 0 && theme.getDimension().height == 0))
					theme.setSize();
			} else if (width >= 0 && height >= 0)
				theme.setSize(new Dimension(width, height));
			else
				theme.setSize(new Dimension());

			if (theme instanceof MouseListener) {
				addMouseListener((MouseListener) theme);
			}

			if (theme instanceof MouseMotionListener) {
				addMouseMotionListener((MouseMotionListener) theme);
			}

			if (theme instanceof MouseWheelListener) {
				addMouseWheelListener((MouseWheelListener) theme);
			}
		}
	}

	public void setTheme(PanelTheme theme) {
		this.theme = theme;
	}

	/**
	 * 初始化theme
	 * 
	 * @param theme
	 *            按钮的主题
	 */
	public void init(PanelTheme theme) {
		this.theme = theme;
		init();
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (theme != null) {
			theme.setBackgroudImage(g);
		}
		for (Component c : getComponents()) {
			c.repaint();
		}
	}

	/**
	 * Paints each of the components in this container.
	 * 
	 * @param g
	 *            the graphics context.
	 * @see Component#paint
	 * @see Component#paintAll
	 */
	public void paintComponent(Graphics g) {
		if (theme != null)
			theme.setBackgroudImage(g);
		super.paintComponent(g);
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
		width = d.width;
		height = d.height;
		if (theme != null)
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
	 * @param d
	 *            the dimension specifying the new size of this component
	 * @throws NullPointerException
	 *             if {@code d} is {@code null}
	 * @see #setSize
	 * @see #setBounds
	 * @see #invalidate
	 * @since JDK1.1
	 */
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.width = width;
		this.height = height;
		if (theme != null)
			if (!theme.getDimension().equals(new Dimension(width, height))) {
				theme.setSize(width, height);
			}
	}

	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		width = preferredSize.width;
		height = preferredSize.height;
		if (theme != null)
			if (!preferredSize.equals(theme.getDimension())) {
				theme.setSize(preferredSize);
			}
	};

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public Dimension getSize() {
		return new Dimension(width, height);
	}

	@Override
	public void dispose() {
		if (theme != null)
			theme.dispose();
		theme = null;
	}

	class DefaultPanelTheme extends PanelTheme {
		public DefaultPanelTheme() {
			super();
		}
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
		}
		if (theme != null)
			if (!bg.equals(theme.getBackground()))
				theme.setColor(bg);
	}

	@Override
	public Color getBackground() {
		// TODO Auto-generated method stub
		return theme == null ? super.getBackground()
				: (theme.getBackground() == null ? super.getBackground() : theme.getBackground());
	}

	public void clearTheme() {
		if (theme != null) {
			theme.dispose();
			theme = null;
		}
	}

	/**
	 * Removes all the components from this container. This method also notifies
	 * the layout manager to remove the components from this container's layout
	 * via the <code>removeLayoutComponent</code> method.
	 * <p>
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy. If the container has already been
	 * displayed, the hierarchy must be validated thereafter in order to reflect
	 * the changes.
	 *
	 * @see #add
	 * @see #remove
	 * @see #invalidate
	 */
	public void removeAll() {
		super.removeAll();
	}

	public PanelTheme getTheme() {
		return theme;
	}
}
