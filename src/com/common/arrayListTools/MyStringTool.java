package com.common.arrayListTools;

public class MyStringTool {

	/**
	 * ���ַ�����ָ���Ӵ��滻��Ŀ���Ӵ�
	 * 
	 * @param target
	 *            Ŀ��ĸ��
	 * @param takePlace
	 *            �滻���Ӵ�
	 * @param takenPlace
	 *            ���滻�Ӵ�
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
	 * ���ַ���ת�����ַ��б�
	 * 
	 * @param s
	 *            ��Ҫת�����ַ���
	 * @return ����ת���õ��ַ��б�
	 */
	public static MyArrayList<Character> StringToCharArray(String s) {
		return MyStringTool.getCharArray(s);
	}

	/**
	 * ���ַ��б�ת�����ַ���
	 * 
	 * @param marray
	 *            ��Ҫת�����ַ��б�
	 * @return ת���õ��ַ���
	 */
	public static String CharArrayToString(MyArrayList<Character> marray) {
		return MyStringTool.getString(marray);
	}

	/**
	 * ���ַ���ת�����ַ��б�
	 * 
	 * @param s
	 *            ��Ҫת�����ַ���
	 * @return ����ת���õ��ַ��б�
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
	 * ���ַ��б�ת��������
	 * 
	 * @param s
	 *            ��Ҫת�����ַ��б�
	 * @return ����ת���õ�����
	 */
	public static int charArrayToInt(MyArrayList<Character> marray) {
		int temp = Integer.parseInt(CharArrayToString(marray));
		return temp;
	}

	/**
	 * ���ַ��б�ת�����ַ���
	 * 
	 * @param marray
	 *            ��Ҫת�����ַ��б�
	 * @return ת���õ��ַ���
	 */
	public static String getString(MyArrayList<Character> marray) {
		return new String(getCharArray(marray));
	}

	/**
	 * ���ַ��б�ת�����ַ�����
	 * 
	 * @param marray
	 *            ��Ҫת�����ַ��б�
	 * @return ת���õ��ַ�����
	 */
	public static char[] getCharArray(MyArrayList<Character> marray) {
		char[] ch = new char[marray.getSize()];
		for (int i = 0; i < ch.length; i++) {
			ch[i] = marray.getElement(i);
		}
		return ch;
	}
}
