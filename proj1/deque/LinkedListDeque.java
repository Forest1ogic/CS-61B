package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class ItemNode {
        T item;
        ItemNode next;
        ItemNode front;

        ItemNode(T x) {
            item = x;
        }
        ItemNode() {

        }
    }
    private ItemNode sentinel = new ItemNode();
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.front = sentinel;
        size = 0;
    }

    /** Adds an item x to the front of the deque. */
    @Override
    public void addFirst(T x) {
        ItemNode i = new ItemNode(x);
        i.front = sentinel;
        i.next = sentinel.next;
        sentinel.next.front = i;
        sentinel.next = i;
        size += 1;
    }

    /** Adds an item x to the back of the deque. */
    @Override
    public void addLast(T x) {
        ItemNode i = new ItemNode(x);
        i.next = sentinel;
        i.front = sentinel.front;
        sentinel.front.next = i;
        sentinel.front = i;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
     */

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        ItemNode i = sentinel.next;
        while (i.next != sentinel) {
            System.out.print(i.item);
            System.out.print(' ');
        }
        System.out.println(i.item);
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T returnItem = sentinel.next.item;
            sentinel.next.next.front = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return returnItem;
        }
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T returnItem = sentinel.front.item;
            sentinel.front.front.next = sentinel;
            sentinel.front = sentinel.front.front;
            size -= 1;
            return returnItem;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. */
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            ItemNode getItem = sentinel;
            for (int i = 0; i <= index; i++) {
                getItem = getItem.next;
            }
            return getItem.item;
        }
    }

    public T getRecursive(int index) {
        ItemNode itemRecursive = sentinel.next;
        return recursiveGet(itemRecursive, index);
    }

    private T recursiveGet(ItemNode itemRecursive, int index) {
        if (itemRecursive == sentinel.front && index > 0) {
            return null;
        }
        if (index == 0) {
            return itemRecursive.item;
        } else {
            itemRecursive = itemRecursive.next;
            return recursiveGet(itemRecursive, index - 1);
        }
    }

    /**  Returns whether the parameter o is equal to the Deque. **/
    public boolean equals(Object o) {
        if (o instanceof Deque<?>) {
            Deque<T> otherDeque = (Deque<T>) o;
            if (this.size != otherDeque.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!this.get(i).equals(otherDeque.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        ItemNode wizNode = sentinel.next;
        @Override
        public boolean hasNext() {
            return wizNode != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T returnItem = wizNode.item;
            wizNode = wizNode.next;
            return returnItem;
        }
    }
}
