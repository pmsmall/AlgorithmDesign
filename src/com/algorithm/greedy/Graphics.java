package com.algorithm.greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Graphics {
	int n;
	ArrayList<Node> nodeList;

	public int getN() {
		return n;
	}

	public int getV() {
		return v;
	}

	/**
	 * 用于生成最小生成树
	 */
	ArrayList<Edge> edgeList;
	int v;

	public Graphics(Graphics g) {

	}

	public Graphics(int n, int v) {
		this.n = n;
		this.v = v;
		nodeList = new ArrayList<>(n + 1);
		edgeList = new ArrayList<>(v);
		for (int i = 0; i < n + 1; i++) {
			nodeList.add(new Node());
		}

		System.out.println("please type in the each edge:");
		int n0, anotherNode, weight;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < v; i++) {

			n0 = sc.nextInt();
			anotherNode = sc.nextInt();
			weight = sc.nextInt();
			nodeList.get(n0).e.add(new Edge(n0, anotherNode, weight));
			nodeList.get(anotherNode).e.add(new Edge(anotherNode, n0, weight));
			edgeList.add(new Edge(n0, anotherNode, weight));
		}
		System.out.println("create successfully.Please type in any key to continue");

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Graphics(ArrayList<Node> nodeList, ArrayList<Edge> edgeList) {
		this.nodeList = (ArrayList<Node>) nodeList.clone();
		this.edgeList = (ArrayList<Edge>) edgeList.clone();
	}

	public Graphics(int n, int v, int[] arg) {
		int index = 0;
		this.n = n;
		this.v = v;
		nodeList = new ArrayList<>(n);

		edgeList = new ArrayList<>(v);
		double x, y;
		for (int i = 0; i < n; i++) {
			x = arg[index++];
			y = arg[index++];
			nodeList.add(new Node(x, y));
		}

		int n0, anotherNode, weight;

		for (int i = 0; i < v; i++) {
			n0 = arg[index++];
			anotherNode = arg[index++];
			weight = arg[index++];
			nodeList.get(n0).e.add(new Edge(n0, anotherNode, weight));
			nodeList.get(anotherNode).e.add(new Edge(anotherNode, n0, weight));
			edgeList.add(new Edge(n0, anotherNode, weight));
		}
	}
	public Graphics(int n, int v, Integer[] arg) {
		int index = 0;
		this.n = n;
		this.v = v;
		nodeList = new ArrayList<>(n);

		edgeList = new ArrayList<>(v);
		double x, y;
		for (int i = 0; i < n; i++) {
			x = arg[index++];
			y = arg[index++];
			nodeList.add(new Node(x, y));
		}

		int n0, anotherNode, weight;

		for (int i = 0; i < v; i++) {
			n0 = arg[index++];
			anotherNode = arg[index++];
			weight = arg[index++];
			nodeList.get(n0).e.add(new Edge(n0, anotherNode, weight));
			nodeList.get(anotherNode).e.add(new Edge(anotherNode, n0, weight));
			edgeList.add(new Edge(n0, anotherNode, weight));
		}
	}

	public Graphics(int n, int v, Scanner sc) {
		this.n = n;
		this.v = v;
		nodeList = new ArrayList<>(n + 1);

		nodeList.add(new Node());
		edgeList = new ArrayList<>(v);

		double x, y;
		for (int i = 0; i < n; i++) {
			x = sc.nextDouble();
			y = sc.nextDouble();
			nodeList.add(new Node(x, y));
			sc.nextLine();
		}

		int n0, anotherNode, weight;

		for (int i = 0; i < v; i++) {
			n0 = sc.nextInt();
			anotherNode = sc.nextInt();
			weight = sc.nextInt();
			sc.nextLine();
			nodeList.get(n0).e.add(new Edge(n0, anotherNode, weight));
			nodeList.get(anotherNode).e.add(new Edge(anotherNode, n0, weight));
			edgeList.add(new Edge(n0, anotherNode, weight));
		}
	}

	public void DFS(int start, ArrayList<Integer> sv, ArrayList<Edge> se, ArrayList<Integer> vis) {
		sv.add(start);
		vis.set(start, 1);
		ArrayList<Edge> elist = nodeList.get(start).e;
		for (int i = 0; i < elist.size(); i++) {
			if (vis.get(elist.get(i).anotherNode) == 0) {
				se.add(elist.get(i));
				DFS(elist.get(i).anotherNode, sv, se, vis);
			}
		}
	}

	public void BFS(int start, ArrayList<Integer> sv, ArrayList<Edge> se, Visitable visitable) {
		int[] visited = new int[n + 1];
		visited[start] = 1;

		int head = 0, end = 0;
		sv.add(start);
		end++;

		while (head < end) {
			ArrayList<Edge> elist = (nodeList.get(sv.get(head++))).e;
			for (int i = 0; i < elist.size(); i++) {
				if (visited[elist.get(i).anotherNode] == 0) {
					se.add(elist.get(i));
					sv.add(elist.get(i).anotherNode);
					end++;
					visitable.visit(nodeList.get(i));
					visited[elist.get(i).anotherNode] = 1;
				}
			}
		}
	}

	public void BFS(int start, ArrayList<Integer> sv, ArrayList<Edge> se) {
		Visitable v = new Visitable();
		BFS(start, sv, se, v);
	}

	/**
	 * 利用并查集来构造最小生成树
	 */
	public void MST(ArrayList<Edge> purple) {

		/* 对red进行初始化 */
		int[] red = new int[n + 1];
		for (int i = 0; i < red.length; i++) {
			red[i] = 0;
		}

		/**
		 * 利用并查集来避免成环
		 */
		int find[] = new int[n + 1];
		/* 对find进行初始化 */
		for (int i = 0; i < find.length; i++) {
			find[i] = -1;
		}

		edgeList.sort(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				if (o1.weight < o2.weight)
					return 1;
				else if (o1.weight == o2.weight)
					return 0;
				else
					return -1;
			}
		});
		int end = edgeList.size() - 1;
		boolean flag = false;

		int border = n;
		while (purple.size() < border) {
			if (end < 0) {
				System.out.println("an error occurred!Please type in any key to continue!");
				try {
					System.in.read();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}

			Edge e = edgeList.get(end--);
			if (red[e.thisNode] == 0) {
				if (red[e.anotherNode] == 0)
					red[e.anotherNode] = 1;
				red[e.thisNode] = 1;
				find[e.thisNode] = e.anotherNode;
				flag = true;
			} else if (red[e.anotherNode] == 0) {
				red[e.anotherNode] = 1;
				find[e.anotherNode] = e.thisNode;
				flag = true;
			} else {
				int parent1 = getRoot(find, e.thisNode);
				int parent2 = getRoot(find, e.anotherNode);
				if (parent1 != parent2) {
					find[parent2] = parent1;
					flag = true;
				}
			}
			if (flag)
				purple.add(e);
		}
	}

	public static class Minlength {
		public int minlength;
	}

	/**
	 * 深搜法找所有路径
	 */
	public void allPath(int start, int end, ArrayList<ArrayList<Edge>> paths) {
		int[] vis = new int[n + 1];
		vis[start] = 1;
		ArrayList<Edge> path = new ArrayList<>(n);
		allPath(start, end, paths, path, vis);
	}

	/**
	 * 深搜法找所有路径
	 */
	@SuppressWarnings("unchecked")
	private void allPath(int start, int end, ArrayList<ArrayList<Edge>> paths, ArrayList<Edge> path, int[] vis) {
		if (start == end) {
			paths.add((ArrayList<Edge>) path.clone());
			return;
		}
		ArrayList<Edge> e = nodeList.get(start).e;
		for (int i = 0; i < e.size(); i++) {
			if (vis[e.get(i).anotherNode] == 0) {
				vis[e.get(i).anotherNode] = 1;
				path.add(e.get(i));
				allPath(e.get(i).anotherNode, end, paths, path, vis);
				path.remove(path.size() - 1);
				vis[e.get(i).anotherNode] = 0;
			}
		}
	}

	/**
	 * 深搜法找最短路径
	 */
	public void nodesMinPath(int[] nodes, ArrayList<Edge> path, Minlength minlength) {
		ArrayList<Edge> mpath = new ArrayList<>(n);
		int length = 0;
		int[] vis;
		minlength.minlength = 99999999;
		// vis.memset(n, 0);

		for (int start : nodes) {
			vis = new int[n + 1];
			vis[start] = 1;
			nodesMinPath(nodes, start, path, mpath, minlength, length, vis, 1);
		}
	}

	private boolean inNodes(int[] nodes, int node) {
		for (int i : nodes) {
			if (i == node)
				return true;
		}
		return false;
	}

	/**
	 * 深搜法找最短路径
	 */
	private void nodesMinPath(int[] nodes, int start, ArrayList<Edge> path, ArrayList<Edge> mpath, Minlength minlength,
			int length, int[] vis, int find) {
		ArrayList<Edge> e = nodeList.get(start).e;

		for (int i = 0; i < e.size(); i++) {
			if (vis[e.get(i).anotherNode] == 0) {
				vis[e.get(i).anotherNode] = 1;
				if (length + e.get(i).weight < minlength.minlength) {
					mpath.add(e.get(i));
					if (inNodes(nodes, e.get(i).anotherNode))
						find++;
					if (find < nodes.length) {
						nodesMinPath(nodes, e.get(i).anotherNode, path, mpath, minlength, length + e.get(i).weight, vis,
								find);
					} else {

						minlength.minlength = length + e.get(i).weight;
						path.clear();
						for (int j = 0; j < mpath.size(); j++) {
							path.add(mpath.get(j));
						}

					}
					if (inNodes(nodes, e.get(i).anotherNode))
						find--;
					mpath.remove(mpath.size() - 1);
				}
				vis[e.get(i).anotherNode] = 0;
			}
		}
	}

	/**
	 * 深搜法找最短路径
	 */
	public void minPath(int start, int end, ArrayList<Edge> path, Minlength minlength) {
		ArrayList<Edge> mpath = new ArrayList<>(n);
		int length = 0;
		int[] vis = new int[n + 1];
		minlength.minlength = 99999999;
		vis[start] = 1;
		minPath(start, end, path, mpath, minlength, length, vis);
	}

	/**
	 * 深搜法找最佳路径
	 */
	public void bestPath(int start, int end, ArrayList<Edge> path) {
		ArrayList<Edge> mpath = new ArrayList<>(n);
		int length = 0;
		int[] vis = new int[n + 1];
		int minlength = 99999999;
		vis[start] = 1;
		bestPath(start, end, path, mpath, minlength, length, vis, n - 1);
	}

	/**
	 * 深搜法找最短路径
	 */
	private void minPath(int start, int end, ArrayList<Edge> path, ArrayList<Edge> mpath, Minlength minlength,
			int length, int[] vis) {
		if (start == end) {
			if (length < minlength.minlength) {
				minlength.minlength = length;
				path.clear();
				for (int i = 0; i < mpath.size(); i++) {
					path.add(mpath.get(i));
				}
			}
			return;
		}
		ArrayList<Edge> e = nodeList.get(start).e;
		for (int i = 0; i < e.size(); i++) {
			if (vis[e.get(i).anotherNode] == 0) {
				vis[e.get(i).anotherNode] = 1;
				if (length + e.get(i).weight < minlength.minlength) {
					mpath.add(e.get(i));
					minPath(e.get(i).anotherNode, end, path, mpath, minlength, length + e.get(i).weight, vis);
					mpath.remove(mpath.size() - 1);
				}
				vis[e.get(i).anotherNode] = 0;
			}
		}
	}

	/**
	 * 深搜法找最佳路径
	 */
	void bestPath(int start, int end, ArrayList<Edge> path, ArrayList<Edge> mpath, int minlength, int length, int[] vis,
			int remain) {
		if (start == end) {
			if (remain == 0 && length < minlength) {
				minlength = length;
				path.clear();
				for (int i = 0; i < mpath.size(); i++) {
					path.add(mpath.get(i));
				}
			}
			return;
		}
		ArrayList<Edge> e = nodeList.get(start).e;
		for (int i = 0; i < e.size(); i++) {
			if (vis[e.get(i).anotherNode] == 0) {
				vis[e.get(i).anotherNode] = 1;
				if (length + e.get(i).weight < minlength) {
					mpath.add(e.get(i));
					bestPath(e.get(i).anotherNode, end, path, mpath, minlength, length + e.get(i).weight, vis,
							remain - 1);
					mpath.remove(mpath.size() - 1);
				}
				vis[e.get(i).anotherNode] = 0;
			}
		}
	}

	int getRoot(int[] find, int n) {
		int result = find[n];
		if (result != -1)
			return getRoot(find, result);
		else {
			return n;
		}
	}

	public ArrayList<Node> getNodes() {
		return nodeList;
	}

	public ArrayList<Edge> getEdges() {
		return edgeList;
	}
}
