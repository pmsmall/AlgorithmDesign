package com.algorithm.trisectionSearch;

public class TrisectionSearch {

	public static int trisecionSearch(double[] array, double tar) {
		return trisecionSearch(array, 0, array.length - 1, tar);
	}

	public static int trisecionSearch(double[] array, int start, int end, double tar) {
		if (start < 0 || end >= array.length)
			return -1;
		if (start > end)
			return -1;
		else if (start == end) {
			if (array[start] == tar)
				return start;
			else
				return -1;
		} else {
			int p1 = start / 3 + end * 2 / 3;
			int p2 = start * 2 / 3 + end / 3;
			if (array[p1] == tar)
				return p1;
			else if (array[p1] > tar)
				return trisecionSearch(array, start, p1 - 1, tar);
			else if (array[p2] > tar)
				return trisecionSearch(array, p1 + 1, p2 - 1, tar);
			else if (array[p2] == tar)
				return p2;
			else
				return trisecionSearch(array, p2 + 1, end, tar);
		}
	}

	public static void main(String[] args) {
		double[] data = { 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(trisecionSearch(data, 10));
	}

}
