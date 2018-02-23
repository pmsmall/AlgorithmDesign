package com.algorithm.dynamic;

import java.util.Arrays;

public class BinarySearchTree {

	public static double[][][] optimalBST(double[] p) {
		// Arrays.sort(p);// Sort by dual pivot quick sort

		double[][][] c = new double[2][p.length + 2][p.length + 1];
		for (int i = 1; i <= p.length; i++) {
			c[0][i][i] = p[i - 1];
			c[1][i][i] = i;
		}

		double min;
		int kmin = 0;
		double value;
		for (int t = 1; t <= p.length; t++) {
			for (int i = 1; i <= p.length - t; i++) {
				int j = i + t;
				min = Double.MAX_VALUE;
				for (int k = i; k <= j; k++) {
					value = c[0][i][k - 1] + c[0][k + 1][j];
					if (value < min) {
						min = value;
						kmin = k;
					}
				}
				c[1][i][j] = kmin;

				double sum = 0;
				for (int k = i; k <= j; k++) {
					sum += p[k - 1];
				}
				sum = (int) (sum * 100) / 100.0;
				c[0][i][j] = min + sum;
			}
		}
		return c;
	}

	public static void main(String[] args) {
		double[] p = { 0.1, 0.2, 0.4, 0.3 };
		double[][][] c = BinarySearchTree.optimalBST(p);
		for (int i = 1; i <= p.length + 1; i++) {
			System.out.println(Arrays.toString(c[0][i]));
		}
		System.out.println();
		for (int i = 1; i <= p.length + 1; i++) {
			System.out.println(Arrays.toString(c[1][i]));
		}
	}

}
