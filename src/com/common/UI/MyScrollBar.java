package com.common.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class MyScrollBar extends JComponent implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8913289335414364082L;

	int width, height;
	Color background = new Color(205, 205, 205), on = new Color(166, 166, 166), drag = new Color(96, 96, 96);
	MyScrollBar bar;
	int status = 0;
	final static int STATUS_NORMAL = 0;
	final static int STATUS_ON = 1;
	final static int STATUS_DRAG = 2;
	Color gColor;
	int round = 0;

	public MyScrollBar() {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		this.width = d.width;
		this.height = d.height;
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	void reSize() {
		super.setSize(width, height);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics g0 = g.create();

		if (gColor != null) {
			g0.setColor(gColor);
			g0.fillRect(0, 0, width, height);
		}
		switch (status) {
		case STATUS_NORMAL:
			g0.setColor(background);
			break;
		case STATUS_ON:
			g0.setColor(on);
			break;
		case STATUS_DRAG:
			g0.setColor(drag);
			break;
		default:
			break;
		}

		g0.fillRoundRect(0, 0, width, height, round, round);
	}

	public void setRound(int round) {
		this.round = round;
	}

	protected boolean inButton = false;

	@Override
	public void mouseDragged(MouseEvent e) {
		status = 2;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
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
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		status = 1;
		inButton = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		status = 0;
		inButton = true;
	}

}
