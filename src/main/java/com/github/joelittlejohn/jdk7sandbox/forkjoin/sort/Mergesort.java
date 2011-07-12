package com.github.joelittlejohn.jdk7sandbox.forkjoin.sort;

public class Mergesort {

	public void sort(int[] values) {
		sort(values, 0, values.length - 1);
	}

	private void sort(int[] values, int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {

			// Get the index of the element which is in the middle
			int middle = (low + high) / 2;

			// Sort the left side of the array
			sort(values, low, middle);

			// Sort the right side of the array
			sort(values, middle + 1, high);

			// Combine them both
			merge(values, low, middle, high);
		}
	}

	private void merge(int[] values, int low, int middle, int high) {

		// Helperarray
		int[] helper = new int[values.length];

		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper[i] = values[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (helper[i] <= helper[j]) {
				values[k] = helper[i];
				i++;
			} else {
				values[k] = helper[j];
				j++;
			}
			k++;
		}

		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			values[k] = helper[i];
			k++;
			i++;
		}
		helper = null;

	}
}