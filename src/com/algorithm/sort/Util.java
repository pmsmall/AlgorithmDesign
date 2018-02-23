package com.algorithm.sort;

public class Util {
	public static void mergeSort(double[] array, int low, int high) {
		if (low >= high)
			return;
		int mid = (low + high) / 2;
		mergeSort(array, low, mid);
		mergeSort(array, mid + 1, high);
		double[] temp1 = new double[mid - low + 1];
		double[] temp2 = new double[high - mid];
		System.arraycopy(array, low, temp1, 0, temp1.length);
		System.arraycopy(array, mid + 1, temp2, 0, temp2.length);
		int i1 = 0, i2 = 0;
		while (i1 < temp1.length && i2 < temp2.length) {
			if (temp1[i1] <= temp2[i2])
				array[low++] = temp1[i1++];
			else
				array[low++] = temp2[i2++];
		}
		while (i1 < temp1.length)
			array[low++] = temp1[i1++];
		while (i2 < temp2.length)
			array[low++] = temp2[i2++];
	}

	public static void quickSort(double[] array, int low, int high) {
		if (low < high) {
			int middle = getMiddle(array, low, high);
			quickSort(array, low, middle - 1);
			quickSort(array, middle + 1, high);
		}
	}

	public static int getMiddle(double[] list, int low, int high) {
		double tmp = list[low];
		while (low < high) {
			while (low < high && list[high] > tmp) {
				high--;
			}
			list[low] = list[high];
			while (low < high && list[low] < tmp) {
				low++;
			}
			list[high] = list[low];
		}
		list[low] = tmp;
		return low;
	}
}
