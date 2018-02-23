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
 * ��MyButton�����趨��ʽ����
 * 
 * <p>
 * <strong> ��Ҫoverride�ķ����У�</strong>
 * </p>
 * 
 * <p>
 * public void onRelease(MouseEvent e); �趨����뿪ʱ���¼�
 * </p>
 * <p>
 * public void onPress(MouseEvent e); �趨��갴�µ��¼�
 * </p>
 * <p>
 * public void onClick(MouseEvent e); �趨��������¼�
 * </p>
 * <p>
 * public void leave(MouseEvent e); �趨����뿪���¼�
 * </p>
 * <p>
 * public void hover(MouseEvent e); �趨����������¼�
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
	 * ��ʼ��һ��button����
	 * 
	 * @param myButton
	 *            ��Ҫ��������myButton
	 */
	public ButtonTheme(MyButton myButton) {
		init(myButton);
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param myButton
	 *            ��Ҫ��������myButton
	 */
	public ButtonTheme() {
		d = new Dimension();
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param releasedImage
	 *            δ����ȥʱ����ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
	 */
	public ButtonTheme(Image releasedImage, Image pressedImage, Image hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param image
	 *            ��ť��ͼƬ
	 */
	public ButtonTheme(Image image) {
		initImage(image);
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param image
	 */
	public ButtonTheme(String image) {
		initImage(image);
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param releasedImage
	 *            δ����ȥʱ����ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
	 */
	public ButtonTheme(Image releasedImage, Image pressedImage, Image hoverImage, Color c) {
		initImage(releasedImage, pressedImage, hoverImage);
		this.c = c;
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param releasedImage
	 *            δ����ȥʱ����ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
	 */
	public ButtonTheme(String releasedImage, String pressedImage, String hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param myButton
	 *            ��Ҫ��������myButton
	 * @param releasedImage
	 *            δ����ȥʱ����ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
	 */
	public ButtonTheme(MyButton myButton, String releasedImage, String pressedImage, String hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
		init(myButton);
	}

	/**
	 * ��ʼ��һ��button����
	 * 
	 * @param myButton
	 *            ��Ҫ��������myButton
	 * @param releasedImage
	 *            δ����ȥʱ����ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
	 */
	public ButtonTheme(MyButton myButton, Image releasedImage, Image pressedImage, Image hoverImage) {
		initImage(releasedImage, pressedImage, hoverImage);
		init(myButton);
	}

	/**
	 * 
	 * @param releasedImage
	 *            δ����ȥʱ����ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
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
	 *            ��ť��ͼƬ
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
	 *            ��ť��ͼƬ
	 * @param pressedImage
	 *            ����ȥʱ��ť��ͼƬ
	 * @param hoverImage
	 *            ��ͣʱ��ť��ͼƬ
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
	 *            ��ť��ͼƬ
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
	 * ��ʼ��һ��button����
	 * 
	 * @param myButton
	 *            ��Ҫ��������myButton
	 */
	public void init(Container container) {
		if (container == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					throw new NullPointerException("myframe����Ϊ��");
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
	 * ��mybutton�趨��С
	 * 
	 * @param d
	 *            ��С
	 */
	public void setSize(Dimension d) {
		if (d != null && mybutton != null) {
			this.d = d;
			if (!d.equals(mybutton.getSize()))
				mybutton.setSize(d);
		}
	}

	/**
	 * �����õķǿյĴ�С��mybutton�趨��С
	 */
	public void setSize() {
		if (d != null && mybutton != null)
			if (!d.equals(mybutton.getSize()))
				mybutton.setSize(d);
	}

	/**
	 * ��mybutton�趨����ͼƬ
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
	 * ��mybutton�趨����ͼƬ
	 * 
	 * @param g
	 *            mybutton�Ļ���
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
	 * ��mybutton�趨͸����
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
	 * �ͷ���Դ
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
	 * @author �����
	 * @since jdk_8
	 * @version 1.0
	 */
	public enum buttonStatus {
		/**
		 * ��ť���ͷ�״̬
		 */
		release,

		/**
		 * ��ť�ڰ���״̬
		 */
		press,

		/**
		 * ����ڰ�ť������
		 */
		hover, //

	}

	public enum buttonActiveStatus {
		/**
		 * ���������ڴ���
		 */
		active,

		/**
		 * ����ʧȥ���ڴ���״̬
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
