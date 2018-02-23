package com.mine.structure;

import java.awt.Color;
import java.util.ArrayList;

public class Node {
	private String name, message;
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

	public Node(ArrayList<Edge> e, String name, String message, double x, double y) {
		this.e = e;
		this.name = name;
		this.message = message;
		this.x = x;
		this.y = y;
	}

	public String name() {
		return name;
	}

	public String message() {
		return message;
	}

	public Node(String name, String message, double x, double y) {
		e = new ArrayList<>();
		this.name = name;
		this.message = message;
		this.x = x;
		this.y = y;
	}

	public Node() {
		e = new ArrayList<>();
	}
}
