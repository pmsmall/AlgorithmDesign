package com.main.UI.theme;

import java.awt.Image;
import java.awt.event.MouseEvent;

import com.common.UI.MyLabel;
import com.common.UI.theme.LabelTheme;

public class DefaultLabelTheme extends LabelTheme {
	public DefaultLabelTheme(MyLabel mylabel, Image backgroundImage) {
		super(mylabel, backgroundImage);
	}

	public DefaultLabelTheme(MyLabel mylabel) {
		super(mylabel);
	}

	public DefaultLabelTheme() {
		super();
	}

	public DefaultLabelTheme(String backgroundImage, stringType sType) {
		super(backgroundImage, sType);
	}

	public DefaultLabelTheme(Image backgroundImage) {
		super(backgroundImage);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean HasListener() {
		// TODO Auto-generated method stub
		return false;
	}

}
