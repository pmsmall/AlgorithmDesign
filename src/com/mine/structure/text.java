package com.mine.structure;

import java.awt.Point;
import java.util.ArrayList;

import com.mine.Manager;

public class text {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Manager m = new Manager();
		// m.main();
		ArrayList<Point>[] a=new ArrayList[12];
		for(int i=0;i<12;i++){
			a[i]=new ArrayList<>();
		}
		a[1].add(new Point());
		System.out.println(a[1].get(0));
		int[] b=new int[100];
		System.out.println(b[2]);
	}

}
