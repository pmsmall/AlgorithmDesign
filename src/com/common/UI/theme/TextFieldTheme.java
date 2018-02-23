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
	 * 初始化需要添加主题的容器
	 * 
	 * @param container
	 */
	public void init(Container container) {
		textField = (MyTextField) container;
		textField.setType(type);
	}

	/**
	 * 给容器设置大小
	 * 
	 * @param d
	 *            窗体大小
	 */
	public void setSize(Dimension d) {
		if (d != null && textField != null) {
			this.d = d;
			if (!d.equals(textField.getSize()))
				textField.setSize(d);
		}
	}

	/**
	 * 给容器设置大小
	 */
	public void setSize() {
		if (d != null && textField != null)
			if (!d.equals(textField.getSize()))
				textField.setSize(d);
	}

	/**
	 * 给容器设置背景图片
	 * 
	 * @param g
	 *            容器的画笔
	 */
	public void setBackgroudImage(Graphics g) {
	}

	/**
	 * 给容器设置背景图片
	 */
	public void setBackgroudImage() {
	}

	/**
	 * 给容器设置透明度
	 */
	public void setAlpha() {
	}

	/**
	 * 给容器设置形状
	 */
	public void setShape() {
	}

	/**
	 * 给容器设置logo
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
		return null;
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
		return null;
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
		return 0;
	}

	/**
	 * 释放资源
	 */
	public void dispose() {
		textField = null;
		d = null;
	}

}
