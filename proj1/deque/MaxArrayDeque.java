package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private Comparator<Item> givenComparator;

    public MaxArrayDeque(Comparator<Item> c) {
        givenComparator = c;
    }

    public Item max() {
        if (size == 0) {
            return null;
        }
        Item maxItem = ItemNode[firstItemIndex()];
        for (Item s: ItemNode) {
            if (givenComparator.compare(maxItem, s) < 0) {
                maxItem = s;
            }
        }
        return maxItem;
    }

    public Item max(Comparator<Item> c) {
        if (size == 0) {
            return null;
        }
        Item maxItem = ItemNode[firstItemIndex()];
        for (Item s: ItemNode) {
            if (c.compare(maxItem, s) < 0) {
                maxItem = s;
            }
        }
        return maxItem;
    }
}
