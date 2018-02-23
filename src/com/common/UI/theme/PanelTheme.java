package com.common.UI.theme;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.common.UI.MyImageIcon;
import com.common.UI.MyPanel;

public abstract class PanelTheme implements Theme {

	protected MyPanel mypanel;
	protected Dimension d = null, backgroundSize = null;
	protected Color color;
	protected Image backgroundImage = null;

	public PanelTheme(MyPanel mypanel) {
		init(mypanel);
	}

	public PanelTheme(Color color) {
		this.color = color;
		d = new Dimension();
	}

	public PanelTheme(MyPanel mypanel, Color color) {
		this.color = color;
		init(mypanel);
	}

	public PanelTheme(String backgroundImage) {
		this.backgroundImage = new MyImageIcon(backgroundImage).getImage();
		d = new Dimension(this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
	}

	public PanelTheme() {
		backgroundImage = null;
		d = new Dimension();
	}

	public PanelTheme(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		d = new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
		backgroundSize = new Dimension(d);
	}

	@Override
	public void init(Container container) {
		// TODO Auto-generated method stub
		if (container == null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					throw new NullPointerException("mypanel不能为空");
				}
			});
		} else {
			this.mypanel = (MyPanel) container;
			mypanel.setTheme(this);
			d = new Dimension(mypanel.getSize());
		}
	}

	/**
	 * 给myPanel设定大小
	 * 
	 * @param d
	 *            大小
	 */
	public void setSize(int width, int height) {
		setSize(new Dimension(width, height));
	}

	/**
	 * 给myPanel设定大小
	 * 
	 * @param d
	 *            大小
	 */
	public void setSize(Dimension d) {
		if (d != null && mypanel != null) {
			this.d = d;
			if (!d.equals(mypanel.getSize()))
				mypanel.setSize(d);
		}
	}

	/**
	 * 用内置的非空的大小给myPanel设定大小
	 */
	public void setSize() {
		if (d != null && mypanel != null)
			if (!d.equals(mypanel.getSize()))
				mypanel.setSize(d);
	}

	@Override
	public void setBackgroudImage() {
		if (mypanel != null) {
			Graphics g = mypanel.getGraphics();
			BufferedImage image = new BufferedImage(d.width, d.height, 3);
			setBackgroudImage(image.getGraphics());
			g.drawImage(image, 0, 0, null);
		}
	}

	public void paint(Image backgroundImage, Graphics2D g, Color c) {
	}

	public void paint(Image backgroundImage, Graphics2D g) {
	}

	public void paint(Graphics2D g, Color c) {
	}

	public void paint(Graphics2D g) {
	}

	/**
	 * 给窗体设置背景图片
	 * 
	 * @param g
	 *            窗体的画笔
	 */
	public void setBackgroudImage(Graphics g) {
		Graphics g0 = g.create();
		if (color != null) {
			g0.setColor(color);
			g0.fillRect(0, 0, mypanel.getWidth(), mypanel.getHeight());
		}
		if (backgroundImage != null) {
			g0.drawImage(backgroundImage, 0, 0, mypanel.getWidth(), mypanel.getHeight(), null);
		}
	}

	@Override
	public void setAlpha() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setShape() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLogo() {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getDimension() {
		// TODO Auto-generated method stub
		return d;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getBackgroudImage() {
		// TODO Auto-generated method stub
		return backgroundImage;
	}

	@Override
	public Image getLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAlpha() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 释放资源
	 */
	public void dispose() {
		mypanel = null;
		d = null;
		color = null;
		backgroundImage = null;
	}

	public Color getBackground() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
	}
}
