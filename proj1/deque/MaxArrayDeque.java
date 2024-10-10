package deque;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    public MaxArrayDeque(Item c) {
        ItemNode = (Item[]) new Object[8];
        ItemNode[4] = c;
        nextFirst = 3;
        nextLast = 5;
        size = 0;
    }

    public Item max() {
        if(size == 0) {
            return null;
        }
        Item maxItem = ItemNode[firstItemIndex()];
        for(Item s: this) {
            if((int)maxItem < (int)s) {
                maxItem = s;
            }
        }
        return maxItem;
    }

    public Item max(Item c) {
        addFirst(c);
        return max();
    }
}