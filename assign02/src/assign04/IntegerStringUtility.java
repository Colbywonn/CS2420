package assign04;

import java.util.Comparator;

/**
 * @author Colby Miller & Todd Sorensen
 * @date el dia de hoy
 */
public class IntegerStringUtility<E> {
    /**
     * Sorts the input array using an insertion sort based on the Comparator that is
     * passed in.
     *
     * @param array - the list of objects to be sorted
     * @param cmp   - a Comparator to give ordering to the array
     */
    public static <E> void insertionSort(E[] array, Comparator<? super E> cmp) {

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
	// call insertionSort. you NEED to do it.
	return null;
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
	 * @return positive if o1 and 02 are similar, 0 if o1 = o1, negative if o1 is
	 *         not similar to o2.
	 */
	@Override
	public int compare(String o1, String o2) {

	    return 0;
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

	    return 0;
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
	return null;

    }

    /**
     * Locates the largest similarity group in the array
     *
     * @param array the array to search
     * @return - the subarray of the maximum similarity
     */
    public static String[] findMaximumSimilarityGroup(int[] array) {
	return null;
    }
}