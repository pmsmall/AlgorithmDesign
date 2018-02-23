package com.common.UI.theme;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.common.UI.MyButton;
import com.common.UI.MyImageIcon;
import com.common.UI.listener.ButtonListener;
import com.common.UI.listener.ButtonStatusChangeEvent;
import com.common.UI.listener.ButtonStatusListener;

/**
 * 给MyButton对象设定样式的类
 * 
 * <p>
 * <strong> 需要override的方法有：</strong>
 * </p>
 * 
 * <p>
 * public void onRelease(MouseEvent e); 设定鼠标离开时的事件
 * </p>
 * <p>
 * public void onPress(MouseEvent e); 设定鼠标按下的事件
 * </p>
 * <p>
 * public void onClick(MouseEvent e); 设定鼠标点击的事件
 * </p>
 * <p>
 * public void leave(MouseEvent e); 设定鼠标离开的事件
 * </p>
 * <p>
 * public void hover(MouseEvent e); 设定鼠标悬浮的事件
 * </p>
 * Frank
 * 
 */
public abstract class ButtonTheme implements Theme, MouseListener, ButtonListener {

	protected MyButton mybutton;
	protected Dimension d;
	protected Image releasedImage, pressedImage, hoverImage;
	protected boolean ifInButton = false, ifPressButton = false;
	protected Color c = null;
	protected ButtonStatusListener[] slistener;
	protected int listenerNumber = 0;
	/**
	 * this variable describes the state of this {@code MyButton}.
	 */
	protected buttonStatus status = buttonStatus.release;
	protected buttonActiveStatus activeStatus = buttonActiveStatus.normal;

