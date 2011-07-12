package com.github.joelittlejohn.jdk7sandbox.forkjoin.sort;

/**
 * Quicksort implementation for
 * (<a href="http://en.wikipedia.org/wiki/Quicksort">http://en.wikipedia.org/wiki/Quicksort</a>)
 * integer arrays.
 */
public class Quicksort {

    /**
     * Sort the given array of integers.
     *
     * @param values the array to be sorted.
     */
    public void sort(int[] values) {
        sort(values, 0, values.length - 1);
    }

    private void sort(int[] values, int low, int high) {
        int i = low, j = high;

        // Get the pivot element from the middle of the list
        int pivot = values[low + (high - low) / 2];

        // Divide into two lists
        while (i <= j) {

            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (values[i] < pivot) {
                i++;
            }

            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (values[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(values, i, j);
                i++;
                j--;
            }
        }

        // Recursion
        if (low < j)
            sort(values, low, j);
        if (i < high)
            sort(values, i, high);
    }

    private void exchange(int[] values, int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}
