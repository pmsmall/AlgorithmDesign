package com.common.UI.theme;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.PixelGrabber;

import com.common.UI.MyImageIcon;
import com.common.UI.MyLabel;

public abstract class LabelTheme implements Theme, MouseListener {
	protected MyLabel mylabel;
	protected Dimension d;

	protected Shape shape;
	protected Image backgroundImage;
	protected String text;

	protected int w, h;
	protected float alpha;
	private Color c;

	private stringType sType = stringType.imagePath;

	public LabelTheme(MyLabel mylabel, Image backgroundImage) {
		init(mylabel, backgroundImage);
	}

	public LabelTheme(MyLabel mylabel) {
		init(mylabel);
	}

	public LabelTheme() {
	}

	public LabelTheme(String backgroundImage, stringType sType) {
		switch (sType) {
		case imagePath:
			this.backgroundImage = new MyImageIcon(backgroundImage).getImage();
			initShape();
			d = new Dimension(this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
			break;
		case test:
			text = backgroundImage;
			break;
		default:
			break;
		}
		this.sType = sType;
	}

	public LabelTheme(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		if (backgroundImage == null)
			this.backgroundImage = backgroundImage = new MyImageIcon("image/home/首页_0006_用户头像I.png").getImage();
		initShape();
		this.d = new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
	}

	public void init(Container container, Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		initShape();
		init(container);
	}

	/**
	 * 初始化需要添加主题的容器
	 * 
	 * @param container
	 */
	public void init(Container container) {
		if (container == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					throw new NullPointerException("mylabel不能为空");
				}
			});
		} else
			this.mylabel = (MyLabel) container;

		switch (sType) {
		case imagePath:
			if (backgroundImage == null) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						throw new NullPointerException("background不能为空");
					}
				});
			} else if (backgroundImage != null) {
				w = backgroundImage.getWidth(null);
				h = backgroundImage.getHeight(null);
				if (d == null) {
					d = new Dimension(w, h);
				}
			}
			break;
		case test:
			((MyLabel) container).setText(text);
			d = container.getSize();
			((MyLabel) container).setOpaque(true);
			((MyLabel) container).setBackground(c);
			break;
		default:
			break;
		}
		alpha = 1;

	}

	/**
	 * 给容器设置大小
	 * 
	 * @param d
	 *            窗体大小
	 */
	public void setSize(Dimension d) {
		this.d = d;
		mylabel.setSize(d);
	}

	/**
	 * 给容器设置大小
	 */
	public void setSize() {
		if (d != null)
			mylabel.setSize(d);
	}

	/**
	 * 给容器设置背景图片
	 */
	public void setBackgroudImage(Graphics g) {
		if (backgroundImage != null) {
			if (c != null) {
				Color c0 = g.getColor();
				g.setColor(c);
				g.fillRect(0, 0, mylabel.getWidth(), mylabel.getHeight());
				g.setColor(c0);
			}
			g.drawImage(backgroundImage, 0, 0, mylabel.getWidth(), mylabel.getHeight(), null, mylabel);
		}
	}

	@Override
	public void setBackgroudImage() {
		// TODO Auto-generated method stub

	}

	/**
	 * 给容器设置透明度
	 */
	public void setAlpha() {
		// TODO Auto-generated method stub
	}

	/**
	 * 给容器设置形状
	 */
	public void setShape() {
		// TODO Auto-generated method stub
		// mylabel.setBounds((Rectangle)shape);
	}

	/**
	 * 给容器设置logo
	 */
	public void setLogo() {
		// TODO Auto-generated method stub
	}

	/**
	 * 获取大小矩形
	 * 
	 * @return 大小的矩形
	 */
	public Rectangle getRectangle() {
		return (Rectangle) shape;
	}

	/**
	 * 获取该皮肤的尺寸
	 * 
	 * @return 该皮肤的尺寸
	 */
	public Dimension getDimension() {
		// TODO Auto-generated method stub
		return d;
	}

	/**
	 * 获取图片
	 * 
	 * @return 图片
	 */
	public Image getImage() {
		// TODO Auto-generated method stub
		return backgroundImage;
	}

	/**
	 * 获取背景图片
	 * 
	 * @return 背景图片
	 */
	public Image getBackgroudImage() {
		// TODO Auto-generated method stub
		return backgroundImage;
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public Image getLog() {
		// TODO Auto-generated method stub
		return backgroundImage;
	}

	/**
	 * 获取透明度
	 * 
	 * @return 透明度
	 */
	public int getAlpha() {
		// TODO Auto-generated method stub
		return (int) alpha;
	}

	protected int getAlpha(int pixel) {
		return (pixel >> 24) & 0xff;
	}

	protected void initShape() {

		PixelGrabber pgb = new PixelGrabber(backgroundImage, 0, 0, -1, -1, true);
		try {
			pgb.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int pixels[] = (int[]) pgb.getPixels();

		int alphaList[][] = new int[h][w];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				alphaList[i][j] = (getAlpha(pixels[j + i * w]) == 0) ? 0 : 1;
			}
		}
		Rectangle rectangle = new Rectangle();
		int temp = 0;

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (alphaList[i][j] == 1) {
					if (temp == 0)
						temp = j;
					else if (j == w) {
						if (temp == 0) {
							Rectangle rectemp = new Rectangle(j, i, 1, 1);
							rectangle.add(new Rectangle(rectemp));
						} else {
							Rectangle rectemp = new Rectangle(temp, i, j - temp, 1);
							rectangle.add(new Rectangle(rectemp));
							temp = 0;
						}
					}
				} else {
					if (temp != 0) {
						Rectangle rectemp = new Rectangle(temp, i, j - temp, 1);
						rectangle.add(new Rectangle(rectemp));
						temp = 0;
					}
				}
			}
			temp = 0;
		}
		shape = rectangle;
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Invoked when the mouse enters a component.
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Invoked when the mouse exits a component.
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * 释放资源
	 */
	public void dispose() {
		mylabel = null;
		d = null;
		shape = null;
		backgroundImage = null;
	}

	public enum stringType {
		/**
		 * 
		 */
		imagePath,
		/**
		 * 
		 */
		test;
	}

	public void setColor(Color c) {
		if (c != null)
			this.c = c;
	}

	abstract public boolean HasListener();
}
