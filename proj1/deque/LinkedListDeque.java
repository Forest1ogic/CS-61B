package deque;
/*
import java.util.Iterator;
 */

public class LinkedListDeque<Item> /*implements Iterable<Item>, Deque<Item>*/{

    private class ItemNode {
        Item item;
        ItemNode nextLast;
        ItemNode nextFirst;

        public ItemNode(Item x) {
            item = x;
        }
        public ItemNode() {

        }
    }
    private ItemNode sentinel = new ItemNode();
    int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque(){
        sentinel.nextLast = sentinel;
        sentinel.nextFirst = sentinel;
        size = 0;
    }
/*
    public class LinkedListIterator implements Iterator<Item> {

        ItemNode wizNode = sentinel;
        @Override
        public boolean hasNext() {
            return wizNode.nextFirst != sentinel;
        }

        @Override
        public Item next() {
            Item returnItem = wizNode.item;
            wizNode = wizNode.nextFirst;
            return returnItem;
        }
    }

    public Iterator<Item> iterator(){
        return new LinkedListIterator();
    }
 */
    /** Adds an item x to the front of the deque. */
    public void addFirst(Item x) {
        ItemNode i = new ItemNode(x);
        i.nextFirst = sentinel;
        i.nextLast = sentinel.nextLast;
        sentinel.nextLast.nextFirst = i;
        sentinel.nextLast = i;
        size += 1;
    }

    /** Adds an item x to the back of the deque. */
    public void addLast(Item x) {
        ItemNode i = new ItemNode(x);
        i.nextLast = sentinel;
        i.nextFirst = sentinel.nextFirst;
        sentinel.nextFirst.nextLast = i;
        sentinel.nextFirst = i;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        ItemNode i = sentinel.nextLast;
        while(i.nextLast != sentinel) {
            System.out.print(i.item);
            System.out.print(' ');
        }
        System.out.println(i.item);
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public Item removeFirst() {
        if(size == 0) {
            return null;
        } else {
            Item returnItem = sentinel.nextLast.item;
            sentinel.nextLast.nextLast.nextFirst = sentinel;
            sentinel.nextLast = sentinel.nextLast.nextLast;
            size -= 1;
            return returnItem;
        }
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    public Item removeLast() {
        if(size == 0) {
            return null;
        } else {
            Item returnItem = sentinel.nextFirst.item;
            sentinel.nextFirst.nextFirst.nextLast = sentinel;
            sentinel.nextFirst = sentinel.nextFirst.nextFirst;
            size -= 1;
            return returnItem;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the nextLast item, and so forth.
     * If no such item exists, returns null. */
    public Item get(int index) {
        if(index > size - 1) {
            return null;
        } else {
            ItemNode getItem = sentinel.nextLast;
            for(int i = 0; i <= index; i++) {
                getItem = getItem.nextLast;
            }
            return getItem.item;
        }
    }

    private ItemNode getItemRecursive = sentinel.nextLast;
    public Item getRecursive(int index) {
        if(index > size - 1) {
            return null;
        }
        if(index == 0) {
            return getItemRecursive.item;
        } else {
            getItemRecursive = getItemRecursive.nextLast;
            return getRecursive(index - 1);
        }
    }

    /**  Returns whether the parameter o is equal to the Deque.
    public boolean equals(Object o) {
        if(o instanceof LinkedListDeque otherLinkedListDeque) {
            if(otherLinkedListDeque.size != this.size) {
                return false;
            }
            ItemNode itemOfo = otherLinkedListDeque.sentinel.nextLast;
            ItemNode itemOfThisList = sentinel.nextLast;
            while (itemOfo != sentinel) {
                if(itemOfo.item != itemOfThisList.item) {
                    return false;
                }
                itemOfo = itemOfo.nextLast;
                itemOfThisList = itemOfThisList.nextLast;
            }
            return true;
        } else {
            return false;
        }
    }
     **/
}