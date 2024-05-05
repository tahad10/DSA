package de.unistuttgart.dsass2024.ex02.p5;

/**
 * This class implements the ISimpleList interface.
 * It is a simple list that can store elements of type T.
 *
 * @param <T> The type of list element
 */
public class SimpleList<T extends Comparable<T>> implements ISimpleList<T> {
    private ISimpleListNode<T> head;
    private int size;

    public SimpleList(ISimpleListNode<T> head, int size){
        this.head = head;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Prepends an element at the start of the list.
     *
     * @param element Element to be appended
     */
    @Override
    public void prepend(T element) {
         ISimpleListNode<T> newNode = new SimpleListNode<T>(element, head);
         newNode.setNext(head);
         head = newNode;
         size++;
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index Index of the element
     */
    @Override
    public T getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ISimpleListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    /**
     * This method sorts your list with Bubble Sort
     */
    @Override
    public void sort() {
        if (head == null || head.getNext() == null) {
            return;
        }
        boolean swapped;
        ISimpleListNode<T> next = null;
        do {
            swapped = false;
            ISimpleListNode<T> current = head;
            while (current.getNext() != next) {
                if (current.getElement().compareTo(current.getNext().getElement()) > 0) {
                    T temp = current.getElement();
                    current.setElement(current.getNext().getElement());
                    current.getNext().setElement(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
            next = current;
        } while (swapped);
    }
}