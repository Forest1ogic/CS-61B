package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Iterable<Item>, Deque<Item>{

    private class ItemNode {
        Item item;
        ItemNode next;
        ItemNode front;

        public ItemNode(Item x) {
            item = x;
        }
        public ItemNode() {

        }
    }
    private ItemNode sentinel = new ItemNode();
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque(){
        sentinel.next = sentinel;
        sentinel.front = sentinel;
        size = 0;
    }

    public class LinkedListIterator implements Iterator<Item> {

        ItemNode wizNode = sentinel;
        @Override
        public boolean hasNext() {
            return wizNode.next != sentinel;
        }

        @Override
        public Item next() {
            Item returnItem = wizNode.item;
            wizNode = wizNode.next;
            return returnItem;
        }
    }

    public Iterator<Item> iterator(){
        return new LinkedListIterator();
    }

    /** Adds an item x to the front of the deque. */
    @Override
    public void addFirst(Item x) {
        ItemNode i = new ItemNode(x);
        i.front = sentinel;
        i.next = sentinel.next;
        sentinel.next.front = i;
        sentinel.next = i;
        size += 1;
    }

    /** Adds an item x to the back of the deque. */
    @Override
    public void addLast(Item x) {
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
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        ItemNode i = sentinel.next;
        while(i.next != sentinel) {
            System.out.print(i.item);
            System.out.print(' ');
        }
        System.out.println(i.item);
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    @Override
    public Item removeFirst() {
        if(size == 0) {
            return null;
        } else {
            Item returnItem = sentinel.next.item;
            sentinel.next.next.front = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return returnItem;
        }
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    @Override
    public Item removeLast() {
        if(size == 0) {
            return null;
        } else {
            Item returnItem = sentinel.front.item;
            sentinel.front.front.next = sentinel;
            sentinel.front = sentinel.front.front;
            size -= 1;
            return returnItem;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. */
    @Override
    public Item get(int index) {
        if(index > size - 1) {
            return null;
        } else {
            ItemNode getItem = sentinel;
            for(int i = 0; i <= index; i++) {
                getItem = getItem.next;
            }
            return getItem.item;
        }
    }

    private ItemNode getItemRecursive = sentinel.next;
    public Item getRecursive(int index) {
        if(index > size - 1) {
            return null;
        }
        if(index == 0) {
            return getItemRecursive.item;
        } else {
            getItemRecursive = getItemRecursive.next;
            return getRecursive(index - 1);
        }
    }

    /**  Returns whether the parameter o is equal to the Deque. **/
    public boolean equals(Object o) {
        if(o instanceof LinkedListDeque) {
            LinkedListDeque<Item> otherLinkedListDeque = (LinkedListDeque<Item>) o;
            if(otherLinkedListDeque.size != this.size) {
                return false;
            }
            ItemNode itemOfo = otherLinkedListDeque.sentinel.next;
            ItemNode itemOfThisList = sentinel.next;
            while (itemOfo != sentinel) {
                if(itemOfo.item != itemOfThisList.item) {
                    return false;
                }
                itemOfo = itemOfo.next;
                itemOfThisList = itemOfThisList.next;
            }
            return true;
        } else {
            return false;
        }
    }
}
