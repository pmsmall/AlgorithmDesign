package com.algorithm.greedy;

import java.awt.Color;

public class Edge {
	public int thisNode, anotherNode, weight;
	Color color = Color.black;

	public Edge() {
		thisNode = -1;
		anotherNode = -1;
		weight = -1;
	}

	public Edge(int thisNode, int anotherNode, int weight) {
		this.thisNode = thisNode;
		this.anotherNode = anotherNode;
		this.weight = weight;
	}

	public boolean smaller(Edge e) {
		return weight < e.weight;
	}

	public boolean Heavier(Edge e) {
		return weight > e.weight;
	}

	public boolean sameWeight(Edge e) {
		return weight == e.weight;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return thisNode + "," + anotherNode + " weight=" + weight;
	}
}
