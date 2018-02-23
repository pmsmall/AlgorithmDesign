package com.common.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import com.common.UI.theme.FrameTheme;
import com.common.UI.theme.ThemeDemo;
import com.common.arrayListTools.MyArrayList;

public class MyFrame extends JFrame implements MyUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7359751527266232591L;

	private FrameTheme theme = null;
	private MyArrayList<Component> componentsList = new MyArrayList<>();
	private boolean firstVisible = true;
	private showType type;
	private int lastWidth, lastHeight;
	private int width, height;

	MyFrame() throws HeadlessException {
		super();
		init();
	}

	MyFrame(GraphicsConfiguration gc) {
		super(gc);
		init();
	}

	public MyFrame(String title) throws HeadlessException {
		super(title);
		init();
	}

	public MyFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		init();
	}

	public MyFrame(FrameTheme theme) {
		this.theme = theme;
		theme.init(this);
		init();
	}

	public void init() {
		if (theme == null) {
			// TODO new theme
			theme = new ThemeDemo(this);
		}

		// setDefaultCloseOperation(3);
		// setLocationRelativeTo(null);

		this.setUndecorated(true);

		// this.setOpacity((float) 0.8);

		theme.setShape();
		// theme.setAlpha();

		FrameListener flistener = new FrameListener(this);
		this.addMouseListener(flistener);
		this.addMouseMotionListener(flistener);
		this.setLayout(null);
	}

	public Graphics2D getGraphics2D() {
		return (Graphics2D) (this.getGraphics());
	}

	public void setVisible(boolean b) {
		theme.setSize();
		super.setVisible(b);

		if (firstVisible && b) {
			firstVisible = false;
			if (type == showType.MAX) {
				stayTop();
				loseTop();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		theme.setBackgroudImage();
		for (int i = 0; i < componentsList.getSize(); i++) {
			componentsList.getElement(i).repaint();
		}
	}

	/**
	 * Adds the specified child <code>Component</code>. This method is
	 * overridden to conditionally forward calls to the <code>contentPane</code>
	 * . By default, children are added to the <code>contentPane</code> instead
	 * of the frame, refer to {@link javax.swing.RootPaneContainer} for details.
	 * 
	 * @param comp
	 *            the component to be enhanced
	 * @param constraints
	 *            the constraints to be respected
	 * @param index
	 *            the index
	 * @exception IllegalArgumentException
	 *                if <code>index</code> is invalid
	 * @exception IllegalArgumentException
	 *                if adding the container's parent to itself
	 * @exception IllegalArgumentException
	 *                if adding a window to a container
	 * 
	 * @see #setRootPaneCheckingEnabled
	 * @see javax.swing.RootPaneContainer
	 */
	protected void addImpl(Component comp, Object constraints, int index) {
		if (isRootPaneCheckingEnabled()) {
			getContentPane().add(comp, constraints, index);
		} else {
			super.addImpl(comp, constraints, index);
		}
		if (componentsList != null && comp != null) {
			componentsList.addElement(comp);
		}
	}

	/**
	 * Appends the specified component to the end of this container. This is a
	 * convenience method for {@link #addImpl}.
	 * <p>
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy. If the container has already been
	 * displayed, the hierarchy must be validated thereafter in order to display
	 * the added component.
	 *
	 * @param comp
	 *            the component to be added
	 * @exception NullPointerException
	 *                if {@code comp} is {@code null}
	 * @see #addImpl
	 * @see #invalidate
	 * @see #validate
	 * @see javax.swing.JComponent#revalidate()
	 * @return the component argument
	 */
	public Component add(Component comp) {
		super.add(comp);
		// addImpl(comp, null, -1);
		return comp;
	}

	/**
	 * Sets the location of the window relative to the specified component
	 * according to the following scenarios.
	 * <p>
	 * The target screen mentioned below is a screen to which the window should
	 * be placed after the setLocationRelativeTo method is called.
	 * <ul>
	 * <li>If the component is {@code null}, or the {@code
	 * GraphicsConfiguration} associated with this component is {@code null},
	 * the window is placed in the center of the screen. The center point can be
	 * obtained with the {@link GraphicsEnvironment#getCenterPoint
	 * GraphicsEnvironment.getCenterPoint} method.
	 * <li>If the component is not {@code null}, but it is not currently
	 * showing, the window is placed in the center of the target screen defined
	 * by the {@code
	 * GraphicsConfiguration} associated with this component.
	 * <li>If the component is not {@code null} and is shown on the screen, then
	 * the window is located in such a way that the center of the window
	 * coincides with the center of the component.
	 * </ul>
	 * <p>
	 * If the screens configuration does not allow the window to be moved from
	 * one screen to another, then the window is only placed at the location
	 * determined according to the above conditions and its
	 * {@code GraphicsConfiguration} is not changed.
	 * <p>
	 * <b>Note</b>: If the lower edge of the window is out of the screen, then
	 * the window is placed to the side of the {@code Component} that is closest
	 * to the center of the screen. So if the component is on the right part of
	 * the screen, the window is placed to its left, and vice versa.
	 * <p>
	 * If after the window location has been calculated, the upper, left, or
	 * right edge of the window is out of the screen, then the window is located
	 * in such a way that the upper, left, or right edge of the window coincides
	 * with the corresponding edge of the screen. If both left and right edges
	 * of the window are out of the screen, the window is placed at the left
	 * side of the screen. The similar placement will occur if both top and
	 * bottom edges are out of the screen. In that case, the window is placed at
	 * the top side of the screen.
	 * <p>
	 * The method changes the geometry-related data. Therefore, the native
	 * windowing system may ignore such requests, or it may modify the requested
	 * data, so that the {@code Window} object is placed and sized in a way that
	 * corresponds closely to the desktop settings.
	 *
	 * @param c
	 *            the component in relation to which the window's location is
	 *            determined
	 * @see java.awt.GraphicsEnvironment#getCenterPoint
	 * @since 1.4
	 */
	public void setLocationRelativeTo(Component c) {
		if (c != null) {
			super.setLocationRelativeTo(c);
		}
		// target location
		int dx = 0, dy = 0;
		// target GC

		Dimension windowSize = theme.getDimension();

		// search a top-level of c

		if ((c == null)) {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Point centerPoint = ge.getCenterPoint();
			dx = centerPoint.x - windowSize.width / 2;
			dy = centerPoint.y - windowSize.height / 2;
		}

		setLocation(dx, dy);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The {@code d.width} and {@code d.height} values are automatically
	 * enlarged if either is less than the minimum size as specified by previous
	 * call to {@code setMinimumSize}.
	 * <p>
	 * The method changes the geometry-related data. Therefore, the native
	 * windowing system may ignore such requests, or it may modify the requested
	 * data, so that the {@code Window} object is placed and sized in a way that
	 * corresponds closely to the desktop settings.
	 *
	 * @see #getSize
	 * @see #setBounds
	 * @see #setMinimumSize
	 * @since 1.6
	 */
	public void setSize(Dimension d) {
		super.setSize(d);
		width = d.width;
		height = d.height;
		theme.setSize(d);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The {@code width} and {@code height} values are automatically enlarged if
	 * either is less than the minimum size as specified by previous call to
	 * {@code setMinimumSize}.
	 * <p>
	 * The method changes the geometry-related data. Therefore, the native
	 * windowing system may ignore such requests, or it may modify the requested
	 * data, so that the {@code Window} object is placed and sized in a way that
	 * corresponds closely to the desktop settings.
	 *
	 * @see #getSize
	 * @see #setBounds
	 * @see #setMinimumSize
	 * @since 1.6
	 */
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.width = width;
		this.height = height;
		theme.setSize(new Dimension(width, height));
	}

	/**
	 * 通过图形界面的驱动使得界面最大化
	 */
	public void max() {
		lastWidth = getWidth();
		lastHeight = getHeight();
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screen_size.width;
		int height = screen_size.height;
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		GraphicsConfiguration gc = device.getDefaultConfiguration();
		int taskbar_height = Toolkit.getDefaultToolkit().getScreenInsets(gc).bottom;
		setSize(width, height - taskbar_height);
	}

	/**
	 * 恢复窗体大小
	 */
	public void restoreSize() {
		if (lastHeight > 0 && lastWidth > 0)
			setSize(lastWidth, lastHeight);
	}

	// TODO to redo this method
	/**
	 * 通过图形界面的驱动启动霸屏模式
	 */
	public void fullScreen() {
		lastWidth = getWidth();
		lastHeight = getHeight();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		stayTop();
	}

	/**
	 * 通过图形界面的驱动解除霸屏模式
	 */
	public void loseFullScreen() {
		setSize(new Dimension(lastWidth, lastHeight));
		loseTop();
	}

	/**
	 * 通过图形界面的驱动启动置顶模式
	 */
	public void stayTop() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		device.setFullScreenWindow(this);
	}

	/**
	 * 通过图形界面的驱动解除置顶模式
	 */
	public void loseTop() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		device.setFullScreenWindow(null);
	}

	public void setMyFrameShowType(showType type) {
		this.type = type;
	}

	public void finalize() {
		System.out.println("system is destroying " + this);
	}

	private class FrameListener implements MouseListener, MouseMotionListener {

		private int x0, y0, x1, y1, x, y;
		private MyFrame mframe;
		private boolean moveInformation = false;

		public FrameListener(MyFrame mframe) {
			this.mframe = mframe;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getX() + "'" + e.getY());

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			moveInformation = true;
			x0 = e.getX();
			y0 = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (moveInformation)
				moveInformation = false;
			// mframe.repaint();
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
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

			if (moveInformation) {
				x1 = e.getX();
				y1 = e.getY();
				x = mframe.getX();
				y = mframe.getY();
				x += x1 - x0;
				y += y1 - y0;
				mframe.setLocation(x, y);
				// mframe.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public enum showType {
		DEFAULT, //
		DEPENDED, //
		MIN, //
		MAX //
	}

	/**
	 * Releases all of the native screen resources used by this {@code Window},
	 * its subcomponents, and all of its owned children. That is, the resources
	 * for these {@code Component}s will be destroyed, any memory they consume
	 * will be returned to the OS, and they will be marked as undisplayable.
	 * <p>
	 * The {@code Window} and its subcomponents can be made displayable again by
	 * rebuilding the native resources with a subsequent call to {@code pack} or
	 * {@code show}. The states of the recreated {@code Window} and its
	 * subcomponents will be identical to the states of these objects at the
	 * point where the {@code Window} was disposed (not accounting for
	 * additional modifications between those actions).
	 * <p>
	 * <b>Note</b>: When the last displayable window within the Java virtual
	 * machine (VM) is disposed of, the VM may terminate. See
	 * <a href="doc-files/AWTThreadIssues.html#Autoshutdown"> AWT Threading
	 * Issues</a> for more information.
	 * 
	 * @see Component#isDisplayable
	 * @see #pack
	 * @see #show
	 */
	public void dispose() {
		theme.dispose();
		theme = null;
		for (Component c : getComponents()) {
			if (c instanceof MyUI) {
				((MyUI) c).dispose();
			}
		}
		for (int i = 0; i < componentsList.getSize(); i++) {
			if (componentsList.getElement(i) instanceof MyUI) {
				((MyUI) componentsList.getElement(i)).dispose();
			}
		}

		clearListeners();
		removeAll();

		componentsList.destroy();
		componentsList = null;

		super.dispose();
	}

	public void removeAll() {
		super.removeAll();
	}

	void clearListeners() {
		clearContainerListener();
		clearComponentListener();
		clearFocusListener();
		clearHierarchyBoundsListener();
		clearInputMethodListeners();
		clearKeyListeners();
		clearMouseListeners();
		clearMouseMotionListeners();
		clearMouseWheelListeners();
		clearPropertyChangeListeners();
		clearWindowFocusListeners();
		clearWindowListener();
		clearWindowStateListeners();
	}

	void clearContainerListener() {
		ContainerListener[] containerl = getContainerListeners();
		if (containerl != null) {
			for (int i = 0; i < containerl.length; i++) {
				removeContainerListener(containerl[i]);
			}
		}
	}

	void clearComponentListener() {
		ComponentListener[] componentl = getComponentListeners();
		if (componentl != null) {
			for (int i = 0; i < componentl.length; i++) {
				removeComponentListener(componentl[i]);
			}
		}
	}

	void clearFocusListener() {
		FocusListener[] focusl = getFocusListeners();
		if (focusl != null) {
			for (int i = 0; i < focusl.length; i++) {
				removeFocusListener(focusl[i]);
			}
		}
	}

	void clearHierarchyBoundsListener() {
		HierarchyBoundsListener[] hierarchyl = getHierarchyBoundsListeners();
		if (hierarchyl != null) {
			for (int i = 0; i < hierarchyl.length; i++) {
				removeHierarchyBoundsListener(hierarchyl[i]);
			}
		}
	}

	void clearInputMethodListeners() {
		InputMethodListener[] inputMethodl = getInputMethodListeners();
		if (inputMethodl != null) {
			for (int i = 0; i < inputMethodl.length; i++) {
				removeInputMethodListener(inputMethodl[i]);
			}
		}
	}

	void clearKeyListeners() {
		KeyListener[] keyl = getKeyListeners();
		if (keyl != null) {
			for (int i = 0; i < keyl.length; i++) {
				removeKeyListener(keyl[i]);
			}
		}
	}

	void clearMouseListeners() {
		MouseListener[] mousel = getMouseListeners();
		if (mousel != null) {
			for (int i = 0; i < mousel.length; i++) {
				removeMouseListener(mousel[i]);
			}
		}
	}

	void clearMouseMotionListeners() {
		MouseMotionListener[] mouseMotionl = getMouseMotionListeners();
		if (mouseMotionl != null) {
			for (int i = 0; i < mouseMotionl.length; i++) {
				removeMouseMotionListener(mouseMotionl[i]);
			}
		}
	}

	void clearMouseWheelListeners() {
		MouseWheelListener[] wheell = getMouseWheelListeners();
		if (wheell != null) {
			for (int i = 0; i < wheell.length; i++) {
				removeMouseWheelListener(wheell[i]);
			}
		}
	}

	void clearPropertyChangeListeners() {
		PropertyChangeListener[] proChal = getPropertyChangeListeners();
		if (proChal != null) {
			for (int i = 0; i < proChal.length; i++) {
				removePropertyChangeListener(proChal[i]);
			}
		}
	}

	void clearWindowFocusListeners() {
		WindowFocusListener[] winFocusl = getWindowFocusListeners();
		if (winFocusl != null) {
			for (int i = 0; i < winFocusl.length; i++)
				removeWindowFocusListener(winFocusl[i]);
		}
	}

	// TODO
	void clearWindowListener() {
		WindowListener[] windowl = getWindowListeners();
		if (windowl != null) {
			for (int i = 0; i < windowl.length; i++)
				removeWindowListener(windowl[i]);
		}
	}

	void clearWindowStateListeners() {
		WindowStateListener[] winStatel = getWindowStateListeners();
		if (winStatel != null) {
			for (int i = 0; i < winStatel.length; i++)
				removeWindowStateListener(winStatel[i]);
		}
	}

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
	
	public FrameTheme getTheme(){
		return theme;
	}
}
