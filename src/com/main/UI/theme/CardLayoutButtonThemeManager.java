package com.main.UI.theme;

import java.util.ArrayList;

import com.common.UI.MyButton;
import com.common.UI.theme.ButtonTheme;
import com.common.UI.theme.ButtonTheme.buttonActiveStatus;

public class CardLayoutButtonThemeManager {
	private ArrayList<CardLayoutButtonTheme> theme;
	private int active;

	public void setActive(int index) {
		if (theme != null && theme.size() > index) {
			if (active != -1) {
				theme.get(active).setActiveStatus(buttonActiveStatus.normal);
				theme.get(active).activeStatusChange();
				if (theme.get(active).getMyButton().isVisible())
					theme.get(active).repaint();
			}
			active = index;
			theme.get(active).setActiveStatus(buttonActiveStatus.active);
			theme.get(active).activeStatusChange();
			if (theme.get(active).getMyButton().isVisible())
				theme.get(active).repaint();
		}
	}

	public CardLayoutButtonThemeManager() {
		theme = new ArrayList<>();
	}

	public void addTheme(CardLayoutButtonTheme theme) {
		this.theme.add(theme);
		theme.initCardLayoutButtonThemeManager(this);
	}

	public void addTheme(MyButton button) {
		ButtonTheme t = button.getTheme();
		if (t instanceof CardLayoutButtonTheme) {
			this.theme.add((CardLayoutButtonTheme) t);
			((CardLayoutButtonTheme) t).initCardLayoutButtonThemeManager(this);
		}
	}

	void deleteTheme(CardLayoutButtonTheme t) {
		theme.remove(t);
	}

	void setActiveButton(CardLayoutButtonTheme theme) {
		int a = this.theme.indexOf(theme);
		if (a != -1 && a != active) {
			this.theme.get(active).setActiveStatus(buttonActiveStatus.normal);
			this.theme.get(active).activeStatusChange();
			this.theme.get(active).repaint();
			active = a;
			this.theme.get(active).repaint();
		}
	}

	public void dispose() {
		theme.clear();
		theme = null;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("system is delete this " + this);
	}

}
