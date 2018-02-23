package com.mine.structure;

public class Edge {
	public int thisNode, anotherNode, weight;
	String message, name;
	public boolean wark, bike, car;

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

	public Edge(int thisNode, int anotherNode, int weight, String name, String message) {
		this.thisNode = thisNode;
		this.anotherNode = anotherNode;
		this.weight = weight;
		this.name = name;
		this.message = message;
		setFlag(message);
	}

	private void setFlag(String message) {
		if (message.contains("走路"))
			wark = true;
		if (message.contains("骑车"))
			bike = true;
		if (message.contains("校车"))
			car = true;
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

	public String name() {
		return name;
	}

	public String message() {
		return message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return thisNode + "," + anotherNode + " weight=" + weight;
	}
}
