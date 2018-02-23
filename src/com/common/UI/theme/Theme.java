package com.common.UI.theme;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public interface Theme {

	/**
	 * 初始化需要添加主题的容器
	 * 
	 * @param container
	 */
	public void init(Container container);

	/**
	 * 给容器设置大小
	 * 
	 * @param d
	 *            窗体大小
	 */
	public void setSize(Dimension d);

	/**
	 * 给容器设置大小
	 */
	public void setSize();

	/**
	 * 给容器设置背景图片
	 * 
	 * @param g
	 *            容器的画笔
	 */
	public void setBackgroudImage(Graphics g);

	/**
	 * 给容器设置背景图片
	 */
	public void setBackgroudImage();

	/**
	 * 给容器设置透明度
	 */
	public void setAlpha();

	/**
	 * 给容器设置形状
	 */
	public void setShape();

	/**
	 * 给容器设置logo
	 */
	public void setLogo();

	/**
	 * 获取大小矩形
	 * 
	 * @return 大小的矩形
	 */
	public Rectangle getRectangle();

	/**
	 * 获取该皮肤的尺寸
	 * 
	 * @return 该皮肤的尺寸
	 */
	public Dimension getDimension();

	/**
	 * 获取图片
	 * 
	 * @return 图片
	 */
	public Image getImage();

	/**
	 * 获取背景图片
	 * 
	 * @return 背景图片
	 */
	public Image getBackgroudImage();

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public Image getLog();

	/**
	 * 获取透明度
	 * 
	 * @return 透明度
	 */
	public int getAlpha();

	/**
	 * 释放资源
	 */
	public void dispose();

}
