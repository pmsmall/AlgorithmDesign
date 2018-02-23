package com.common.UI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.plaf.ScrollBarUI;

public class MyScrollPanelBarUI extends ScrollBarUI {

	@SuppressWarnings("unused")
	private Image image;

	/**
	 * ����image���Գ�ʼ��MyScrollPanelBar��UI
	 * @param image ���Գ�ʼ��UI�� image
	 */
	public MyScrollPanelBarUI(Image image) {
		this.image = image;
	}

	/**
	 * ����imageicon��ȡ��image������ʼ��MyScrollPanelBar��UI
	 * @param imageIcon ������ȡImage��ImageIcon
	 */
	public MyScrollPanelBarUI(ImageIcon imageIcon) {
		this.image = imageIcon.getImage();
	}

	/**
	 * Paints the specified component appropriately for the look and feel. This
	 * method is invoked from the <code>ComponentUI.update</code> method when
	 * the specified component is being painted. Subclasses should override this
	 * method and use the specified <code>Graphics</code> object to render the
	 * content of the component.
	 * 
	 * @param g
	 *            the <code>Graphics</code> context in which to paint
	 * @param c
	 *            the component being painted; this argument is often ignored,
	 *            but might be used if the UI object is stateless and shared by
	 *            multiple components
	 * 
	 * @see #update
	 */
	public void paint(Graphics g, JComponent c) {

	}

}
