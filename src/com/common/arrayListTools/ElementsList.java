package com.common.arrayListTools;

/**
 * 创建一个接口，包含元素的增加，删除，修改，获取元素数量
 * 
 * @author ktn
 * @version jdk1.8.0 or later versions
 */
public interface ElementsList<E> {

	/**
	 * 末尾增加元素
	 */
	public void addElement(E e);

	/**
	 * 指定位置增加元素
	 */
	public void addElement(int index, E e);

	/**
	 * 获取元素数量
	 */
	public int getSize();

	/**
	 * 删除元素
	 * 
	 * @index 删除元素的索引
	 */
	public void delete(int index);

	/**
	 * param 修改元素
	 */
	public void update(int index, E e);

	/**
	 * 获取元素
	 */
	public E getElement(int index);

	/**
	 * 替换部分元素
	 */
	public void replace(MyArrayList<E> takePlace, MyArrayList<E> takenPlace);

	/**
	 * 替换部分元素
	 */
	public void replace(int index, int length, MyArrayList<E> takePlace);

	/**
	 * 匹配元素
	 */
	public boolean compare(int index, Object object);

	/**
	 * 获取子串
	 */
	public MyArrayList<E> getSubArrayList(int index, int length);

	/**
	 * 比较内容是否相同
	 */
	public boolean equals(Object object);

	/**
	 * 清除所有成员
	 */
	public void clear();

}
