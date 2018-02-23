package com.algorithm.greedy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Heap<T> {

	public static <T> void buildHeap(T[] h, int length, Comparator<T> comp) {
		for (int i = length / 2; i >= 0; i--) {
			int k = i;
			T v = h[i];
			boolean heap = false;
			while (!heap && 2 * k < length) {
				int j = 2 * k;
				if (j < length - 1)
					if (comp.compare(h[j], h[j + 1]) < 0)
						j = j + 1;
				if (comp.compare(v, h[j]) >= 0)
					heap = true;
				else {
					h[k] = h[j];
					k = j;
				}
			}
			h[k] = v;
		}
	}

	public static <T> void heapSort(T[] h, Comparator<T> comp) {
		heapSort(h, h.length, comp);
	}

	public static <T> void heapSort(T[] h, int length, Comparator<T> comp) {
		buildHeap(h, length, comp);
		for (int i = length; i > 1; i--) {
			deleteFromHeap(h, i, 0, comp);
		}
	}

	public static <T> void updateHeap(T[] h, int length, int index, T newValue, Comparator<T> comp) {
		deleteFromHeap(h, length, index, comp);
		insertIntoHeap(h, length - 1, newValue, comp);
	}

	public static <T> T[] insertIntoHeap(T[] h, int length, T data, Comparator<T> comp) {
		if (h.length < length + 1) {
			@SuppressWarnings("unchecked")
			T[] h0 = (T[]) Array.newInstance(h[0].getClass(), (int) (h.length * 1.5));
			System.arraycopy(h, 0, h0, 0, h.length);
			h = h0;
		}

		int k = length;
		boolean heap = false;
		while (!heap && k > 0) {
			int j = (k - 1) / 2;
			if (comp.compare(h[j], data) >= 0)
				heap = true;
			else {
				h[k] = h[j];
				k = j;
			}
		}
		h[k] = data;
		return (T[]) h;
	}

	public static <T> T deleteFromHeap(T[] h, int length, int index, Comparator<T> comp) {
		T temp = h[index];
		h[index] = h[length - 1];
		h[length - 1] = temp;
		length--;

		int k = index;
		T v = h[k];
		boolean heap = false;
		while (!heap && 2 * k < length) {
			int j = 2 * k;
			if (j < length - 1)
				if (comp.compare(h[j], h[j + 1]) < 0)
					j = j + 1;
			if (comp.compare(v, h[j]) >= 0)
				heap = true;
			else {
				h[k] = h[j];
				k = j;
			}
		}
		h[k] = v;

		return temp;

	}

	public static void main(String[] args) {
		Integer[] a = { 9, 8, 131, 15, 54, 47, 3, 0, -3, 144 };
		heapSort(a, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (o1 == o2)
					return 0;
				else if (o1 < o2)
					return -1;
				else
					return 1;
			}
		});
		System.out.println(Arrays.toString(a));
		Comparator<Integer> com = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (o1 == o2)
					return 0;
				else if (o1 > o2)
					return -1;
				else
					return 1;
			}
		};
		// heapSort(a, com);
		// buildHeap(a, a.length, com);
		System.out.println(Arrays.toString(a));
		int l = a.length;
		deleteFromHeap(a, l--, 0, com);
		System.out.println(Arrays.toString(a));
		deleteFromHeap(a, l--, 0, com);
		System.out.println(Arrays.toString(a));
		deleteFromHeap(a, l--, 0, com);
		System.out.println(Arrays.toString(a));
	}

}
