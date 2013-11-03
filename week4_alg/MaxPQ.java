import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    private int size = 0;

    public MaxPQ() {
        this(0);
    }
    public MaxPQ(int capacity) {
        keys = (Key[]) new Object[capacity];
    }

    public void insert(Key key) {
        if(size == keys.length) {
            resizeArray(size * 2);
        }

        keys[size] = key;
        swim_(keys, size);
        size++;
    }

    public Key deleteMax() {
        if(size == 0) {
            throw new NoSuchElementException();
        }

        Key max = keys[0];
        keys[0] = keys[size - 1];
        sink_(keys, 0, size - 1);
        return max;
    }

    private void resizeArray(int newSize) {
        Key[] newKeys = (Key[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
        }
    }

    private static void sink_(Comparable[] items, int k, int n) {
        while(k <= n / 2) {
            int j = k * 2 + 1;
            if (j + 1 < n && items[j].compareTo(items[j + 1]) < 0) j++;
            if(j >= n || items[k].compareTo(items[j]) > 0) break;
            exch_(items, k, j);
            k = j;
        }
    }

    private static void swim_(Comparable[] items, int k) {
        while(k > 0 && items[getParentIndex_(k)].compareTo(items[k]) < 0) {
            exch_(items, k, getParentIndex_(k));

            k = getParentIndex_(k);
        }
    }

    private static int getParentIndex_(int k) {
        if(k % 2 == 0) {
            return k / 2 - 1;
        } else {
            return k / 2;
        }
    }

    private static void exch_(Comparable[] items, int a, int b) {
        Comparable temp = items[a];
        items[a] = items[b];
        items[b] = temp;
    }
}
