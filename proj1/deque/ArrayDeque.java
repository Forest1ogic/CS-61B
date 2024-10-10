package deque;
/*
import java.util.Iterator;
*/
public class ArrayDeque<Item> /*implements Iterable<Item>, Deque<Item>*/{
    int nextFirst;
    int nextLast;
    int size;
    Item[] ItemNode;
/*
    public class ArrayIterator implements Iterator<Item>{

        int wizPoz = firstItemIndex();
        int wizSize = 0;
        @Override
        public boolean hasNext() {
            return wizSize + 1 <= size;
        }

        @Override
        public Item next() {
            Item returnItem = ItemNode[wizPoz];
            wizPoz += 1;
            if(wizPoz >= ItemNode.length) {
                wizPoz = 0;
            }
            return returnItem;
        }
    }

    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }
*/
    /** Creates an empty array deque. */
    public ArrayDeque() {
        ItemNode = (Item[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    /** Return the index of the first item. */
    public int firstItemIndex() {
        int first = nextFirst + 1;
        if(first > ItemNode.length) {
            first = 0;
        }
        return first;
    }

    private void resetNextFirst(String s) {
        if(s.equals("left")) {
            nextFirst -= 1;
            if(nextFirst < 0) {
                nextFirst = ItemNode.length - 1;
            }
        } else if(s.equals("right")) {
            nextFirst += 1;
            if(nextFirst >= ItemNode.length) {
                nextFirst = 0;
            }
        }
    }

    private void resetNextLast(String s) {
        if(s.equals("right")) {
            nextLast += 1;
            if(nextLast >= ItemNode.length) {
                nextLast = 0;
            }
        } else if(s.equals("left")) {
            nextLast -= 1;
            if(nextLast < 0) {
                nextLast = ItemNode.length - 1;
            }
        }
    }

    /** Resize the Deque. */
    private void resizing() {
        Item[] a;
        if(ItemNode.length >= 16 && size * 4 < ItemNode.length) {
            a = (Item[]) new Object[ItemNode.length / 2];
        } else {
            a = (Item[]) new Object[ItemNode.length * 2];
        }
        System.arraycopy(ItemNode, firstItemIndex(), a, 0, size);
        ItemNode = a;
        nextFirst = ItemNode.length - 1;
        nextLast = ItemNode.length / 2;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(Item i) {
        if(size == ItemNode.length) {
            resizing();
        }
        ItemNode[nextFirst] = i;
        size += 1;
        resetNextFirst("left");
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(Item i) {
        if(size == ItemNode.length) {
            resizing();
        }
        ItemNode[nextLast] = i;
        size += 1;
        resetNextLast("right");
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last */
    public void printDeque() {
        int index = nextFirst + 1;
        while(index != nextLast - 1) {
            if(index >= ItemNode.length) {
                index = 0;
            }
            System.out.print(ItemNode[index]);
            System.out.print(' ');
            index += 1;
        }
        System.out.println(ItemNode[index]);
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public Item removeFirst() {
        if(size == 0) {
            return null;
        }
        if(ItemNode.length >= 16 && size * 4 < ItemNode.length) {
            resizing();
        }
        resetNextFirst("right");
        size -= 1;
        return ItemNode[nextFirst];
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public Item removeLast() {
        if(size == 0) {
            return null;
        }
        if(ItemNode.length >= 16 && size * 4 < ItemNode.length) {
            resizing();
        }
        resetNextLast("left");
        size -= 1;
        return ItemNode[nextLast];
    }

    /** Gets the item at the given index.
     * If no such item exists, returns null. */
    public Item get(int index) {
        if(index + 1 > size) {
            return null;
        }
        int first = firstItemIndex();
        if(first + index <= ItemNode.length - 1) {
            return ItemNode[first + index];
        } else {
            return ItemNode[(index - ((ItemNode.length - 1) - first)) - 1];
        }
    }

    /**  Returns whether the parameter o is equal to the Deque.
    @Override
    public boolean equals(Object o) {
        if(o instanceof ArrayDeque otherArrayDeque) {
            if (size != otherArrayDeque.size) {
                return false;
            }
            int i = otherArrayDeque.firstItemIndex();
            while(i != otherArrayDeque.nextLast) {
                for(Item s: this) {
                    if(s != otherArrayDeque.ItemNode[i]) {
                        return false;
                    }
                    i += 1;
                    if(i >= otherArrayDeque.ItemNode.length) {
                        i = 0;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    **/
}