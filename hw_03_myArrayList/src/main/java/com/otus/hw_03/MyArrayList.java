package com.otus.hw_03;

import java.util.*;

/**
 * {@code MyArrayList} class. Array-based implementation of array list.
 */
@SuppressWarnings("unchecked")
public class MyArrayList<E> implements List<E>
{
    private static final int CAPACITY = 10;
    private E[] data;
    private int size;

    /**
     * Constructs list with default capacity.
     */
    public MyArrayList()
    {
        this(CAPACITY);
    }

    /**
     * Constructs list with given capacity.
     *
     * @param capacity the given capacity of this list
     */
    public MyArrayList(final int capacity)
    {
        if (capacity < 0) {
            throw new IllegalArgumentException("Array size cannot be negative: " + capacity);
        }
        data = (E[]) new Object[capacity];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size()
    {
        return this.size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty()
    {
        return this.size == 0;
    }

    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains
     * at least one element e such that (o==null ? e==null : o.equals(e)).
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(final Object o)
    {
        return this.indexOf(o) > -1;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<E> iterator()
    {
        return new IteratorForMyArrayList();
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     * <p>
     * The returned array will be "safe" in that no references to it are
     * maintained by this list. (In other words, this method must allocate
     * a new array). The caller is thus free to modify the returned array.
     * <p>
     * This method acts as bridge between array-based and collection-based APIs.
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray()
    {
        final E[] newData = (E[]) new Object[this.size()];
        System.arraycopy(this.data, 0, newData, 0, this.size());
        return newData;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array. If the list fits in the specified
     * array, it is returned therein. Otherwise, a new array is allocated with
     * the runtime type of the specified array and the size of this list.
     * <p>
     * If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the collection is set to null. (This
     * is useful in determining the length of the list only if the caller
     * knows that the list does not contain any null elements.)
     *
     * @param a   the array into which the elements of the list are to be stored,
     *            if it is big enough; otherwise, a new array of the same runtime
     *            type is allocated for this purpose.
     * @param <T> the runtime type of the array to contain the collection
     * @return an array containing the elements of the list
     */
    public <T> T[] toArray(final T[] a)
    {
        if (a.length < this.size) {
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        }
        System.arraycopy(this.data, 0, a, 0, this.size());
        if (a.length > this.size()) {
            a[size] = null;
        }
        return a;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param newElement element to be appended to this list
     * @return true (as specified by {@code Collection.add(E)})
     */
    public boolean add(final E newElement)
    {
        growArrayIfNecessary();
        this.size++;
        data[size - 1] = newElement;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from
     * this list, if it is present.
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove(final Object o)
    {
        for (int i = 0; i < this.size(); i++) {
            if (data[i].equals(o)) {
                if (i != this.size() - 1) {
                    System.arraycopy(data, i + 1, data, i, this.size() - i - 1);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this list contains all of the elements
     * of the specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all of the elements
     * of the specified collection
     */
    public boolean containsAll(final Collection<?> c)
    {
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end
     * of this list, in the order that they are returned by the specified
     * collection's iterator (optional operation). The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress. (Note that this will occur if
     * the specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addAll(final Collection<? extends E> c)
    {
        for (final E item : c) {
            this.add(item);
        }
        return true;
    }

    public boolean addAll(final int index, final Collection<? extends E> c)
    {
        throw new UnsupportedOperationException("addAll(int index, Collection c) " +
            "is not supported by this implementation of List interface");
    }

    public boolean removeAll(final Collection<?> c)
    {
        throw new UnsupportedOperationException("removeAll(Collection<?> c) " +
            "is not supported by this implementation of List interface");
    }

    public boolean retainAll(final Collection<?> c)
    {
        throw new UnsupportedOperationException("retainAll(Collection<?> c) " +
            "is not supported by this implementation of List interface");
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear()
    {
        this.data = (E[]) new Object[1];
        this.size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return element at the specified position in this list
     */
    public E get(final int index)
    {
        checkIndex(index, this.size());
        return this.data[index];
    }

    /**
     * Replaces the element at the specified position in this list
     * with the specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public E set(final int index, final E element)
    {
        checkIndex(index, this.size());
        E temp = data[index];
        data[index] = element;
        return temp;
    }

    /**
     * Inserts the specified element at the specified position
     * in this list (optional operation). Shifts the element
     * currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(final int index, final E element)
    {
        checkIndex(index, this.size() + 1);
        growArrayIfNecessary();
        System.arraycopy(data, index, data, index + 1, this.size() - index);
        data[index] = element;
        this.size++;
    }

    /**
     * Removes the element at the specified position in this list
     * (optional operation). Shifts any subsequent elements to the
     * left (subtracts one from their indices). Returns the element
     * that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    public E remove(final int index)
    {
        checkIndex(index, this.size());
        E temp = data[index];
        System.arraycopy(data, index + 1, data, index, this.size() - 1 - index);
        data[this.size() - 1] = null;
        size--;
        return temp;
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element. More formally,
     * returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))),
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(final Object o)
    {
        if (o == null) {
            for (int i = 0; i < this.size(); i++) {
                if (data[i] == null) return i;
            }
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (o.equals(data[i])) return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified
     * element in this list, or -1 if this list does not contain
     * the element.* More formally, returns the highest index i
     * such that (o==null ? get(i)==null : o.equals(get(i))), or
     * -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(final Object o)
    {
        int lastIndex = -1;
        if (o == null) {
            for (int i = 0; i < this.size(); i++) {
                if (data[i] == null) {
                    lastIndex = getLastIndexOf(i, lastIndex);
                }
            }
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (o.equals(data[i])) {
                    lastIndex = getLastIndexOf(i, lastIndex);
                }
            }
        }
        return lastIndex;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     *
     * @return a list iterator over the elements in this list (in proper sequence)
     */
    public ListIterator<E> listIterator()
    {
        return new ListIteratorForMyArrayList(0);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list. The
     * specified index indicates the first element that would be returned
     * by an initial call to next. An initial call to previous would return
     * the element with the specified index minus one.
     *
     * @param index index of the first element to be returned
     *              from the list iterator (by a call to next)
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     */
    public ListIterator<E> listIterator(final int index)
    {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                "Index supplied to iterator is out of bound: " + index);
        }
        return new ListIteratorForMyArrayList(index);
    }

    public List<E> subList(final int fromIndex, final int toIndex)
    {
        throw new UnsupportedOperationException("subList(int fromIndex, int toIndex) " +
            "is not supported by this implementation of List interface");
    }

    /**
     * Helper method to find last index of specified element in this list.
     *
     * @param currIndex current index of the element
     * @param lastIndex last index of the element
     * @return last index of the element in this list
     */
    private int getLastIndexOf(final int currIndex, final int lastIndex)
    {
        return currIndex > lastIndex ? currIndex : lastIndex;
    }

    /**
     * Helper method to grow the size of this array when it is full.
     * The array will grow twice the size of the original array.
     */
    private void growArrayIfNecessary()
    {
        if (this.size == this.data.length) {
            final int growFactor = 2;
            final int newCapacity = this.data.length * growFactor;
            E[] temp = (E[]) new Object[newCapacity];
            System.arraycopy(this.data, 0, temp, 0, this.size);
            this.data = temp;
        }
    }

    /**
     * Helper method to check if index supplied is in valid range [0, n-1].
     */
    private void checkIndex(final int index, final int size)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not valid: " + index);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < this.size(); i++) {
            if (i < this.size() - 1) {
                result.append(data[i]).append(", ");
            } else {
                result.append(data[i]);
            }
        }
        return result.append("]").toString();
    }

    private enum Move
    {
        NEXT, PREVIOUS
    }

    /**
     * Iterator implementation. Includes remove() method.
     */
    private class IteratorForMyArrayList implements Iterator<E>
    {
        private int nextIndex;
        private boolean wasNextCalled;

        @Override
        public boolean hasNext()
        {
            return this.nextIndex < size;
        }

        @Override
        public E next()
        {
            if (this.nextIndex == size) {
                throw new NoSuchElementException("No next element");
            }
            wasNextCalled = true;
            return data[nextIndex++];
        }

        @Override
        public void remove()
        {
            if (!wasNextCalled) {
                throw new IllegalStateException("remove() call should follow next() call");
            }
            MyArrayList.this.remove(nextIndex - 1);
            nextIndex--;
            wasNextCalled = false;
        }
    }

    private class ListIteratorForMyArrayList implements ListIterator<E>
    {
        private int nextIndex;
        private boolean isRemoveOrSetLegal;
        private Move lastMove;

        ListIteratorForMyArrayList(final int index)
        {
            this.nextIndex = index;
            this.isRemoveOrSetLegal = false;
            this.lastMove = null;
        }

        @Override
        public boolean hasNext()
        {
            return this.nextIndex < size;
        }

        @Override
        public E next()
        {
            if (this.hasNext()) {
                this.lastMove = Move.NEXT;
                this.isRemoveOrSetLegal = true;
                return data[this.nextIndex++];
            } else {
                throw new NoSuchElementException("Illegal call to next(); " +
                    "iterator is after end of list.");
            }
        }

        @Override
        public boolean hasPrevious()
        {
            return this.nextIndex > 0 && this.nextIndex <= size;
        }

        @Override
        public E previous()
        {
            if (this.hasPrevious()) {
                this.lastMove = Move.PREVIOUS;
                this.isRemoveOrSetLegal = true;
                this.nextIndex--;
                return data[this.nextIndex];
            } else {
                throw new NoSuchElementException("Illegal call to previous(); " +
                    "iterator is before beginning of the list.");
            }

        }

        @Override
        public int nextIndex()
        {
            if (this.hasNext()) {
                return this.nextIndex;
            } else {
                return size;
            }
        }

        @Override
        public int previousIndex()
        {
            if (this.hasPrevious()) {
                return this.nextIndex - 1;
            } else {
                return -1;
            }
        }

        @Override
        public void remove()
        {
            if (this.isRemoveOrSetLegal) {
                this.isRemoveOrSetLegal = false;
                if (lastMove.equals(Move.NEXT)) {
                    MyArrayList.this.remove(this.nextIndex - 1);
                    this.nextIndex--;
                } else {
                    if (lastMove.equals(Move.PREVIOUS)) {
                        MyArrayList.this.remove(nextIndex);
                    }
                }
            } else {
                throw new IllegalStateException("Illegal call to remove(); " +
                    "next() or previous() was not called, or add() or remove()" +
                    " called since then");
            }
        }

        @Override
        public void set(final E e)
        {
            if (this.isRemoveOrSetLegal) {
                if (this.lastMove.equals(Move.NEXT)) {
                    data[this.nextIndex - 1] = e;
                } else {
                    if (this.lastMove.equals(Move.PREVIOUS)) {
                        data[this.nextIndex] = e;
                    }
                }
            } else {
                throw new IllegalStateException("Illegal call to set(); " +
                    "next() or previous() was not called, or add() or" +
                    " remove() called since then");
            }
        }

        @Override
        public void add(final E e)
        {
            this.isRemoveOrSetLegal = false;
            this.nextIndex++;
            MyArrayList.this.add(nextIndex, e);
        }
    }
}
