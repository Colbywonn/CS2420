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

    @SuppressWarnings("unchecked")
    @Override
    public void sort(ArrayList<E> list) {
	int sizeOfList = list.size();
	if (sizeOfList < threshold) {
	    threshold = sizeOfList;
	}
	
	E[] tempArray = (E[]) new Object[sizeOfList];
	
	mergeSort(list, sizeOfList, sizeOfList, tempArray);
    }

    private void mergeSort(ArrayList<E> list, int start, int end, E[] tempArray) {
	
	if((end-start) > threshold) {
	    int mid = (start + end) / 2;
	    mergeSort(list, start, mid, tempArray);
	    mergeSort(list, start, mid + 1, tempArray);
	}
	

    }
}
