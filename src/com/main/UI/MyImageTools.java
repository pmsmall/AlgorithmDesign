package com.main.UI;

import java.awt.Image;

import com.common.UI.MyImageIcon;

public class MyImageTools {

	public Image getImage(String imagePath) {
		return new MyImageIcon(imagePath).getImage();
	}

}
