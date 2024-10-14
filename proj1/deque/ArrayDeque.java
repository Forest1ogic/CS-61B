package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int front;
    private int next;
    private int size;
    private T[] itemNode;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        itemNode = (T[]) new Object[8];
        front = 3;
        next = 4;
        size = 0;
    }

    /** Return the index of the first item. */
    private int firstItemIndex() {
        int first = front + 1;
        if (first >= itemNode.length) {
            first = 0;
        }
        return first;
    }

    private void resetNextFirst(String s) {
        if (s.equals("left")) {
            front -= 1;
            if (front < 0) {
                front = itemNode.length - 1;
            }
        } else if (s.equals("right")) {
            front += 1;
            if (front >= itemNode.length) {
                front = 0;
            }
        }
    }

    private void resetNextLast(String s) {
        if (s.equals("right")) {
            next += 1;
            if (next >= itemNode.length) {
                next = 0;
            }
        } else if (s.equals("left")) {
            next -= 1;
            if (next < 0) {
                next = itemNode.length - 1;
            }
        }
    }

    /** Resize the Deque. */
    private void resizing() {
        T[] a;
        if (itemNode.length >= 16 && size * 4 < itemNode.length) {
            a = (T[]) new Object[itemNode.length / 2];
        } else {
            a = (T[]) new Object[itemNode.length * 2];
        }
        if (firstItemIndex() + size <= itemNode.length) {
            System.arraycopy(itemNode, firstItemIndex(), a, 0, size);
        } else {
            System.arraycopy(itemNode, firstItemIndex(), a, 0, itemNode.length - firstItemIndex());
            int destPos = itemNode.length - firstItemIndex();
            int length = size - (itemNode.length - firstItemIndex());
            System.arraycopy(itemNode, 0, a, destPos, length);
        }
        itemNode = a;
        front = itemNode.length - 1;
        next = size;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T i) {
        if (size == itemNode.length) {
            resizing();
        }
        itemNode[front] = i;
        size += 1;
        resetNextFirst("left");
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T i) {
        if (size == itemNode.length) {
            resizing();
        }
        itemNode[next] = i;
        size += 1;
        resetNextLast("right");
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

    /** Prints the items in the deque from first to last */
    @Override
    public void printDeque() {
        int index = front + 1;
        while (index != next - 1) {
            if (index >= itemNode.length) {
                index = 0;
            }
            System.out.print(itemNode[index]);
            System.out.print(' ');
            index += 1;
        }
        System.out.println(itemNode[index]);
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (itemNode.length >= 16 && size * 4 < itemNode.length) {
            resizing();
        }
        resetNextFirst("right");
        size -= 1;
        return itemNode[front];
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (itemNode.length >= 16 && size * 4 < itemNode.length) {
            resizing();
        }
        resetNextLast("left");
        size -= 1;
        return itemNode[next];
    }

    /** Gets the item at the given index.
     * If no such item exists, returns null. */
    @Override
    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        int first = firstItemIndex();
        if (first + index <= itemNode.length - 1) {
            return itemNode[first + index];
        } else {
            return itemNode[(index - ((itemNode.length - 1) - first)) - 1];
        }
    }

    /**  Returns whether the parameter o is equal to the Deque. **/
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> otherArrayDeque = (ArrayDeque<T>) o;
            if (this.size != otherArrayDeque.size) {
                return false;
            }
            int i = otherArrayDeque.firstItemIndex();
            int s = this.firstItemIndex();
            while (i != otherArrayDeque.next) {
                if (!this.itemNode[s].equals(otherArrayDeque.itemNode[i])) {
                    return false;
                }
                i += 1;
                s += 1;
                if (i >= otherArrayDeque.itemNode.length) {
                    i = 0;
                }
                if (s >= this.itemNode.length) {
                    s = 0;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        int wizPoz = firstItemIndex();
        @Override
        public boolean hasNext() {
            return wizPoz != next;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T returnItem = itemNode[wizPoz];
            wizPoz += 1;
            if (wizPoz >= itemNode.length) {
                wizPoz = 0;
            }
            return returnItem;
        }
    }
}
