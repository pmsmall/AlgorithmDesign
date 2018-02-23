package com.common.arrayListTools;

import java.util.Arrays;

public class MyArrayList<E> implements ElementsList<E>, Cloneable {

	private Object data[];
	private int size = 0;
	/**
	 * The number of times this list has been <i>structurally modified</i>.
	 * Structural modifications are those that change the size of the list, or
	 * otherwise perturb it in such a fashion that iterations in progress may
	 * yield incorrect results.
	 *
	 * <p>
	 * This field is used by the iterator and list iterator implementation
	 * returned by the {@code iterator} and {@code listIterator} methods. If the
	 * value of this field changes unexpectedly, the iterator (or list iterator)
	 * will throw a {@code ConcurrentModificationException} in response to the
	 * {@code next}, {@code remove}, {@code previous}, {@code set} or
	 * {@code add} operations. This provides <i>fail-fast</i> behavior, rather
	 * than non-deterministic behavior in the face of concurrent modification
	 * during iteration.
	 *
	 * <p>
	 * <b>Use of this field by subclasses is optional.</b> If a subclass wishes
	 * to provide fail-fast iterators (and list iterators), then it merely has
	 * to increment this field in its {@code add(int, E)} and
	 * {@code remove(int)} methods (and any other methods that it overrides that
	 * result in structural modifications to the list). A single call to
	 * {@code add(int, E)} or {@code remove(int)} must add no more than one to
	 * this field, or the iterators (and list iterators) will throw bogus
	 * {@code ConcurrentModificationExceptions}. If an implementation does not
	 * wish to provide fail-fast iterators, this field may be ignored.
	 */
	protected transient int modCount = 0;

	/**
	 * Shared empty array instance used for empty instances.
	 */
	@SuppressWarnings("unused")
	private static final Object[] EMPTY_ELEMENTDATA = {};

	/**
	 * Shared empty array instance used for default sized empty instances. We
	 * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
	 * first element is added.
	 */
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	/**
	 * Default initial capacity.
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * ĩβ����Ԫ��
	 * 
	 * @param e
	 *            ���ӵ�Ԫ��
	 */
	public void addElement(E e) {
		ensureCapacityInternal(size + 1);
		data[size] = e;
		size++;
	}

	/**
	 * ָ��λ������Ԫ��
	 * 
	 * @param index
	 *            ���λ�õ�����
	 * @param e
	 *            ��ӵ�Ԫ��
	 */
	public void addElement(int index, E e) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

