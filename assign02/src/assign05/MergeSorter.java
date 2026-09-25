package assign05;

import java.util.ArrayList;

public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E> {

    private int threshold;

    public MergeSorter(int threshold) {
	if (threshold <= 0) {
	    throw new IllegalArgumentException(
		    "Cannot construct MergeSorter object. Threshold: " + threshold + "is not positive");
	}
	this.threshold = threshold;
    }

    @Override
    public void sort(ArrayList<E> list) {
	int sizeOfList = list.size();
	if (sizeOfList < threshold) {
	    threshold = sizeOfList;
	}

	E[] tempArray = createArray(sizeOfList);

	mergeSort(list, 0, sizeOfList, tempArray);
    }

    private void mergeSort(ArrayList<E> list, int start, int end, E[] tempArray) {
	if ((end - start) > threshold) {
	    int mid = (start + end) / 2;
	    mergeSort(list, start, mid, tempArray);
	    mergeSort(list, start, mid + 1, tempArray);
	    merge(list, start, mid + 1, end, tempArray);
	} else {
	    insertionSort(start, end, list);
	}

    }

    private void insertionSort(int start, int end, ArrayList<E> list) {
	for (int i = start; i < end - 1; i++) {
	    int n = i;
	    while (n >= 0 && list.get(n).compareTo(list.get(n+1)) > 0) {
		E temp = list.get(n+1);
		list.set(n+1, list.get(n));
		list.set(n, temp);
		n--;
	    }
	}
    }

    private void merge(ArrayList<E> list, int start, int mid, int end, E[] tempArray) {
	int location = 0;
	while(start < mid && mid < end) {
	if (start <= end) {
	    tempArray[location] = list.get(start);
	    start++;
	} else {
	    tempArray[location] = list.get(mid);
	    mid++;
	}
	location++;
	}
	while(start < mid) {
	    tempArray[location] = list.get(start);
	    start++;
	    location++;
	}
	while(mid < end) {
	    tempArray[location] = list.get(mid);
	    mid++;
	    location++;
	}
	setEquals(list, tempArray, start);

    }

    
    
    private void setEquals(ArrayList<E> list, E[] tempArray, int start) {
	for(int i = start; i < tempArray.length; i++) {
	    list.set(i, tempArray[i]);
	}
	
    }

    @SuppressWarnings("unchecked")
    private E[] createArray(int len) {
	return (E[]) new Object[len];
    }
}
