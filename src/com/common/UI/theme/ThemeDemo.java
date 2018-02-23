package com.common.UI.theme;

import java.awt.Image;
import javax.swing.ImageIcon;

import com.common.UI.MyFrame;


public class ThemeDemo extends FrameTheme {

	protected String backgroudImageDirectory = "image//background-demo.png";
	protected Image backgroudImage = new ImageIcon(backgroudImageDirectory)
			.getImage();

	public ThemeDemo(MyFrame myframe, Image backgroundImage) {
		super(myframe, backgroundImage);
		// TODO Auto-generated constructor stub
	}

	public ThemeDemo(MyFrame myframe) {
		super(myframe);
		// TODO Auto-generated constructor stub
		super.init(myframe, backgroudImage);
	}

}
