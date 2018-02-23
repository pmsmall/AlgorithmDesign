package com.algorithm.greedy;

import java.util.ArrayList;
import java.util.Comparator;

public class Dijksta {

	public static DijkstaRouteNode[] dijksta(Graphics g, Node startNode) {
		ArrayList<Node> nodes = g.nodeList;
		int visited[] = new int[nodes.size()];
		DijkstaRouteNode[] q = new DijkstaRouteNode[g.edgeList.size()];
		DijkstaRouteNode[] p = new DijkstaRouteNode[g.edgeList.size()];
		int p_index = 0;
		Comparator<DijkstaRouteNode> comp = new Comparator<Dijksta.DijkstaRouteNode>() {

			@Override
			public int compare(DijkstaRouteNode o1, DijkstaRouteNode o2) {
				if (o1.weight == o2.weight)
					return 0;
				else if (o1.weight < o2.weight)
					return 1;
				else
					return -1;
			}
		};
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) == startNode)
				q = Heap.insertIntoHeap(q, i, new DijkstaRouteNode(i, 0), comp);
			else
				q = Heap.insertIntoHeap(q, i, new DijkstaRouteNode(i), comp);
		}
		int length = nodes.size();
		while (p_index < nodes.size()) {
			DijkstaRouteNode u = Heap.deleteFromHeap(q, length--, 0, comp);
			if (visited[u.thisNode] == 0) {
				visited[u.thisNode] = 1;
				p[p_index++] = u;
				for (Edge e : nodes.get(u.thisNode).e) {
					q = Heap.insertIntoHeap(q, length++,
							new DijkstaRouteNode(e.anotherNode, e.thisNode, u.weight + e.weight), comp);
				}
			}

		}
		DijkstaRouteNode[] result = new DijkstaRouteNode[p_index];
		System.arraycopy(p, 0, result, 0, p_index);
		return result;
	}

	public static DijkstaRouteNode[] dijksta(Graphics g, int startNode) {
		ArrayList<Node> nodes = g.nodeList;
		int visited[] = new int[nodes.size()];
		DijkstaRouteNode[] q = new DijkstaRouteNode[g.edgeList.size()];
		DijkstaRouteNode[] p = new DijkstaRouteNode[g.edgeList.size()];
		int p_index = 0;
		Comparator<DijkstaRouteNode> comp = new Comparator<Dijksta.DijkstaRouteNode>() {

			@Override
			public int compare(DijkstaRouteNode o1, DijkstaRouteNode o2) {
				if (o1.weight == o2.weight)
					return 0;
				else if (o1.weight < o2.weight)
					return 1;
				else
					return -1;
			}
		};
		for (int i = 0; i < nodes.size(); i++) {
			if (i == startNode)
				q = Heap.insertIntoHeap(q, i, new DijkstaRouteNode(i, 0), comp);
			else
				q = Heap.insertIntoHeap(q, i, new DijkstaRouteNode(i), comp);
		}
		int length = nodes.size();

		while (p_index < nodes.size()) {
			DijkstaRouteNode u = Heap.deleteFromHeap(q, length--, 0, comp);
			if (visited[u.thisNode] == 0) {
				visited[u.thisNode] = 1;
				p[p_index++] = u;
				for (Edge e : nodes.get(u.thisNode).e) {
					q = Heap.insertIntoHeap(q, length++,
							new DijkstaRouteNode(e.anotherNode, e.thisNode, u.weight + e.weight), comp);
				}
			}

		}
		DijkstaRouteNode[] result = new DijkstaRouteNode[p_index];
		System.arraycopy(p, 0, result, 0, p_index);
		return result;
	}

	public static class DijkstaRouteNode {
		int thisNode, anotherNode;
		int weight;

		public DijkstaRouteNode(int thisNode, int anotherNode, int weight) {
			this.thisNode = thisNode;
			this.anotherNode = anotherNode;
			this.weight = weight;
		}

		public DijkstaRouteNode(int thisNode, int weight) {
			this.thisNode = thisNode;
			this.anotherNode = -1;
			this.weight = weight;
		}

		public DijkstaRouteNode(int thisNode) {
			this.thisNode = thisNode;
			this.anotherNode = -1;
			this.weight = Integer.MAX_VALUE;
		}

		@Override
		public String toString() {
			return thisNode + "," + anotherNode + ":" + weight;
		}
	}

	public static void main(String[] args) {
		int[] arg = { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 0, 1, 3, 0, 3, 7, 1, 3, 2, 1, 2, 4, 2, 3, 5, 3, 4, 4, 2, 4, 6 };
		Graphics g = new Graphics(5, 7, arg);
		DijkstaRouteNode[] result = dijksta(g, 0);
		for (int i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}

}
