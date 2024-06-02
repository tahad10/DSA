package de.unistuttgart.dsass2024.ex05.p4;

public class Sorter {

    public static <T extends Comparable<T>> void heapSort(final ISimpleList<T> list) {
        T[] array = toArray(list);
       for (int i = array.length / 2; i >= 0; i--){
           percolate(array, i, array.length - 1);
       }
       for (int i = array.length - 1; i > 0; i--){
           swap(array, 0, i);
           percolate(array, 0, i - 1);
       }
       updateList(list, array);
    }

    private static <T extends Comparable<T>> T[] toArray(ISimpleList<T> list) {
        T[] array = (T[]) new Comparable[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static <T extends Comparable<T>> void updateList(ISimpleList<T> list, T[] array) {
        for (T item : array) {
            list.append(item);
        }
    }

    private static <T extends Comparable<T>> void swap(final T[] array, final int i, final int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T extends Comparable<T>> void percolate(final T[] array, final int idx, final int last) {
        int i = idx;
        while ((2*i)+1 <= last) {
            int j = (2*i)+1;
            if (j + 1 <= last && array[j].compareTo(array[j + 1]) > 0) {
                j++;
            }
            if (array[i].compareTo(array[j]) > 0) {
                swap(array, i, j);
                i = j;
            } else {
                break;
            }
        }
    }
}