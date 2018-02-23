package com.algorithm.nQueen;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueen {
	
	public static ArrayList<int[]> nQueen(int n) {
		ArrayList<int[]> result = new ArrayList<>();
		int[] chessboard = new int[n];
		nQueen(chessboard, n, result);
		return result;
	}

	static void nQueen(int[] chessboard, int n, ArrayList<int[]> result) {
		for (int i = 0; i < n; i++)
			nQueen(0, i, chessboard, n, result);
	}

	static void nQueen(int row, int col, int[] chessboard, int n, ArrayList<int[]> result) {
		if (!canGo(chessboard, row, row, col)) {
			return;
		}
		chessboard[row] = col;
		if (row < n - 1) {
			for (int i = 0; i < n; i++)
				nQueen(row + 1, i, chessboard, n, result);
		} else {
			result.add(chessboard.clone());
		}
	}

	static boolean canGo(int[] chessboard, int row, int x, int y) {
		for (int i = 0; i < row; i++) {
			// if (i == x)
			// return false;
			if (chessboard[i] == y)
				return false;
			if (i - x == chessboard[i] - y || i - x == y - chessboard[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		ArrayList<int[]> result = nQueen(8);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(Arrays.toString(result.get(i)));
		}
	}
	
}
