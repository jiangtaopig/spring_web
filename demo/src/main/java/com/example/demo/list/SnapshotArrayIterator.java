package com.example.demo.list;

public class SnapshotArrayIterator<E> implements Iterator<E> {

    private long snapshotTimestamp;
    private int cursorInAll; // 在整个容器中的下标，而非快照中的下标
    private int leftCount; // 快照中还有几个元素未被遍历
    private ArrayList<E> arrayList;

    public SnapshotArrayIterator(ArrayList<E> arrayList) {
        this.snapshotTimestamp = System.currentTimeMillis();
        this.cursorInAll = 0;
        this.leftCount = arrayList.actualSize();
        ;
        this.arrayList = arrayList;

        findFirstData(); // 先跳到这个迭代器快照的第一个元素
    }

    @Override
    public boolean hasNext() {
        return this.leftCount >= 0; // 注意是>=, 而非>
    }

    @Override
    public E next() {
        System.out.println(">>> cursorInAll = " + cursorInAll);
        E currentItem = arrayList.get(cursorInAll);

        justNext();
        return currentItem;
    }

    private void findFirstData() {
        System.out.println(" findFirstData cursorInAll = " + cursorInAll);
        while (cursorInAll < arrayList.totalSize()) {
            long addTimestamp = arrayList.getAddTimestamp(cursorInAll);
            long delTimestamp = arrayList.getDelTimestamp(cursorInAll);
            if (snapshotTimestamp > addTimestamp && snapshotTimestamp < delTimestamp) {
                leftCount--;
                break;
            }
            cursorInAll++;
            System.out.println(" findFirstData cursorInAll 2 = " + cursorInAll);
        }
    }

    private void justNext() {
        System.out.println(" justNext cursorInAll = " + cursorInAll);
        while (cursorInAll < arrayList.totalSize()) {
            long addTimestamp = arrayList.getAddTimestamp(cursorInAll);
            long delTimestamp = arrayList.getDelTimestamp(cursorInAll);
            if (snapshotTimestamp > addTimestamp && snapshotTimestamp < delTimestamp) {
                leftCount--;
                cursorInAll++;
                break;
            }
//            cursorInAll++;
        }
    }
}