		ensureCapacityInternal(size + 1); // Increments modCount!!
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = e;
		size++;
	}

	/**
	 * ��ȡԪ������
	 * 
	 * @return Ԫ�ظ���
	 */
	public int getSize() {
		return size;
	}

	/**
	 * ɾ��Ԫ��
	 * 
	 * @param index
	 *            ɾ��Ԫ�ص�����
	 */
	public void delete(int index) {
		if (index >= 0 && index < size) {
			for (int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			size--;
			Object temp[] = new Object[size];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

	}

	/**
	 * �޸�Ԫ��
	 * 
	 * @param index
	 *            �޸�Ԫ�ص�����
	 */
	public void update(int index, E e) {
		if (index < size && index >= 0) {
			data[index] = e;
		}
	}

	/**
	 * ��ȡԪ��
	 * 
	 * @param index
	 *            ��ȡԪ�ص�����
	 * @return ��ȡԪ��
	 */
	@SuppressWarnings("unchecked")
	public E getElement(int index) {
		if (index < size && index >= 0)
			return (E) data[index];
		else
			return null;
	}

	/**
	 * �滻����Ԫ��
	 * 
	 * @param takePlace
	 *            �滻Ŀ��
	 * @param takenPlace
	 *            ���滻Ŀ��
	 */
	public void replace(MyArrayList<E> takePlace, MyArrayList<E> takenPlace) {
		boolean information = false;
		for (int i = 0; i < size - takenPlace.getSize() - 1; i++) {
			if (this.getSubArrayList(i, takenPlace.getSize()).equals(takenPlace)) {
				information = true;
				this.replace(i, takenPlace.getSize(), takePlace);
				i += takePlace.getSize() - 1;
			}
		}
		if (!information) {
			throw new java.lang.NoSuchFieldError("�Ӵ������ڣ��޷�����滻��");
		}
	}

	/**
	 * �滻����Ԫ��
	 * 
	 * @param index
	 *            ���滻Ԫ�ص���λ��
	 * @param length
	 *            ���滻Ԫ�صĳ���
	 * @param takePlace
	 *            �滻Ŀ��
	 */
	public void replace(int index, int length, MyArrayList<E> takePlace) {
		// TODO Auto-generated method stub
		if ((index + length) > size)
			throw new java.lang.IndexOutOfBoundsException("Խ�磡");
		if (length >= takePlace.getSize()) {
			for (int i = 0; i < takePlace.getSize(); i++) {
				this.update(index + i, takePlace.getElement(i));
			}
			for (int i = 0; i < length - takePlace.getSize(); i++) {
				this.delete(index + takePlace.getSize());
			}
		} else {
			for (int i = 0; i < length; i++) {
				this.update(index + i, takePlace.getElement(i));
			}
			for (int i = 0; i < takePlace.getSize() - length; i++) {
				this.addElement(index + i + length, takePlace.getElement(i + length));
			}
		}
	}

	/**
	 * ƥ��Ԫ��
	 * 
	 * @param index
	 *            ��ӦԪ�ص�����
	 * @param e
	 *            ��Ҫƥ���Ŀ��
	 * @return ��ͬ����true�����󷵻�false
	 */
	public boolean compare(int index, Object object) {
		// TODO Auto-generated method stub
		if (data[index].equals(object))
			return true;
		else
			return false;
	}

	/**
	 * ��ȡ�Ӵ�
	 * 
	 * @param index
	 *            ��ʼλ������
	 * @param length
	 *            �Ӵ�����
	 * @return �����Ӵ�
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList<E> getSubArrayList(int index, int length) {
		MyArrayList<E> temp = new MyArrayList<>();
		int l = Math.min(length, size - index);
		for (int i = 0; i < l; i++) {
			temp.addElement((E) data[i + index]);
		}
		return temp;
	}

	/**
	 * �Ƚ������Ƿ���ͬ
	 * 
	 * @param object
	 *            �ȽϵĶ���
	 * @return �������һ��������true�������һ��������false
	 */
	public boolean equals(Object object) {
		@SuppressWarnings("unchecked")
		MyArrayList<E> temp = (MyArrayList<E>) object;
		if (temp.getSize() == data.length) {
			for (int i = 0; i < size; i++) {
				if (!temp.compare(i, data[i]))
					return false;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		modCount++;

		// clear to let GC do its work
		for (int i = 0; i < size; i++)
			data[i] = null;

		size = 0;
	}

	public void destroy() {
		clear();
		data = null;
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (data == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}

		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity) {
		modCount++;

		if (data == null) {
			data = new Object[0];
			size = 0;
		}

		// overflow-conscious code
		if (minCapacity - data.length > 0)
			grow(minCapacity);
	}

	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words
	 * in an array. Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * Increases the capacity to ensure that it can hold at least the number of
	 * elements specified by the minimum capacity argument.
	 *
	 * @param minCapacity
	 *            the desired minimum capacity
	 */
	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = data.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		// minCapacity is usually close to size, so this is a win:
		data = Arrays.copyOf(data, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	/**
	 * Constructs an IndexOutOfBoundsException detail message. Of the many
	 * possible refactorings of the error handling code, this "outlining"
	 * performs best with both server and client VMs.
	 */
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	public MyArrayList<E> clone() {
		return new MyArrayList<>();
	}
}
