package com.algorithm.sort;

import com.main.UI.MainFrame;

public class Manager {
	double[] data;

	public void init() {
		int range = (int) (Math.random() * 20 + 20);
		data = new double[range];
		for (int i = 0; i < data.length; i++) {
			data[i] = (Math.random() * Integer.MAX_VALUE);
		}
	}

	public void quickSort() {
		Util.quickSort(data, 0, data.length - 1);
	}

	public void mergeSort() {
		Util.quickSort(data, 0, data.length - 1);
	}

	public double[] getData() {
		double[] d = new double[data.length];
		System.arraycopy(data, 0, d, 0, data.length);
		return d;
	}

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		main.setVisible(true);
	}
}
