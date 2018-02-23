package com.main.UI;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;

public class MyFileDialog extends FileDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4884125789161846047L;

	public MyFileDialog(Frame parent, String title, int mode) {
		super(parent, title, mode);
	}

	public MyFileDialog(Frame parent, String title) {
		super(parent, title);
	}

	public MyFileDialog(Frame parent) {
		super(parent);
	}

	public MyFileDialog(Dialog parent, String title, int mode) {
		super(parent, title, mode);
	}

	public MyFileDialog(Dialog parent, String title) {
		super(parent, title);
	}

	public MyFileDialog(Dialog parent) {
		super(parent);
	}

}
