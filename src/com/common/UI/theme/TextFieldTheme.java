package com.common.UI.theme;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.common.UI.MyTextField;

public abstract class TextFieldTheme implements Theme {
	private final int type;
	public static final int KEY = 0;
	public static final int ACCOUNT = 1;
	public static final int TEXT = 2;
	private MyTextField textField;
	private Dimension d;

	public TextFieldTheme(int type) {
		this.type = type;
	}

	/**
	 * ��ʼ����Ҫ������������
	 * 
	 * @param container
	 */
	public void init(Container container) {
		textField = (MyTextField) container;
		textField.setType(type);
	}

	/**
	 * ���������ô�С
	 * 
	 * @param d
	 *            �����С
	 */
	public void setSize(Dimension d) {
		if (d != null && textField != null) {
			this.d = d;
			if (!d.equals(textField.getSize()))
				textField.setSize(d);
		}
	}

	/**
	 * ���������ô�С
	 */
	public void setSize() {
		if (d != null && textField != null)
			if (!d.equals(textField.getSize()))
				textField.setSize(d);
	}

	/**
	 * ���������ñ���ͼƬ
	 * 
	 * @param g
	 *            �����Ļ���
	 */
	public void setBackgroudImage(Graphics g) {
	}

	/**
	 * ���������ñ���ͼƬ
	 */
	public void setBackgroudImage() {
	}

	/**
	 * ����������͸����
	 */
	public void setAlpha() {
	}

	/**
	 * ������������״
	 */
	public void setShape() {
	}

	/**
	 * ����������logo
	 */
	public void setLogo() {
	}

	/**
	 * ��ȡ��С����
	 * 
	 * @return ��С�ľ���
	 */
	public Rectangle getRectangle() {
		return null;
	}

	/**
	 * ��ȡ��Ƥ���ĳߴ�
	 * 
	 * @return ��Ƥ���ĳߴ�
	 */
	public Dimension getDimension() {
		return null;
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @return ͼƬ
	 */
	public Image getImage() {
		return null;
	}

	/**
	 * ��ȡ����ͼƬ
	 * 
	 * @return ����ͼƬ
	 */
	public Image getBackgroudImage() {
		return null;
	}

	/**
	 * ��ȡlogo
	 * 
	 * @return logo
	 */
	public Image getLog() {
		return null;
	}

	/**
	 * ��ȡ͸����
	 * 
	 * @return ͸����
	 */
	public int getAlpha() {
		return 0;
	}

	/**
	 * �ͷ���Դ
	 */
	public void dispose() {
		textField = null;
		d = null;
	}

}
