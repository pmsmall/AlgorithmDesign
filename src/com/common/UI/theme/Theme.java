package com.common.UI.theme;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public interface Theme {

	/**
	 * ��ʼ����Ҫ������������
	 * 
	 * @param container
	 */
	public void init(Container container);

	/**
	 * ���������ô�С
	 * 
	 * @param d
	 *            �����С
	 */
	public void setSize(Dimension d);

	/**
	 * ���������ô�С
	 */
	public void setSize();

	/**
	 * ���������ñ���ͼƬ
	 * 
	 * @param g
	 *            �����Ļ���
	 */
	public void setBackgroudImage(Graphics g);

	/**
	 * ���������ñ���ͼƬ
	 */
	public void setBackgroudImage();

	/**
	 * ����������͸����
	 */
	public void setAlpha();

	/**
	 * ������������״
	 */
	public void setShape();

	/**
	 * ����������logo
	 */
	public void setLogo();

	/**
	 * ��ȡ��С����
	 * 
	 * @return ��С�ľ���
	 */
	public Rectangle getRectangle();

	/**
	 * ��ȡ��Ƥ���ĳߴ�
	 * 
	 * @return ��Ƥ���ĳߴ�
	 */
	public Dimension getDimension();

	/**
	 * ��ȡͼƬ
	 * 
	 * @return ͼƬ
	 */
	public Image getImage();

	/**
	 * ��ȡ����ͼƬ
	 * 
	 * @return ����ͼƬ
	 */
	public Image getBackgroudImage();

	/**
	 * ��ȡlogo
	 * 
	 * @return logo
	 */
	public Image getLog();

	/**
	 * ��ȡ͸����
	 * 
	 * @return ͸����
	 */
	public int getAlpha();

	/**
	 * �ͷ���Դ
	 */
	public void dispose();

}
