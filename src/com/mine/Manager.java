package com.mine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mine.structure.Edge;
import com.mine.structure.Graphics;
import com.mine.structure.Graphics.Minlength;

public class Manager {
	/*
	 * manager.cpp
	 *
	 * Created on: Dec 21, 2016 Author: frank
	 */

	Graphics g = null;
	Scanner sc = new Scanner(System.in);

	int v = 0;
	int n = 0;

	public void select(int in) throws InterruptedException, IOException {
		switch (in) {
		case 0:
			System.exit(0);
		case 1:
			system("clear");
			System.out.println("please type in the number of nodes and edges");
			n = sc.nextInt();
			v = sc.nextInt();
			g = new Graphics(n, v);
			System.out.println("create successfully.Please type in any keys to continue!");
			Thread.sleep(100);
			// sc.nextLine();
			System.in.read();
			break;
		case 2:
			system("clear");
			if (g != null) {
				System.out.println("please select the first node ");
				in = sc.nextInt();
				dfs(in);
			} else
				System.out.println("please create a new graphics first!");
			System.out.println("Please type in any keys to continue!");
			// Thread.sleep(100);
			System.in.read();
			break;
		case 3:
			system("clear");
			if (g != null) {
				System.out.println("please select the first node ");
				in = sc.nextInt();
				bfs(in);
			} else
				System.out.println("please create a new graphics first!");
			System.out.println("Please type in any keys to continue!");
			System.in.read();
			break;
		case 4:
			system("clear");
			if (g != null) {
				ArrayList<Edge> e = new ArrayList<>();
				g.MST(e);
				System.out.println("The Minimum Spanning Tree is:");
				for (int i = 0; i < e.size(); i++)
					System.out.println(e.get(i));
			} else
				System.out.println("please create a new graphics first!");
			System.out.println("Please type in any keys to continue!");
			System.in.read();
			break;
		case 5:
			system("clear");
			if (g != null) {
				ArrayList<Edge> e = new ArrayList<>();
				int start, end;
				Minlength length = new Minlength();
				System.out.println("Please type in the first node and last node");
				start = sc.nextInt();
				end = sc.nextInt();
				g.minPath(start, end, e, length);
				System.out.println("The Minimum Path is:");
				for (int i = 0; i < e.size(); i++)
					System.out.println(e.get(i));
			} else
				System.out.println("please create a new graphics first!");
			System.out.println("Please type in any keys to continue!");
			System.in.read();
			break;
		case 6:
			system("clear");
			if (g != null) {
				ArrayList<Edge> e = new ArrayList<>();
				int start, end;
				System.out.println("Please type in the first node and last node");
				start = sc.nextInt();
				end = sc.nextInt();
				g.bestPath(start, end, e);
				if (e.isEmpty())
					System.out.println("There is no best Path!");
				else {
					System.out.println("The best Path is:");
					for (int i = 0; i < e.size(); i++)
						System.out.println(e.get(i));
				}
			} else
				System.out.println("please create a new graphics first!");
			System.out.println("Please type in any keys to continue!");
			System.in.read();
			break;
		default:
			break;
		}
	}

	void menu() {

		system("clear");
		System.out.println("please choose:");
		System.out.println("1、create a graphic");
		System.out.println("2、depth first search");
		System.out.println("3、breadth first search");
		System.out.println("4、get the Minimum Spanning Tree");
		System.out.println("5、print the Minimum path between two node");
		System.out.println("6、print the best path between two node");
		System.out.println("0、quit");

		try {
			select(sc.nextInt());
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void bfs(int start) {
		ArrayList<Integer> sv = new ArrayList<>(n + 1);
		// for (int i = 0; i <= n; i++) {
		// sv.add(0);
		// }

		ArrayList<Edge> se = new ArrayList<>(v + 1);
		// for (int i = 0; i < n; i++) {
		// se.add(new Edge());
		// }
		g.BFS(start, sv, se);
		print(sv, se);
	}

	void dfs(int start) {
		ArrayList<Integer> sv = new ArrayList<>(n);

		ArrayList<Edge> se = new ArrayList<>(v);
		ArrayList<Integer> vis = new ArrayList<>(n + 1);

		for (int i = 0; i <= n; i++) {
			vis.add(0);
		}
		g.DFS(start, sv, se, vis);

		print(sv, se);
	}

	void print(ArrayList<Integer> sv, ArrayList<Edge> se) {
		System.out.println("遍历顺序是：");
		System.out.print(sv.get(0));
		for (int i = 1; i < sv.size(); i++) {
			System.out.print("->" + sv.get(i));
		}
		System.out.println();
		System.out.println("边的顺序是：");
		for (int i = 0; i < se.size(); i++) {
			System.out.println(se.get(i));
		}
		System.out.println();
	}

	public int main() {
		// queue<int> q;
		// for (int i = 0; i < 10; i++)
		// q.add(i);
		// for (int i = 0; i < q.length(); i++)
		// std::cout << q.get(i) << " ";
		// std::cout << std::endl;
		// q.remove(5);
		// q.setAnyway(15, 1);
		// q.set(5, 1);
		// for (int i = 0; i < q.length(); i++)
		// std::cout << q.get(i) << " ";
		// std::cout << std::endl;
		while (true)
			menu();
	}

	void system(String cmd) {
		switch (cmd) {
		case "cls":
		case "clear":
			try {
				new ProcessBuilder("cmd", "/C", "cls").inheritIO().start();
				Thread.sleep(100);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:

		}
	}

}
