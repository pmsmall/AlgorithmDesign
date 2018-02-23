package com.common.arrayListTools;

public class MangeText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyArrayList<Integer> integer = new MyArrayList<>(), takePlace = new MyArrayList<>(),
				takenPlace = new MyArrayList<>();
		takePlace.addElement(1);
		takePlace.addElement(2);
		takePlace.addElement(3);
		takePlace.addElement(4);
		takenPlace.addElement(3);
		takenPlace.addElement(4);
		takenPlace.addElement(5);
		takenPlace.addElement(6);
		for (int i = 0; i < 10; i++) {
			integer.addElement(i);
		}
		integer.replace(takePlace, takenPlace);
		System.out.println("integer包含的元素个数是：" + integer.getSize());
		for (int i = 0; i < integer.getSize(); i++) {
			System.out.println(integer.getElement(i));
		}
		System.out.println(integer.compare(5, 3));
		String s1 = "123456", s2 = "123", s3 = "456";
		s1 = MyStringTool.replace(s1, s2, s3);
		System.out.println(MyStringTool.charArrayToInt(MyStringTool.StringToCharArray(s1)));
	}

}
