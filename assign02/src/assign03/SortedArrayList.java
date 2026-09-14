package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SortedArrayList<E extends Comparable<? super E>> implements SortedList<E> {
    
    private E[] data;
    private int size;
    private Comparator<? super E> cmp;
    
    public SortedArrayList() {
	data = generateNewArray(16);
	size = 0;
    }
    
    
    public SortedArrayList(Comparator<? super E> cmp) {
	this();
	this.cmp = cmp;
    }
    
    @SuppressWarnings("unchecked")
    private E[] generateNewArray(int length) {
	return (E[]) new Comparable[length];
    }
    
    @Override
    public void clear() {
	data = generateNewArray(data.length);
	size = 0;
    }

    @Override
    public boolean contains(E element) {
	return binarySearch(element) > 0;
    }

    private int binarySearch(E element) {
	int low = 0, high = size - 1, mid = 0;
	while(low <= high) {
		mid = (low + high) / 2;
		if(element.compareTo(data[mid]) == 0) {
		    return mid;
		} else if(element.compareTo(data[mid]) < 0) {
		    high = mid - 1;
		} else {
		    low = mid + 1;
		}
	}
	return -1;
    }
    
    private int binarySearches(E element) {
	int low = 0, high = size - 1, mid = 0, count = 0;
	while(low <= high) {
		mid = (low + high) / 2;
		if(element.compareTo(data[mid]) == 0) {
		    count += 1;
		} else if(element.compareTo(data[mid]) < 0) {
		    high = mid - 1;
		} else {
		    low = mid + 1;
		}
	}
	return count;
    }


    @Override
    public boolean containsAll(Collection<? extends E> items) {
	for(E item : items) {
	    if(!contains(item)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public int countEntries(E target) {
	return binarySearches(target);
    }

    @Override
    public void insert(E element) {
	if(size == data.length) {
	    E[] tempArray = generateNewArray(data.length*2);
	    for(int i = 0; i < size - 1; i++) {
		tempArray[i] = data[i];
	    }
	    data = tempArray;
	}

	for (int i = size; i >= 0; i--) {
	    if(data[i-1].compareTo(element) < 0) {
		data[i] = element;
		break;
	    } else {
		data[i] = data[i-1];
	    }
	}
	size += 1;
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
	for(E item : coll) {
	    insert(item);
	}
    }

    @Override
    public boolean isEmpty() {
	return size == 0;
    }

    @Override
    public E max() throws NoSuchElementException {
	if(isEmpty()) {
	    throw new NoSuchElementException();
	}
	return data[size-1];
    }

    @Override
    public E median() throws NoSuchElementException {
	if(isEmpty()) {
	    throw new NoSuchElementException();
	}
	int middleIndex = size/2;
	return (size % 2 == 0) ? data[middleIndex + 1] : data[middleIndex];
    }

    @Override
    public E min() throws NoSuchElementException {
	if(isEmpty()) {
	    throw new NoSuchElementException();
	}
	return data[0];
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public Object[] toArray() {
	Object[] array = new Object[size];
	for (int i = 0; i < array.length; i++) {
	    array[i] = data[i];
	}
	return array;
    }

}
