package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SortedArrayList<E> implements SortedList<E> {

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

    @Override
    public void clear() {
	data = generateNewArray(data.length);
	size = 0;
    }

    @Override
    public boolean contains(E element) {
	E elementFromSearch = data[binarySearch(element)];
	return elementFromSearch != null && elementFromSearch.equals(element);
    }

    @Override
    public boolean containsAll(Collection<? extends E> items) {
	for (E item : items) {
	    if (!contains(item)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public int countEntries(E target) {
	int count = 0;
	int targetIndex = binarySearch(target);
	if(!data[targetIndex].equals(target)) {
	    return count;
	}
	
	count++;
	int lowerIndex = Math.max(0, targetIndex - 1);
	int higherIndex = targetIndex + 1;
	while((lowerIndex != 0 && data[lowerIndex].equals(target))) {
	    count++;
	    lowerIndex--;
	}
	while(higherIndex != size && data[higherIndex].equals(target)) {
	    count++;
	    higherIndex++;
	}
	return count;
    }

    @Override
    public void insert(E element) {
	if (size >= data.length) {
	    doubleBackingArray();
	}
	shiftInsert(element, binarySearch(element));
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
	for (E item : coll) {
	    insert(item);
	}
    }

    @Override
    public boolean isEmpty() {
	return size == 0;
    }

    @Override
    public E max() throws NoSuchElementException {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	return data[size - 1];
    }

    @Override
    public E median() throws NoSuchElementException {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	int middleIndex = size / 2;
	return (size % 2 == 0) ? data[middleIndex + 1] : data[middleIndex];
    }

    @Override
    public E min() throws NoSuchElementException {
	if (isEmpty()) {
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

    @SuppressWarnings("unchecked")
    private E[] generateNewArray(int length) {
	return (E[]) new Object[length];
    }

    private void doubleBackingArray() {
	E[] tempArray = generateNewArray(data.length * 2);
	for (int i = 0; i < size; i++) {
	    tempArray[i] = data[i];
	}
	data = tempArray;
    }

    @SuppressWarnings("unchecked")
    private int innerCompare(E elt1, E elt2) {
	if (cmp == null) {
	    return ((Comparable<? super E>) elt1).compareTo(elt2);
	}
	return cmp.compare(elt1, elt2);
    }

    private int binarySearch(E element) {
	int low = 0, high = size - 1, mid = 0;
	while (low <= high) {
	    mid = (low + high) / 2;
	    if (innerCompare(element, data[mid]) == 0) {
		return mid;
	    }
	    if (innerCompare(element, data[mid]) < 0) {
		high = mid - 1;
	    } else {
		low = mid + 1;
	    }
	}
	return low;
    }
    
    private void shiftInsert(E element, int insertionPoint) {
   	for (int i = size; i > insertionPoint; i--) {
   	    data[i] = data[i - 1];
   	}
   	data[insertionPoint] = element;
   	size++;
       }
}
