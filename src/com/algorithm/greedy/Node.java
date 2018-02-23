package com.algorithm.greedy;

import java.awt.Color;
import java.util.ArrayList;

public class Node {
	ArrayList<Edge> e;
	private double x, y;
	public Color c = Color.RED;

	public Node(ArrayList<Edge> e) {
		this.e = e;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public Node(ArrayList<Edge> e, double x, double y) {
		this.e = e;
		this.x = x;
		this.y = y;
	}
	
	public Node(double x, double y) {
		e = new ArrayList<>();

		this.x = x;
		this.y = y;
	}

	public Node() {
		e = new ArrayList<>();
	}
}
