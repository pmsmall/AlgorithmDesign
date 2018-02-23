package com.common.UI;

import java.awt.Color;
import java.awt.Dimension;

import com.common.UI.theme.FrameTheme;

public class Test {
	public void test() {
		MyFrame f = new MyFrame(new FrameTheme("image/dataCard/×ÊÁÏ¿¨_0018_±³¾°.png") {
		});
		f.setSize(500, 500);
		f.setDefaultCloseOperation(3);
		f.setLocationRelativeTo(null);

		MyPanel l = new MyPanel();
		l.setBackground(Color.BLACK);
		l.setSize(new Dimension(1000, 500));
		MyScrollArea s = new MyScrollArea(l);
		s.setSize(new Dimension(500, 500));
		s.setLocation(0, 0);
		f.add(s);
		f.setLayout(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.test();
	}
}
