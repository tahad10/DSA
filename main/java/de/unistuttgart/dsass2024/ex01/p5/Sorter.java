package de.unistuttgart.dsass2024.ex01.p5;

public class Sorter {

    /**
     * Performs selection sort on provided list, works directly on list object,
     * hence no return value
     * 
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void selectionSort(ISimpleList<T> list) {
        int maxIndex;
        for (int i = 0; i < list.getSize() - 1; i++){
            maxIndex = i;
            for (int j = i + 1; j < list.getSize(); j++){
                if (list.getElement(j).compareTo(list.getElement(maxIndex)) > 0){
                    maxIndex = j;
                }
            }
            list.swapElements(i, maxIndex);
        }
    }

    /**
     * Performs insertion sort on provided list, works directly on list object,
     * hence no return value
     * 
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void insertionSort(ISimpleList<T> list) {
      for (int i = 1; i < list.getSize(); i++){
          T key = list.getElement(i);
          int j = i -1;
          while (j >= 0 && list.getElement(j).compareTo(key) > 0){
              list.swapElements(j + 1, j);
              j--;
          }
          list.swapElements(j + 1, i);
      }
    }

    /**
     * Performs bubble sort on provided list, works directly on list object, hence
     * no return value
     * 
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void bubbleSort(ISimpleList<T> list) {
        for (int i = 0; i < list.getSize() - 1; i++){
            for (int j = 0; j < list.getSize() - i - 1; j++){
                if (list.getElement(j).compareTo(list.getElement(j + 1)) < 0){
                    list.swapElements(j, j + 1);
                }
            }
        }
    }
}
