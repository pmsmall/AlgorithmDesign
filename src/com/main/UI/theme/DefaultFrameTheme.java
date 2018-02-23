package com.main.UI.theme;

import java.awt.Image;

import com.common.UI.MyFrame;
import com.common.UI.theme.FrameTheme;

public class DefaultFrameTheme extends FrameTheme {

	/**
	 * 初始化一个frame主题
	 * 
	 * @param myframe
	 *            需要添加主题的myframe
	 */
	public DefaultFrameTheme(MyFrame myframe) {
		super(myframe);
	}

	/**
	 * 初始化一个frame主题
	 * 
	 * @param myframe
	 *            需要添加主题的myframe
	 */
	public DefaultFrameTheme(MyFrame myframe, Image backgroundImage) {
		super(myframe, backgroundImage);
	}

	public DefaultFrameTheme(Image backgroundImage) {
		super(backgroundImage);
	}

	public DefaultFrameTheme(Image backgroundImage, int scale, scaleType sType) {
		super(backgroundImage, scale, sType);
	}

	public DefaultFrameTheme(String backgroundImage) {
		super(backgroundImage);
	}

	public DefaultFrameTheme(String backgroundImage, int scale, scaleType sType) {
		super(backgroundImage, scale, sType);
	}

}
