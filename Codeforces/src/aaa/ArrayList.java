package aaa;

import java.util.Arrays;

class ArrayList {

    private int[] myStore;
    private int actSize = 0;

    public ArrayList() {
        myStore = new int[2];
    }

    public int get(int index) {
        if (index < actSize)
            return myStore[index];
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void add(int obj) {
        if (myStore.length - actSize <= 1)
            increaseListSize();
        myStore[actSize++] = obj;
    }

    public int size() {
        return actSize;
    }

    public void clear() {
        actSize = 0;
    }

    private void increaseListSize() {
        myStore = Arrays.copyOf(myStore, myStore.length * 2);
    }
}