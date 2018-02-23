package com.common.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class MyScrollGroove extends JComponent implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4121150641724449449L;

	int width, height;
	Color background = new Color(240, 240, 240), on = new Color(105, 105, 105), press = new Color(96, 96, 96);
	MyScrollBar bar;
	int status = 0;
	boolean left = false, right = false;
	final static int STATUS_NORMAL = 0;
	final static int STATUS_ON = 1;
	final static int STATUS_DRAG = 2;

	public MyScrollGroove() {
		// TODO Auto-generated constructor stub
		this(new MyScrollBar());
	}

	public MyScrollGroove(MyScrollBar bar) {
		super();
		initScrollBar(bar);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void initScrollBar(MyScrollBar bar) {
		this.bar = bar;
		bar.gColor = background;
	}

	@Override
	public void setSize(Dimension d) {
		// TODO Auto-generated method stub
		super.setSize(d);
		this.width = d.width;
		this.height = d.height;
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	void reSize() {
		super.setSize(width, height);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics g0 = g.create();
		g0.setColor(background);
		g0.fillRect(0, 0, width, height);
		Graphics g1 = g.create();
		// switch (status) {
		// case STATUS_NORMAL:
		// if (left)
		// g0.setColor(background);
		// if (right)
		// g1.setColor(background);
		// break;
		// case STATUS_ON:
		// if (left)
		// g0.setColor(on);
		// if (right)
		// g1.setColor(on);
		// break;
		// case STATUS_DRAG:
		// if (left)
		// g0.setColor(press);
		// if (right)
		// g1.setColor(press);
		// break;
		// default:
		// break;
		// }

		Polygon left = new Polygon();
		Polygon right = new Polygon();
		if (width > height) {
			left.addPoint(8, 4);
			left.addPoint(5, 7);
			left.addPoint(8, 10);

			right.addPoint(width - 8, 4);
			right.addPoint(width - 5, 7);
			right.addPoint(width - 8, 10);
		} else {
			left.addPoint(4, 8);
			left.addPoint(7, 5);
			left.addPoint(10, 8);

			right.addPoint(4, width - 8);
			right.addPoint(7, width - 5);
			right.addPoint(10, width - 8);
		}

		g0.setColor(Color.GRAY);
		g1.setColor(Color.GRAY);
		g0.drawPolygon(left);
		g1.drawPolygon(right);
	}

	public void setBackground(Color background) {
		super.setBackground(background);
		bar.gColor = this.background = background;
	}

	public Color getBackground() {
		return background != null ? background : super.getBackground();
	}

	protected boolean inButton = false;

	@Override
	public void mouseDragged(MouseEvent e) {
		if (inButton)
			status = 2;
		else
			status = 0;
		left = inleft(e);
		right = inright(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		left = inleft(e);
		right = inright(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (inButton)
			status = 1;
		else
			status = 0;
		left = inleft(e);
		right = inright(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		status = 1;
		inButton = true;
		left = inleft(e);
		right = inright(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		status = 0;
		inButton = true;
		left = false;
		right = false;
	}

	private boolean inleft(MouseEvent e) {
		if (e.getX() >= 0 && e.getX() < height && e.getY() >= getLocation().getY()
				&& e.getY() < (getLocation().getY() + height)) {
			return true;
		}
		return false;
	}

	private boolean inright(MouseEvent e) {
		if (e.getX() >= (getLocation().x + width - height) && e.getX() < (getLocation().x + width)
				&& e.getY() >= getLocation().getY() && e.getY() < (getLocation().getY() + height)) {
			return true;
		}
		return false;
	}

}
