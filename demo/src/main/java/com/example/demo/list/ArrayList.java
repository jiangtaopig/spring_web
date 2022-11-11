package com.example.demo.list;

/**
 * 通过迭代器实现容器的快照，即实现快照之后（即 调用 iterator()方法后），容器中增、删、改，迭代器中的元素不受影响
 * @param <E>
 */
public class ArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private int actualSize; //不包含标记删除元素
    private int totalSize; //包含标记删除元素

    private Object[] elements;
    private long[] addTimestamps;
    private long[] delTimestamps;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.addTimestamps = new long[DEFAULT_CAPACITY];
        this.delTimestamps = new long[DEFAULT_CAPACITY];
        this.totalSize = 0;
        this.actualSize = 0;
    }

    public void add(E obj) {
        elements[totalSize] = obj;
        addTimestamps[totalSize] = System.currentTimeMillis();
        delTimestamps[totalSize] = Long.MAX_VALUE;
        totalSize++;
        actualSize++;
    }

    public void remove(E obj) {
        for (int i = 0; i < totalSize; ++i) {
            if (elements[i].equals(obj)) {
                delTimestamps[i] = System.currentTimeMillis();
                actualSize--;
            }
        }
    }

    public Iterator iterator() { return new SnapshotArrayIterator(this); }

    public int actualSize() {
        return this.actualSize;
    }

    public int totalSize() {
        return this.totalSize;
    }

    public E get(int i) {
        System.out.println("i = " + i);
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[i];
    }

    public long getAddTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return addTimestamps[i];
    }

    public long getDelTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return delTimestamps[i];
    }



}

