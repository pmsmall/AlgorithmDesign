package com.algorithm.closestPair;

import java.awt.Point;
import java.util.ArrayList;

public class EfficientClosestPair {

	/**
	 * 
	 * @param point
	 *            输入点集
	 * @param size
	 *            输入点的数量
	 * @return 返回最近点和最近的距离
	 */
	public DimensionWithPoints closestPair(Point[] point, int size) {
		if (size < 2)
			return null;
		else if (size == 2) {
			return new DimensionWithPoints(point[0], point[1], Math.sqrt(dimension(point[0], point[1])));
		} else if (size == 3) {
			double d1 = dimension(point[0], point[1]);
			double d2 = dimension(point[0], point[2]);
			double d3 = dimension(point[1], point[2]);
			double d4;
			Point p1, p2;
			if (d1 <= d2) {
				d4 = d1;
				p1 = point[0];
				p2 = point[1];
			} else {
				d4 = d2;
				p1 = point[0];
				p2 = point[2];
			}
			if (d4 <= d3)
				d3 = d4;
			else {
				p1 = point[1];
				p2 = point[2];
			}
			return new DimensionWithPoints(p1, p2, Math.sqrt(d3));

		}
		Point[] p = new Point[size];
		System.arraycopy(point, 0, p, 0, size);
		Point[] q = p.clone();
		quickSort(p, 0, p.length - 1, true);
		System.out.println();
		quickSort(q, 0, q.length - 1, false);
		DimensionWithPoints d = efficientClosestPair(p, q);
		d.dimension = Math.sqrt(d.dimension);
		return d;
	}

	// TODO 记录最近的点对
	DimensionWithPoints efficientClosestPair(Point[] p, Point[] q) {
		Point[] point = p;
		if (point.length < 2) {
			return null;
		} else if (point.length == 2) {
			return new DimensionWithPoints(point[0], point[1], dimension(point[0], point[1]));
		} else if (point.length == 3) {
			double d1 = dimension(point[0], point[1]);
			double d2 = dimension(point[0], point[2]);
			double d3 = dimension(point[1], point[2]);
			double d4;
			Point p1, p2;
			if (d1 <= d2) {
				d4 = d1;
				p1 = point[0];
				p2 = point[1];
			} else {
				d4 = d2;
				p1 = point[0];
				p2 = point[2];
			}
			if (d4 <= d3)
				d3 = d4;
			else {
				p1 = point[1];
				p2 = point[2];
			}
			return new DimensionWithPoints(p1, p2, d3);
		}

		Point[] p1 = new Point[p.length / 2];
		Point[] pr = new Point[p.length - p1.length];
		Point[] q1 = new Point[q.length / 2];
		Point[] qr = new Point[q.length - q1.length];
		int i, j;
		System.arraycopy(p, 0, p1, 0, p1.length);
		System.arraycopy(p, p1.length, pr, 0, pr.length);

		System.arraycopy(q, 0, q1, 0, q1.length);
		System.arraycopy(q, q1.length, qr, 0, qr.length);

		DimensionWithPoints d1 = efficientClosestPair(p1, q1);
		DimensionWithPoints d2 = efficientClosestPair(pr, qr);
		DimensionWithPoints d = d1.dimension <= d2.dimension ? d1 : d2;

		int m = p[p.length / 2 - 1].x;
		int m1 = p[p.length / 2].x;

		double d_d = Math.sqrt(d.dimension);

		ArrayList<Point> s = new ArrayList<>(q.length);
		for (i = 0; i < q.length; i++) {
			if ((q[i].x - m) < d_d || (m1 - q[i].x) < d_d)
				s.add(q[i]);
		}

		for (i = 0; i < s.size() - 2; i++) {
			j = i + 1;
			while (j <= s.size() - 1 && j <= 11 && Math.pow((s.get(j).y - s.get(i).y), 2) < d.dimension) {
				double d0 = dimension(s.get(j), s.get(i));
				d = d0 < d.dimension ? new DimensionWithPoints(s.get(j), s.get(i), d0) : d;
				j++;
			}
		}

		return d;
	}

	double dimension(Point p1, Point p2) {
		return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
	}

	/**
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @param flag
	 *            true x; flase y
	 */
	void quickSort(Point[] array, int low, int high, boolean flag) {
		if (low < high) {
			int middle = getMiddle(array, low, high, flag);
			quickSort(array, low, middle - 1, flag);
			quickSort(array, middle + 1, high, flag);
		}
	}

	int getMiddle(Point[] list, int low, int high, boolean flag) {
		double tmp = flag ? list[low].getX() : list[low].getY();
		Point tmpPair = list[low];
		while (low < high) {
			while (low < high && (flag ? list[high].getX() : list[high].getY()) >= tmp) {
				high--;
			}
			list[low] = list[high];
			while (low < high && (flag ? list[low].getX() : list[low].getY()) <= tmp) {
				low++;
			}
			list[high] = list[low];
		}
		list[low] = tmpPair;
		return low;
	}

	public static class DimensionWithPoints {
		Point p1, p2;
		double dimension;

		public DimensionWithPoints(Point p1, Point p2, double dimension) {
			this.p1 = p1;
			this.p2 = p2;
			this.dimension = dimension;
		}

		public Point[] getPoints() {
			Point[] ps = { (Point) p1.clone(), (Point) p2.clone() };
			return ps;
		}

		public double getDimension() {
			return dimension;
		}
	}

}
