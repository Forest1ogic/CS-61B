package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> givenComparator;

    public MaxArrayDeque(Comparator<T> c) {
        givenComparator = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 0; i < size(); i++) {
            T currentItem = get(i);
            if (givenComparator.compare(maxItem, currentItem) < 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 0; i < size(); i++) {
            T currentItem = get(i);
            if (c.compare(maxItem, currentItem) < 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }
}
