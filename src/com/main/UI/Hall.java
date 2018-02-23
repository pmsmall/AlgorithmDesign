package com.main.UI;

import com.common.UI.MyFrame;
import com.common.UI.theme.FrameTheme;

public class Hall extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 569792789050969132L;

	public Status status = Status.home;

	public Hall(FrameTheme theme) {
		super(theme);
		// TODO Auto-generated constructor stub
	}

	public enum Status {
		home, //
		recieving, //
		sending, //
		manager, //
		setting
	}

}
