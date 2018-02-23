package com.algorithm.dynamic;

import java.util.Arrays;

public class KnapsackProblem {
	
	public static int[][] oneZeroKnapsackByCircling(int[] weight, int[] value, int w) {
		int[][] f = new int[weight.length + 1][w + 1];
		// because of the original values of the f(0,j) and f(i,0) are zeros
		// we can let i and j begin at 1
		int remain;
		for (int i = 1; i < f.length; i++) {
			for (int j = 1; j < f[i].length; j++) {
				remain = j - weight[i - 1];
				if (remain >= 0) {
					f[i][j] = Math.max(f[i - 1][j], f[i - 1][remain] + value[i - 1]);
				} else
					f[i][j] = f[i - 1][j];
			}
		}
		return f;
	}

	public static void oneZeroKnapsackByRecursion(int[][] f, int[] weight, int[] value, int w, int i, int j) {
		int remain = j - weight[i - 1];
		if (f[i - 1][j] == -1)
			oneZeroKnapsackByRecursion(f, weight, value, w, i - 1, j);
		if (remain >= 0) {
			if (f[i - 1][remain] == -1)
				oneZeroKnapsackByRecursion(f, weight, value, w, i - 1, remain);
			f[i][j] = Math.max(f[i - 1][j], f[i - 1][remain] + value[i - 1]);
		} else
			f[i][j] = f[i - 1][j];
	}

	public static void main(String[] args) {
		int[] weight = { 2, 1, 3, 2 };
		int[] value = { 12, 10, 20, 15 };
		int w = 5;
		int[][] f = oneZeroKnapsackByCircling(weight, value, w);
		for (int i = 0; i < f.length; i++) {
			System.out.println(Arrays.toString(f[i]));
		}

		int[][] f1 = new int[weight.length + 1][w + 1];
		System.out.println();

		for (int i = 1; i < f1.length; i++) {
			for (int j = 1; j < f1[i].length; j++) {
				f1[i][j] = -1;
			}
		}
		oneZeroKnapsackByRecursion(f1, weight, value, w, weight.length, w);

		for (int i = 0; i < f1.length; i++) {
			System.out.println(Arrays.toString(f1[i]));
		}
	}
	
}
