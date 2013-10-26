import java.lang.reflect.Array;

//Symbol Table implemented via Ordered Array
public class STOA<Key extends Comparable<Key>, Value> {
    private int size;
    private Pair<Key, Value>[] pairs = new Pair[0];
    public STOA() {

    }

    public STOA(int capacity) {
        pairs = new Pair[capacity];
    }

    public void Put(Key key, Value value) {
        int indexToInsert = FindIndex(key, true);

        if(indexToInsert == -1) {
            indexToInsert = 0;
        } else if(pairs[indexToInsert].key.compareTo(key) == 0) {
            pairs[indexToInsert].value = value;
            return;
        } else if(pairs[indexToInsert].key.compareTo(key) < 0) {
            indexToInsert++;
        }

        if(indexToInsert > pairs.length) {
            //TODO: resize array
        }

        for(int j = size; j > indexToInsert; j--) {
            pairs[j] = pairs[j - 1];
        }
        pairs[indexToInsert] = new Pair<Key, Value>(key, value);
        size++;
    }

    public Value Get(Key key) throws Exception {
        int index = FindIndex(key, false);
        if(index != -1) {
            return pairs[index].value;
        }

        throw new Exception();
    }

    private int FindIndex(Key key, boolean returnFoundIndex) {
        if(size == 0) {
            return -1;
        }

        int low = 0, high = size - 1;
        while(high > low) {
            int index = (high + low) / 2;
            if(pairs[index].key.compareTo(key) == 0) {
                return index;
            } else if(pairs[index].key.compareTo(key) < 0) {
                low = index + 1;
            } else {
                high = index - 1;
            }
        }

        if(pairs[high].key.compareTo(key) == 0) {
            return high;
        }

        return returnFoundIndex ? high : -1;
    }

    public void Delete(Key key) throws Exception {
        int index = FindIndex(key, false);
        if(index != -1) {
            for(int i = index; i < size - 1; i++) {
                pairs[i] = pairs[i + 1];
            }
            size--;
            return;
        }
        throw new Exception();
    }

    public boolean Contains(Key key) {
        return FindIndex(key, false) != -1;
    }

    public boolean IsEmpty() {
        return Size() == 0;
    }

    public int Size() {
        return size;
    }

    private class Pair<Key, Value> {
        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        private Key key;
        private Value value;
    }
}
