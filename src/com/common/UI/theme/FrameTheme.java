package com.common.UI.theme;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.PixelGrabber;

import com.common.UI.MyFrame;
import com.common.UI.MyImageIcon;

public abstract class FrameTheme implements Theme {

	protected MyFrame myframe;
	protected Dimension d;

	protected Shape shape;
	protected Image backgroundImage;
	// private Image originalBackgroundImage;

	protected int w, h;
	protected float alpha;
	private scaleType sType = scaleType.magnify;
	private int scale = 1;

	/**
	 * 初始化一个frame主题
	 * 
	 * @param myframe
	 *            需要添加主题的myframe
	 */
	public FrameTheme(MyFrame myframe) {
		init(myframe);
	}

	/**
	 * 初始化一个frame主题
	 * 
	 * @param myframe
	 *            需要添加主题的myframe
	 */
	public FrameTheme(MyFrame myframe, Image backgroundImage) {
		init(myframe, backgroundImage);
	}

	public FrameTheme(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public FrameTheme(Image backgroundImage, int scale, scaleType sType) {
		this.backgroundImage = backgroundImage;
		this.scale = scale;
		this.sType = sType;
	}

	public FrameTheme(String backgroundImage) {
		this.backgroundImage = new MyImageIcon(backgroundImage).getImage();
	}

	public FrameTheme(String backgroundImage, int scale, scaleType sType) {
		this.backgroundImage = new MyImageIcon(backgroundImage).getImage();
		this.scale = scale;
		this.sType = sType;
	}

	/**
	 * 初始化myframe
	 * 
	 * @param myframe
	 */
	public void init(Container container) {
		if (container == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					throw new NullPointerException("myframe不能为空");
				}
			});
		} else
			this.myframe = (MyFrame) container;
		if (backgroundImage == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					throw new NullPointerException("background不能为空");
				}
			});
		} else {
			if (sType == scaleType.shrink) {
				w = backgroundImage.getWidth(null) / scale;
				h = backgroundImage.getHeight(null) / scale;
			} else {
				w = backgroundImage.getWidth(null) * scale;
				h = backgroundImage.getHeight(null) * scale;
			}
			initShape();
		}
		alpha = 1;
		if (d == null) {
			d = new Dimension(w, h);
		}
	}

	/**
	 * 初始化myframe
	 * 
	 * @param myframe
	 * @param backgroundImage
	 */
	public void init(MyFrame myframe, Image backgroundImage) {
		// originalBackgroundImage = backgroundImage;
		// this.backgroundImage = originalBackgroundImage;
		this.backgroundImage = backgroundImage;
		init(myframe);
	}

	/**
	 * 初始化myframe
	 * 
	 * @param myframe
	 *            应用皮肤的窗体
	 */
	public void setFrame(MyFrame myframe) {
		init(myframe);
	}

	/**
	 * 给窗体设置大小
	 * 
	 * @param d
	 *            窗体大小
	 */
	public void setSize(Dimension d) {
		if (!this.d.equals(d))
			this.d = d;
		if (myframe != null) {
			if (!myframe.getSize().equals(d))
				myframe.setSize(d);
		}
	}

	/**
	 * 给窗体设置大小
	 */
	public void setSize() {
		if (d != null && myframe != null)
			setSize(this.d);
	}

	/**
	 * 给窗体设置背景图片
	 */
	public void setBackgroudImage() {
		if (myframe != null) {
			Graphics2D g = myframe.getGraphics2D();
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, myframe.getWidth(), myframe.getHeight(), null);
			}
		}
	}

	/**
	 * 给窗体设置背景图片
	 * 
	 * @param g
	 *            窗体的画笔
	 */
	public void setBackgroudImage(Graphics g) {
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, myframe.getWidth(), myframe.getHeight(), null);
		}
	}

	/**
	 * 给窗体设置透明度
	 */
	public void setAlpha() {
		System.out.println(alpha);
		myframe.setOpacity(alpha);
	}

	/**
	 * 给窗体设置形状
	 */
	public void setShape() {
		myframe.setShape(shape);
	}

	/**
	 * 给窗体设置logo
	 */
	public void setLogo() {
	}

	/**
	 * 获取大小矩形
	 * 
	 * @return 大小的矩形
	 */
	public Rectangle getRectangle() {
		return null;
	}

	/**
	 * 获取该皮肤的尺寸
	 * 
	 * @return 该皮肤的尺寸
	 */
	public Dimension getDimension() {
		return d;
	}

	/**
	 * 获取图片
	 * 
	 * @return 图片
	 */
	public Image getImage() {
		return null;
	}

	/**
	 * 获取背景图片
	 * 
	 * @return 背景图片
	 */
	public Image getBackgroudImage() {
		return backgroundImage;
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public Image getLog() {
		return null;
	}

	/**
	 * 获取透明度
	 * 
	 * @return 透明度
	 */
	public int getAlpha() {
		return (int) (alpha * 255);
	}

	protected int getAlpha(int pixel) {
		return (pixel >> 24) & 0xff;
	}

	protected void initShape() {

		PixelGrabber pgb = new PixelGrabber(backgroundImage, 0, 0, -1, -1, true);
		try {
			pgb.grabPixels();
			int pixels[] = (int[]) pgb.getPixels();

			int alphaList[][] = new int[h][w];

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					alphaList[i][j] = (getAlpha(pixels[j + i * w]) >= 170) ? 1 : 0;
				}
			}
			Area rectangle = new Area();
			int temp = 0;

			// 有一个bug，就是图片不能顶满//解决了
			if (scale == 1 || sType != scaleType.magnify)
				for (int i = 0; i < h; i += scale) {
					for (int j = 0; j < w; j += scale) {
						if (alphaList[i][j] == 1) {
							if (temp == 0)
								temp = j;
						} else {
							if (temp != 0) {
								Rectangle rectemp = new Rectangle(temp, i, j - temp, 1);
								rectangle.add(new Area(rectemp));
								temp = 0;
							}
						}
					}
					if (temp != 0) {
						Rectangle rectemp = new Rectangle(temp, i, w, 1);
						rectangle.add(new Area(rectemp));
						temp = 0;
					}
				}
			shape = rectangle;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 释放资源
	 */
	public void dispose() {
		myframe = null;
		d = null;
		shape = null;
		backgroundImage = null;
	}

	// public void finalize() {
	// System.out.println("system is destroying " + this);
	// }
	/**
	 * include type of magnify and shrink
	 * 
	 * @author 康天楠
	 * @since jdk_8
	 */
	public enum scaleType {
		/**
		 * 
		 */
		magnify,
		/**
		 * 
		 */
		shrink;
	}
}
