package com.mine.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.mine.structure.Graphics;

public class NodeReader {
	private File file;
	private Graphics g;

	public NodeReader(String file) {
		this.file = new File(file);
	}

	public NodeReader(File file) {
		this.file = file;
	}

	public void builGraphics() throws FileNotFoundException {
		FileInputStream in = new FileInputStream(file);
		Scanner sc = new Scanner(in);
		String tmp = sc.nextLine();
		int i = 0;
		int data[] = new int[2];
		for (String s : tmp.split(":")) {
			data[i++] = new Integer(s);
		}
		g = new Graphics(data[0], data[1], sc);

		sc.close();
	}

	public Graphics getGraphics() {
		if (g == null)
			try {
				builGraphics();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return g;
	}
}
