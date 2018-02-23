package com.common.arrayListTools;

/**
 * ����һ���ӿڣ�����Ԫ�ص����ӣ�ɾ�����޸ģ���ȡԪ������
 * 
 * @author ktn
 * @version jdk1.8.0 or later versions
 */
public interface ElementsList<E> {

	/**
	 * ĩβ����Ԫ��
	 */
	public void addElement(E e);

	/**
	 * ָ��λ������Ԫ��
	 */
	public void addElement(int index, E e);

	/**
	 * ��ȡԪ������
	 */
	public int getSize();

	/**
	 * ɾ��Ԫ��
	 * 
	 * @index ɾ��Ԫ�ص�����
	 */
	public void delete(int index);

	/**
	 * param �޸�Ԫ��
	 */
	public void update(int index, E e);

	/**
	 * ��ȡԪ��
	 */
	public E getElement(int index);

	/**
	 * �滻����Ԫ��
	 */
	public void replace(MyArrayList<E> takePlace, MyArrayList<E> takenPlace);

	/**
	 * �滻����Ԫ��
	 */
	public void replace(int index, int length, MyArrayList<E> takePlace);

	/**
	 * ƥ��Ԫ��
	 */
	public boolean compare(int index, Object object);

	/**
	 * ��ȡ�Ӵ�
	 */
	public MyArrayList<E> getSubArrayList(int index, int length);

	/**
	 * �Ƚ������Ƿ���ͬ
	 */
	public boolean equals(Object object);

	/**
	 * ������г�Ա
	 */
	public void clear();

}
