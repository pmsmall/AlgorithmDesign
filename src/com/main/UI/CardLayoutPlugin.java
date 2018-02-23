package com.main.UI;

import com.common.UI.MyPanel;

public abstract class CardLayoutPlugin {

	protected MyPanel father;

	public CardLayoutPlugin(MyPanel panel) {
		father = panel;
		creat();
	}

	abstract public void creat();
}
