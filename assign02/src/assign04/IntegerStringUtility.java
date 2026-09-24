package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Colby Miller & Todd Sorensen
 * @date el dia de hoy
 */
public class IntegerStringUtility<E> {
    private static StringNumericalValueComparator valueComparator = new StringNumericalValueComparator();
    private static StringSimilarityComparator similarityComparator = new StringSimilarityComparator();
    private static StringSimilarityGroupComparator groupComparator = new StringSimilarityGroupComparator();
    /**
     * Sorts the input array using an insertion sort based on the Comparator that is
     * passed in.
     *
     * @param array - the list of objects to be sorted
     * @param cmp   - a Comparator to give ordering to the array
     */
    public static <E> void insertionSort(E[] array, Comparator<? super E> cmp) {
	for(int i = 0; i < array.length-1; i++) {
	    int n = i;
	    while(n >= 0 && cmp.compare(array[n], array[n+1]) > 0) {
		E temp = array[n+1];
		array[n+1] = array[n];
		array[n] =  temp;
		n--;
	    }
	}
    }

    /**
     * Returns the largest element in the input array, according to the input
     * Comparator object.
     *
     * @param array - the list to find the max of
     * @param cmp   - a Comparator to give ordering to the array
     * @return the largest element in the array
     */
    public static <E> E findMax(E[] array, Comparator<? super E> cmp) {
	E[] tempArray = Arrays.copyOf(array, array.length);
	insertionSort(tempArray, cmp);
	return tempArray[tempArray.length-1];
    }

    /**
     * An implementation of the Comparator interface for strings with numerical
     * values.
     */
    public static class StringNumericalValueComparator implements Comparator<String> {
	/**
	 * compares two numeric Strings.
	 *
	 * @param o1 - the number in a String to compare
	 * @param o2 - the number in a String to compare to
	 * @return positive if o1 > 02, 0 if o1 = o1, negative if o1 < o2.
	 */
	@Override
	public int compare(String o1, String o2) {
	    while (o1.length() < o2.length()) {
		o1 = "0" + o1;
	    }
	    while (o2.length() < o1.length()) {
		o2 = "0" + o2;
	    }
	    return o1.compareTo(o2);
	}
    }

    /**
     * An implementation of the Comparator interface for strings with similar
     * numerical structures.
     */
    public static class StringSimilarityComparator implements Comparator<String> {
	/**
	 * Defines the comparison of strings, by similarity. When two strings have
	 * different lengths, they are not similar and the shorter string comes before
	 * the longer string. When two strings have the same length but are not similar,
	 * the sorted order of the characters are compared lexicographically.
	 *
	 * @param o1 - the number in a String to compare
	 * @param o2 - the number in a String to compare to
	 * @return positive if o1 is larger than o2, 0 if o1 is similar to  o2, negative if o1 is
	 *         smaller than o2.
	 */
	@Override
	public int compare(String o1, String o2) {
	    Character[] o1c = stringToCharacterArray(o1), o2c = stringToCharacterArray(o2);
	    insertionSort(o1c, Comparator.naturalOrder());
	    insertionSort(o2c, Comparator.naturalOrder());
	    return valueComparator.compare(characterToString(o1c), characterToString(o2c));
	}
    }

    /**
     * Defines the comparison of similarity groups by the length of the array.
     */
    public static class StringSimilarityGroupComparator implements Comparator<String[]> {
	/**
	 * If two groups have the same size, the group with the largest integer value
	 * (represented as a string) is deemed the largest group. If both groups are
	 * empty, they are deemed equal.
	 *
	 * @param o1 - the first string array
	 * @param o2 - the string array to compare to
	 * @return positive if o1 > 02, 0 if o1 = o1, negative if o1 < o2.
	 */
	@Override
	public int compare(String[] o1, String[] o2) {
	    int comp = ((Integer) o1.length).compareTo(o2.length);
	    if (comp == 0) {
		return valueComparator.compare(findMax(o1, valueComparator), findMax(o2, valueComparator));
	    }
	    return comp;
	}

    }

    /**
     * Returns the similarity groups in the input array. Each row in the
     * two-dimensional array returned is a single similarity group, and each string
     * in a row is similar to every other string in the same row.
     *
     * @param array - the array to compare to
     * @return a 2d array representing the similarities.
     */
    public static String[][] getSimilarityGroups(String[] array) {
	String[] sortedArray = Arrays.copyOf(array, array.length);
	
	insertionSort(sortedArray, similarityComparator);
	
	List<List<String>> groupsList = new ArrayList<>();
	groupsList.add(new ArrayList<>(Arrays.asList(sortedArray[0])));
	
	for (int i = 1; i < sortedArray.length; i++) {
	    if(similarityComparator.compare(sortedArray[i-1], sortedArray[i]) != 0) {
		groupsList.add(new ArrayList<String>());
	    }
	    groupsList.getLast().add(sortedArray[i]);
	}
	
	groupsList.removeIf(list -> list.size() <= 1);
	
	String[][] groupsArray = new String[groupsList.size()][];
	for(int i = 0; i < groupsList.size(); i++) {
	    groupsArray[i] = groupsList.get(i).toArray(new String[0]);
	}
	return groupsArray;
    }

    /**
     * Locates the largest similarity group in the array.
     *
     * @param array the array to search
     * @return - the subarray of the maximum similarity
     */
    public static String[] findMaximumSimilarityGroup(int[] array) {
	String[][] groupsArray = getSimilarityGroups(intToStringArray(array));
	String[] largestGroup = groupsArray[0];
	
	for(String[] group : groupsArray) {
	    if(groupComparator.compare(group, largestGroup) > 0) {
		largestGroup = group;
	    }
	}
	return largestGroup;
    }
    
    
    
    private static  String[] intToStringArray(int[] array) {
	String[] stringArray = new String[array.length];
	
	for (int i = 0; i < stringArray.length; i++) {
	    stringArray[i] = String.valueOf(array[i]);
	}
	return stringArray;
    }

    private static Character[] stringToCharacterArray(String s) {
	Character[] characterArray = new Character[s.length()];
	char[] charArray = s.toCharArray();
	for (int i = 0; i < s.length(); i++) {
	    characterArray[i] = charArray[i];
	}
	return characterArray;
    }
    
    private static String characterToString(Character[] array) {
	StringBuilder builder = new StringBuilder();
	for(Character character : array) {
	    builder.append(character);
	}
	return builder.toString();
    }
}