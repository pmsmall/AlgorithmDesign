package com.common.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyScrollArea extends JPanel {

	BufferedImage content;
	final static int DEFAULT_HEIGHT = 200;
	int width, height, preWidth, preHeight;
	MyScrollBar scrollBar;
	MyScrollGroove groove;
	Color background;
	int left;
	int i;
	ScrollListener listener = new ScrollListener();
	JComponent child;

	/**
	 * Graphics2D 样式的map
	 */
	HashMap<RenderingHints.Key, Object> renderingHintsMap = new HashMap<>();

	public MyScrollArea() {
		this(null);
	}

	public MyScrollArea(JComponent content) {
		super();
		initBar();
		height = DEFAULT_HEIGHT;
		if (content != null) {
			this.content = new BufferedImage(content.getWidth(), content.getHeight(), 3);
			child = content;
		} else {
			this.content = new BufferedImage(width, DEFAULT_HEIGHT - scrollBar.height, 3);
		}
		super.setSize(width, height);
		scrollBar.reSize();
		groove.reSize();
		super.setBackground(Color.white);
		groove.setLocation(0, height - groove.height);
		scrollBar.setLocation(groove.height, height - scrollBar.height);
		background = new Color(250, 250, 250);
		setOpaque(true);

		initGraphicTools();

		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	public void resetContent(JComponent content) {
		if (content != null) {
			this.content = new BufferedImage(content.getWidth(), content.getHeight(), 3);
			child = content;
		} else {
			this.content = new BufferedImage(width, DEFAULT_HEIGHT - scrollBar.height, 3);
		}
		updateGroove();
		updateBar();
	}

	void initGraphicTools() {
		/**
		 * 抗锯齿
		 */
		renderingHintsMap.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/**
		 * 抗文本锯齿
		 */
		renderingHintsMap.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

	}

	void initBar() {
		scrollBar = new MyScrollBar();
		scrollBar.height = 15;
		groove = new MyScrollGroove(scrollBar);
		groove.height = 15;
		scrollBar.width = width = DEFAULT_HEIGHT;
		groove.width = scrollBar.width - scrollBar.height * 2;
	}

	@Override
	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	public void setContentSize(int width, int height) {
		child.setSize(width, height);
		this.content = new BufferedImage(content.getWidth(), content.getHeight(), 3);
		updateGroove();
		updateBar();
	}

	public void setContentSize(Dimension d) {
		child.setSize(d);
		this.content = new BufferedImage(content.getWidth(), content.getHeight(), 3);
		updateGroove();
		updateBar();
	}

	@Override
	public void setSize(int width, int height) {
		preWidth = this.width;
		preHeight = this.height;
		this.width = width;
		this.height = height;
		updateGroove();
		updateBar();
		super.setSize(width, height);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		Graphics2D g0 = (Graphics2D) g.create();
		// g0.setRenderingHints(renderingHintsMap);

		g0.setColor(background);
		g0.fillRect(0, 0, width, height);
		if (child != null) {
			Graphics g1 = content.getGraphics();
			if (child instanceof MyUI)
				((MyUI) child).getTheme().setBackgroudImage(g1);
			else
				child.paint(g1);
		}

		int a = width - scrollBar.width - groove.height * 2;
		if (a == 0)
			a = 1;

		g0.drawImage(content, 0 - left * (content.getWidth() - width) / a, 0, content.getWidth(),
				height - groove.height, null);

		groove.paint(g.create(groove.getLocation().x, groove.getLocation().y, groove.width, groove.height));
		scrollBar.paint(
				g.create(scrollBar.getLocation().x, scrollBar.getLocation().y, scrollBar.width, scrollBar.height));
	}

	void updateGroove() {
		groove.width = width;
		groove.setLocation(0, height - groove.height);
	}

	void updateBar() {
		scrollBar.width = (width - scrollBar.height * 2) * groove.width / content.getWidth();
		left = left * width / preWidth;
		scrollBar.setLocation(groove.height + left, height - scrollBar.height);
	}

	public Color getGrooveBackground() {
		return groove.background;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + '\n' + scrollBar + "\n" + groove;
	}

	private class ScrollListener implements MouseMotionListener, MouseListener {

		boolean inBar = false;
		boolean inGroove = false;
		int x;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (inBar(e))
				scrollBar.mouseClicked(e);
			if (inGroove(e))
				groove.mouseClicked(e);

			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (inBar(e))
				scrollBar.mousePressed(e);
			if (inGroove(e))
				groove.mousePressed(e);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (inBar(e))
				scrollBar.mouseReleased(e);
			else {
				inBar = false;
				scrollBar.mouseExited(e);
			}
			if (inGroove(e))
				groove.mouseReleased(e);
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (inBar(e)) {
				BarEntered(e);
				repaint();
			} else
				inBar = false;
			if (inGroove(e)) {
				groove.mouseEntered(e);
				inGroove = true;
				repaint();
			} else
				inBar = false;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			scrollBar.mouseExited(e);
			groove.mouseExited(e);
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (inBar) {
				scrollBar.mouseDragged(e);
				int preX = (int) scrollBar.getLocation().getX();
				int a = preX + e.getX() - x;
				if (a < groove.height)
					a = groove.height;
				if (a > groove.width - groove.height - scrollBar.width)
					a = groove.width - groove.height - scrollBar.width;
				updateLeft(left + a - preX);
				int b = (int) (scrollBar.getLocation().getY());
				scrollBar.setLocation(a, b);
				x = e.getX();
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (inBar(e)) {
				if (!inBar) {
					BarEntered(e);
				}
				scrollBar.mouseMoved(e);
				repaint();
			} else {
				if (inBar) {
					inBar = false;
					scrollBar.mouseExited(e);
					repaint();
				}
			}
			if (inGroove(e)) {
				if (!inGroove) {
					inGroove = true;
					groove.mouseEntered(e);
				}
				groove.mouseMoved(e);
				repaint();
			} else {
				if (inGroove) {
					inGroove = false;
					groove.mouseExited(e);
					repaint();
				}
			}
		}

		public boolean inGroove(MouseEvent e) {
			return (e.getX() >= groove.getLocation().x) && (e.getX() < groove.getLocation().x + groove.width)
					&& (e.getY() >= groove.getLocation().y) && (e.getY() < groove.getLocation().y + groove.height);
		}

		public boolean inBar(MouseEvent e) {
			return (e.getX() >= scrollBar.getLocation().x) && (e.getX() < scrollBar.getLocation().x + scrollBar.width)
					&& (e.getY() >= scrollBar.getLocation().y)
					&& (e.getY() < scrollBar.getLocation().y + scrollBar.height);
		}

		public void BarEntered(MouseEvent e) {
			inBar = true;
			scrollBar.mouseEntered(e);
			x = e.getX();
		}

	}

	private void updateLeft(int left) {
		if (this.left != left) {
			this.left = left;
		}
	}
}
