package com.common.arrayListTools;

public class MyStringTool {

	/**
	 * 将字符串的指定子串替换成目标子串
	 * 
	 * @param target
	 *            目标母串
	 * @param takePlace
	 *            替换后子串
	 * @param takenPlace
	 *            被替换子串
	 */
	public static String replace(String target, String takePlace, String takenPlace) {
		MyArrayList<Character> tar = MyStringTool.StringToCharArray(target);
		MyArrayList<Character> take = MyStringTool.StringToCharArray(takePlace);
		MyArrayList<Character> taken = MyStringTool.StringToCharArray(takenPlace);

		for (int i = 0; i <= tar.getSize() - taken.getSize(); i++) {
			if (tar.getSubArrayList(i, taken.getSize()).equals(taken)) {
				tar.replace(i, taken.getSize(), take);
			}
		}
		return MyStringTool.CharArrayToString(tar);
	}

	/**
	 * 将字符串转换成字符列表
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @return 返回转换好的字符列表
	 */
	public static MyArrayList<Character> StringToCharArray(String s) {
		return MyStringTool.getCharArray(s);
	}

	/**
	 * 将字符列表转换成字符串
	 * 
	 * @param marray
	 *            需要转换的字符列表
	 * @return 转换好的字符串
	 */
	public static String CharArrayToString(MyArrayList<Character> marray) {
		return MyStringTool.getString(marray);
	}

	/**
	 * 将字符串转换成字符列表
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @return 返回转换好的字符列表
	 */
	public static MyArrayList<Character> getCharArray(String s) {
		MyArrayList<Character> charArray = new MyArrayList<>();
		char[] ch = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			charArray.addElement(ch[i]);
		}
		return charArray;
	}

	/**
	 * 将字符列表转换成整数
	 * 
	 * @param s
	 *            需要转换的字符列表
	 * @return 返回转换好的整数
	 */
	public static int charArrayToInt(MyArrayList<Character> marray) {
		int temp = Integer.parseInt(CharArrayToString(marray));
		return temp;
	}

	/**
	 * 将字符列表转换成字符串
	 * 
	 * @param marray
	 *            需要转换的字符列表
	 * @return 转换好的字符串
	 */
	public static String getString(MyArrayList<Character> marray) {
		return new String(getCharArray(marray));
	}

	/**
	 * 将字符列表转换成字符数组
	 * 
	 * @param marray
	 *            需要转换的字符列表
	 * @return 转换好的字符数字
	 */
	public static char[] getCharArray(MyArrayList<Character> marray) {
		char[] ch = new char[marray.getSize()];
		for (int i = 0; i < ch.length; i++) {
			ch[i] = marray.getElement(i);
		}
		return ch;
	}
}
