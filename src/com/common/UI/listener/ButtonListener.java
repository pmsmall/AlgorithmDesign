package com.common.UI.listener;

import java.awt.event.MouseEvent;

public interface ButtonListener {

	/**
	 * 点击事件
	 */
	public void onClick(MouseEvent e);

	/**
	 * 鼠标悬停事件
	 */
	public void hover(MouseEvent e);

	/**
	 * 鼠标悬移离开
	 */
	public void leave(MouseEvent e);

	/**
	 * 鼠标按下
	 */
	public void onPress(MouseEvent e);

	/**
	 * 鼠标松开
	 */
	public void onRelease(MouseEvent e);

}
