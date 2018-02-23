package com.main.UI.theme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import com.common.UI.MyButton;
import com.common.UI.listener.ButtonStatusChangeEvent;
import com.common.UI.theme.ButtonTheme;

public class CardLayoutButtonTheme extends ButtonTheme {

	CardLayoutButtonThemeManager father = null;
	boolean hoverable;

	public CardLayoutButtonTheme() {
		super();
		hoverable = false;
	}

	public CardLayoutButtonTheme(Image background, Image activeBackground) {
		super(background, activeBackground, background);
		hoverable = false;
	}

	public CardLayoutButtonTheme(Image background, Image activeBackground, Image hoverBackground) {
		super(background, activeBackground, hoverBackground);
		hoverable = true;
	}

	void initCardLayoutButtonThemeManager(CardLayoutButtonThemeManager father) {
		this.father = father;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		father.deleteTheme(this);
	}

	/**
	 * 给mybutton设定背景图片
	 */
	public void setBackgroudImage() {
		if (mybutton != null) {
			Graphics2D g = mybutton.getGraphics2D();
			if (c != null) {
				Color c0 = g.getColor();
				g.setColor(c);
				g.fillRect(0, 0, mybutton.getWidth(), mybutton.getHeight());
				g.setColor(c0);
			}
			setBackgroudImage(g);
		}
	}

	/**
	 * 给mybutton设定背景图片
	 * 
	 * @param g
	 *            mybutton的画笔
	 */
	public void setBackgroudImage(Graphics g) {
		super.setBackgroudImage(g);
		switch (getActiveStatus()) {
		case normal:
			if (!hoverable) {
				if (releasedImage != null)
					if (c != null) {
						Color c0 = g.getColor();
						g.setColor(c);
						g.fillRect(0, 0, mybutton.getWidth(), mybutton.getHeight());
						g.setColor(c0);
					}
				g.drawImage(releasedImage, 0, 0, mybutton.getWidth(), mybutton.getHeight(), null);
			}
			break;
		case active:
			if (pressedImage != null) {
				if (c != null) {
					Color c0 = g.getColor();
					g.setColor(c);
					g.fillRect(0, 0, mybutton.getWidth(), mybutton.getHeight());
					g.setColor(c0);
				}
				g.drawImage(pressedImage, 0, 0, mybutton.getWidth(), mybutton.getHeight(), null);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mybutton.grabFocus();
		if (father != null)
			if (getActiveStatus() != buttonActiveStatus.active) {
				setActiveStatus(buttonActiveStatus.active);
				activeStatusChange();
				father.setActiveButton(this);
			}
		onPress(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
	}

	public void repaint() {
		mybutton.repaint();
	}

	MyButton getMyButton() {
		return mybutton;
	}

	protected void activeStatusChange() {
		doListener(new ButtonStatusChangeEvent(getActiveStatus(), getSatus(),
				ButtonStatusChangeEvent.ACTIVESTATUS_CHANGE));
	}
}
