package com.mine.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.mine.structure.Edge;
import com.mine.structure.Graphics;
import com.mine.structure.Node;

public class NodeWriter {
	public static void writeNode(Graphics g, String s) {
		File file = new File(s);
		writeNode(g, file);
	}

	public static String getLine(String title, String message, double x, double y) {
		return new String(title + "\r\n" + message + "\r\n" + x + "\r\n" + y + "\r\n");
	}

	public static String getEdge(Edge e) {
		return new String(
				e.thisNode + " " + e.anotherNode + " " + e.weight + "\r\n" + e.name() + "\r\n" + e.message() + "\r\n");
	}

	public static void writeNode(Graphics g, File file) {
		try {
			FileWriter writer = new FileWriter(file);

			ArrayList<Node> n = g.getNodes();
			ArrayList<Edge> e = g.getEdges();
			writer.write(g.getN() + ":" + g.getV() + "\r\n");
			for (Node n0 : n) {
				if (n0.name() == null)
					continue;
				writer.write(getLine(n0.name(), n0.message(), n0.x(), n0.y()));
			}
			writer.write("\r\n");
			for (Edge e0 : e) {
				writer.write(getEdge(e0));
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
