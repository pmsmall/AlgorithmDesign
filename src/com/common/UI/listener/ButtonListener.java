package com.common.UI.listener;

import java.awt.event.MouseEvent;

public interface ButtonListener {

	/**
	 * ����¼�
	 */
	public void onClick(MouseEvent e);

	/**
	 * �����ͣ�¼�
	 */
	public void hover(MouseEvent e);

	/**
	 * ��������뿪
	 */
	public void leave(MouseEvent e);

	/**
	 * ��갴��
	 */
	public void onPress(MouseEvent e);

	/**
	 * ����ɿ�
	 */
	public void onRelease(MouseEvent e);

}
