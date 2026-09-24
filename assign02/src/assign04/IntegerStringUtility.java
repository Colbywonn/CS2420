package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class that compares positive integer values represented as strings
 * and groups them by similarity. Similarity is defined by the internal
 * StringSimilarityComparator class's Comparator implementation.
 *
 * @author Colby Miller & Todd Sorensen
 * @date 9/24/2026
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
     * @param cmp   - a Comparator object that gives ordering to the array
     */
    public static <E> void insertionSort(E[] array, Comparator<? super E> cmp) {
	for (int i = 0; i < array.length - 1; i++) {
	    int n = i;
	    while (n >= 0 && cmp.compare(array[n], array[n + 1]) > 0) {
		E temp = array[n + 1];
		array[n + 1] = array[n];
		array[n] = temp;
		n--;
	    }
	}
    }

    /**
     * Returns the largest element in the input array, according to the input
     * Comparator object.
     *
     * @param array - the list to find the largest element of
     * @param cmp   - a Comparator object that gives ordering to the array
     * @return the largest element in the array
     */
    public static <E> E findMax(E[] array, Comparator<? super E> cmp) {
	E[] tempArray = Arrays.copyOf(array, array.length);
	insertionSort(tempArray, cmp);
	return tempArray[tempArray.length - 1];
    }

    /**
     * Comparator implementation that defines the comparison of positive integer
     * values represented as strings, numerically.
     *
     * NOTE: The behavior of this comparator is undefined if one or both of the
     * strings being compared do not represent a positive integer value.
     */
    public static class StringNumericalValueComparator implements Comparator<String> {
	/**
	 * Compares the integer values of two strings.
	 *
	 * @param o1 - the first integer value to compare
	 * @param o2 - the second integer value to compare
	 * @return positive if o1 > o2, 0 if o1 = o1, negative if o1 < o2.
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
     * Comparator implementation that defines the comparison of strings, by
     * similarity. Similarity of two strings means that their digits can be
     * rearranged to form the same value.
     */
    public static class StringSimilarityComparator implements Comparator<String> {
	/**
	 * If two strings have different lengths, they are not similar and the shorter
	 * string comes before the longer string. If two strings have the same length
	 * but are not similar, the sorted order of the characters are compared
	 * lexicographically.
	 *
	 * @param o1 - the first integer value to compare
	 * @param o2 - the second integer value to compare
	 * @return positive if o1 is larger than o2, 0 if o1 is similar to o2, negative
	 *         if o1 is smaller than o2.
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
     * This nested class defines the comparison of similarity groups by group size
     * (i.e., array length).
     */
    public static class StringSimilarityGroupComparator implements Comparator<String[]> {
	/**
	 * If two groups have the same size, the group with the largest integer value
	 * (represented as a string) is deemed the largest group. If both groups are
	 * empty, they are deemed equal.
	 *
	 * @param o1 - the first group to compare
	 * @param o2 - the second group to compare
	 * @return positive if o1 > o2, 0 if o1 = o1, negative if o1 < o2.
	 */
	@Override
	public int compare(String[] o1, String[] o2) {
	    if (o1.length == 0 && o2.length == 0) {
		return 0;
	    }
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
     * in a row is similar to every other string in the same row. If the length of
     * array is zero, returns an empty String array.
     *
     * @param array - the array of strings to extract similarity groups from
     * @return a 2d array containing the similarity groups of the given array
     */
    public static String[][] getSimilarityGroups(String[] array) {
	if (array.length == 0) {
	    return new String[][] {};
	}
	String[] sortedArray = Arrays.copyOf(array, array.length);

	insertionSort(sortedArray, similarityComparator);

	List<List<String>> groupsList = new ArrayList<>();
	groupsList.add(new ArrayList<>(Arrays.asList(sortedArray[0])));

	for (int i = 1; i < sortedArray.length; i++) {
	    if (similarityComparator.compare(sortedArray[i - 1], sortedArray[i]) != 0) {
		groupsList.add(new ArrayList<>());
	    }
	    groupsList.getLast().add(sortedArray[i]);
	}

	groupsList.removeIf(list -> list.size() <= 1);
	return listToArray(groupsList);
    }

    /**
     * Locates the largest similarity group in the array. If the length of array is
     * zero, returns an empty String array.
     *
     * @param array - the array to search through
     * @return the largest similarity group
     */
    public static String[] findMaximumSimilarityGroup(int[] array) {
	if (array.length == 0) {
	    return new String[] {};
	}
	String[][] groupsArray = getSimilarityGroups(intToStringArray(array));
	String[] largestGroup = groupsArray[0];

	for (String[] group : groupsArray) {
	    if (groupComparator.compare(group, largestGroup) > 0) {
		largestGroup = group;
	    }
	}
	return largestGroup;
    }

    /**
     * Helper method that converts a primitive integer array into a String array
     * where each value is converted into a string. IE: the input [1, 2, 3, 4] will
     * give the output ["1", "2", "3", "4"].
     *
     * @param array - the values to be converted into strings
     * @return the String array
     */
    private static String[] intToStringArray(int[] array) {
	String[] stringArray = new String[array.length];

	for (int i = 0; i < stringArray.length; i++) {
	    stringArray[i] = String.valueOf(array[i]);
	}
	return stringArray;
    }

    /**
     * Helper method which converts a string into an array of Character
     * wrapper-class objects.
     *
     * @param s - the string to be converted
     * @return Character array containing each character in the given string
     */
    private static Character[] stringToCharacterArray(String s) {
	Character[] characterArray = new Character[s.length()];
	char[] charArray = s.toCharArray();
	for (int i = 0; i < s.length(); i++) {
	    characterArray[i] = charArray[i];
	}
	return characterArray;
    }

    /**
     * Helper method that converts an array of Character wrapper-class objects into
     * a single String object.
     *
     * @param array - the Characters to be appended to a String
     * @return the finished String
     */
    private static String characterToString(Character[] array) {
	StringBuilder builder = new StringBuilder();
	for (Character character : array) {
	    builder.append(character);
	}
	return builder.toString();
    }

    /**
     * Helper method that converts a 2d List of type String to a 2d String array.
     *
     * @param groupsList - the 2d List to be converted into a 2d String array
     * @return the 2d String array version of the input 2d List
     */
    private static String[][] listToArray(List<List<String>> groupsList) {
	String[][] groupsArray = new String[groupsList.size()][];
	for (int i = 0; i < groupsList.size(); i++) {
	    groupsArray[i] = groupsList.get(i).toArray(new String[0]);
	}
	return groupsArray;
    }
}