	/**
	 * 初始化一个button主题
	 * 
	 * @param myButton
	 *            需要添加主题的myButton
	 */
	public ButtonTheme(MyButton myButton) {
		init(myButton);
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param myButton
	 *            需要添加主题的myButton
	 */
	public ButtonTheme() {
		d = new Dimension();
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param releasedImage
	 *            未按下去时，按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public ButtonTheme(Image releasedImage, Image pressedImage, Image hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param image
	 *            按钮的图片
	 */
	public ButtonTheme(Image image) {
		initImage(image);
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param image
	 */
	public ButtonTheme(String image) {
		initImage(image);
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param releasedImage
	 *            未按下去时，按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public ButtonTheme(Image releasedImage, Image pressedImage, Image hoverImage, Color c) {
		initImage(releasedImage, pressedImage, hoverImage);
		this.c = c;
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param releasedImage
	 *            未按下去时，按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public ButtonTheme(String releasedImage, String pressedImage, String hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param myButton
	 *            需要添加主题的myButton
	 * @param releasedImage
	 *            未按下去时，按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public ButtonTheme(MyButton myButton, String releasedImage, String pressedImage, String hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
		init(myButton);
	}

	/**
	 * 初始化一个button主题
	 * 
	 * @param myButton
	 *            需要添加主题的myButton
	 * @param releasedImage
	 *            未按下去时，按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public ButtonTheme(MyButton myButton, Image releasedImage, Image pressedImage, Image hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
		init(myButton);
	}

	/**
	 * 
	 * @param releasedImage
	 *            未按下去时，按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public void initImage(Image releasedImage, Image pressedImage, Image hoverImage) {
		if (releasedImage != null)
			this.releasedImage = releasedImage;
		else {
		}
		if (pressedImage != null)
			this.pressedImage = pressedImage;
		else {
		}
		if (hoverImage != null)
			this.hoverImage = hoverImage;
		else {
		}
		d = new Dimension(releasedImage.getWidth(null), releasedImage.getHeight(null));
	}

	/**
	 * 
	 * @param image
	 *            按钮的图片
	 */
	public void initImage(Image image) {
		if (image != null)
			releasedImage = pressedImage = hoverImage = image;
		else {
		}
		d = new Dimension(releasedImage.getWidth(null), releasedImage.getHeight(null));
	}

	/**
	 * 
	 * @param image
	 *            按钮的图片
	 * @param pressedImage
	 *            按下去时按钮的图片
	 * @param hoverImage
	 *            悬停时按钮的图片
	 */
	public void initImage(String image, String pressedImage, String hoverImage) {

		if (releasedImage != null) {
			this.releasedImage = new MyImageIcon(releasedImage).getImage();
		} else if (releasedImage == null && pressedImage == null && hoverImage == null) {

		}
		if (hoverImage != null) {
			this.hoverImage = new MyImageIcon(hoverImage).getImage();
			if (releasedImage == null)
				this.releasedImage = this.hoverImage;
		} else {
			this.hoverImage = this.releasedImage;
		}
		if (pressedImage != null) {
			this.pressedImage = new MyImageIcon(pressedImage).getImage();
			if (releasedImage == null)
				this.releasedImage = this.pressedImage;
		} else {
			this.pressedImage = this.releasedImage;
		}

		if (this.releasedImage != null)
			d = new Dimension(this.releasedImage.getWidth(null), this.releasedImage.getHeight(null));
	}

	/**
	 * 
	 * @param image
	 *            按钮的图片
	 */
	public void initImage(String image) {
		if (image != null) {
			releasedImage = pressedImage = hoverImage = new MyImageIcon(image).getImage();
		} else if (releasedImage == null && pressedImage == null && hoverImage == null) {

		}
		if (releasedImage != null)
			d = new Dimension(releasedImage.getWidth(null), releasedImage.getHeight(null));
	}

	/**
	 * 
	 * 初始化一个button主题
	 * 
	 * @param myButton
	 *            需要添加主题的myButton
	 */
	public void init(Container container) {
		if (container == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					throw new NullPointerException("myframe不能为空");
				}
			});
		} else {
			this.mybutton = (MyButton) container;
			mybutton.init(this);
			setSize();

			mybutton.addMouseListener(this);
			mybutton.repaint();
		}
	}

	/**
	 * 给mybutton设定大小
	 * 
	 * @param d
	 *            大小
	 */
	public void setSize(Dimension d) {
		if (d != null && mybutton != null) {
			this.d = d;
			if (!d.equals(mybutton.getSize()))
				mybutton.setSize(d);
		}
	}

	/**
	 * 用内置的非空的大小给mybutton设定大小
	 */
	public void setSize() {
		if (d != null && mybutton != null)
			if (!d.equals(mybutton.getSize()))
				mybutton.setSize(d);
	}

	/**
	 * 给mybutton设定背景图片
	 */
	public void setBackgroudImage() {
		if (mybutton != null) {
			Graphics2D g = mybutton.getGraphics2D();
			if(g==null)
				return;
			BufferedImage image = new BufferedImage(d.width, d.height, 3);
			setBackgroudImage(image.getGraphics());
			g.drawImage(image, 0, 0, null);
		}
	}

	/**
	 * 给mybutton设定背景图片
	 * 
	 * @param g
	 *            mybutton的画笔
	 */
	public void setBackgroudImage(Graphics g) {
		Graphics g0 = g.create();
		if (c != null) {
			Color c0 = g0.getColor();
			g0.setColor(c);
			g0.fillRect(0, 0, mybutton.getWidth(), mybutton.getHeight());
			g0.setColor(c0);
		}
		switch (status) {
		case release:
			if (releasedImage != null) {
				g0.drawImage(releasedImage, 0, 0, mybutton.getWidth(), mybutton.getHeight(), null);
			}
			break;
		case press:
			if (pressedImage != null) {
				g0.drawImage(pressedImage, 0, 0, mybutton.getWidth(), mybutton.getHeight(), null);
			}
			break;
		case hover:
			if (hoverImage != null) {
				g0.drawImage(hoverImage, 0, 0, mybutton.getWidth(), mybutton.getHeight(), null);
			}
			break;
		default:
			break;
		}

	}

	/**
	 * 给mybutton设定透明度
	 */
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
		return null;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getBackgroudImage() {
		// TODO Auto-generated method stub
		return null;
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

	boolean isClicked = false;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isClicked) {
			onClick(e);
			isClicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isClicked = false;
		mybutton.grabFocus();
		status = buttonStatus.press;
		setBackgroudImage();
		statusChange();
		ifPressButton = true;
		onPress(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (ifInButton) {
			status = buttonStatus.hover;
			statusChange();
			if (ifPressButton)
				if (!isClicked) {
					onClick(e);
					isClicked = true;
				}

		} else {
			status = buttonStatus.release;
			statusChange();
		}
		setBackgroudImage();
		ifPressButton = false;
		onRelease(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ifInButton = true;
		status = buttonStatus.hover;
		setBackgroudImage();
		statusChange();
		hover(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ifInButton = false;
		status = buttonStatus.release;
		statusChange();
		setBackgroudImage();
		leave(e);
	}

	@Override
	public void onRelease(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hover(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setLisener() {
		mybutton.addMouseListener(this);
	}

	/**
	 * 释放资源
	 */
	public void dispose() {
		mybutton = null;
		d = null;
		releasedImage = null;
		pressedImage = null;
		hoverImage = null;
		if (slistener != null) {
			clearStatusListener();
			slistener = null;
		}
	}

	/**
	 * Get the state of this button.The states include the state of releasing,
	 * the state of pressing and the state of hovering.
	 * 
	 * @return The state of this button. The state may be the state of
	 *         releasing, the state of pressing ors the state of hovering
	 * @see buttonStatus
	 */
	public buttonStatus getSatus() {
		return status;
	}

	public buttonActiveStatus getActiveStatus() {
		return activeStatus;
	}

	public boolean isActive() {
		return activeStatus == buttonActiveStatus.active;
	}

	public void setActiveStatus(buttonActiveStatus s) {
		activeStatus = s;
	}

	/**
	 * This enum contains the states that represent the state of the
	 * {@code MyButton}. The states including releasing, pressing and hover.
	 * Programer can use them to describe different states of the button.
	 * 
	 * @author 康天楠
	 * @since jdk_8
	 * @version 1.0
	 */
	public enum buttonStatus {
		/**
		 * 按钮在释放状态
		 */
		release,

		/**
		 * 按钮在按下状态
		 */
		press,

		/**
		 * 鼠标在按钮上悬浮
		 */
		hover, //

	}

	public enum buttonActiveStatus {
		/**
		 * 按键被长期触发
		 */
		active,

		/**
		 * 按键失去长期触发状态
		 */
		normal
	}
	// protected void finalize() throws Throwable {
	// // TODO Auto-generated method stub
	// System.out.println("system is destroying this " + this);
	// super.finalize();
	// }

	public void addButtonStatusListener(ButtonStatusListener listener) {
		if (slistener == null) {
			slistener = new ButtonStatusListener[1];
			slistener[0] = listener;
			listenerNumber++;
		} else {
			if (listenerNumber == slistener.length) {
				int newCapacity = slistener.length + 1;
				growOfListener(newCapacity);
			}
			slistener[listenerNumber++] = listener;
		}
	}

	void growOfListener(int newCapacity) {
		this.slistener = Arrays.copyOf(this.slistener, newCapacity);
	}

	protected void doListener(ButtonStatusChangeEvent e) {
		if (slistener != null)
			for (int i = 0; i < slistener.length; i++) {
				slistener[i].statusChange(e);
			}
	}

	protected void statusChange() {
		doListener(new ButtonStatusChangeEvent(activeStatus, status, ButtonStatusChangeEvent.STATUS_CHANGE));
	}

	public void clearStatusListener() {
		for (int i = 0; i < slistener.length; i++) {
			slistener[i] = null;
		}
		listenerNumber = 0;
	}
}
