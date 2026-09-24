package assign04;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign04.IntegerStringUtility.StringNumericalValueComparator;
import assign04.IntegerStringUtility.StringSimilarityComparator;
import assign04.IntegerStringUtility.StringSimilarityGroupComparator;


public class IntegerStringUtilityTest {

    Integer[] emptyIntArray;
    
    Integer[] largeIntArray;
    
    String[] largeStringArray;
    
    StringSimilarityComparator similarityComparator;
    StringSimilarityGroupComparator groupComparator;
    StringNumericalValueComparator valueComparator;
    
    Random rng;

    @BeforeEach
    void setUp() {
	similarityComparator = new StringSimilarityComparator();
	groupComparator = new StringSimilarityGroupComparator();
	valueComparator = new StringNumericalValueComparator();
	rng = new Random();
	emptyIntArray = new Integer[0];
	
	int largeIntArraySize = 1000;
	largeIntArray = new Integer[largeIntArraySize];
	int randInt = rng.nextInt();
	for (int i = 0; i < largeIntArray.length; i++) {
	    if(i % 2 != 0) {
		randInt = rng.nextInt();
	    }
	    largeIntArray[i] = randInt;
	}
	
	largeStringArray = new String[largeIntArraySize + 1];
	String initialString = generateRandomString();
	for (int i = 0; i < largeStringArray.length; i++) {
	    if(i % 2 != 0) {
		largeStringArray[i] = generateRandomString();
	    }
	    else {
		largeStringArray[i] = initialString;
	    }
	}
    }

    private String generateRandomString() {
	StringBuilder randomStringBuilder = new StringBuilder();
	int randomStringLength = rng.nextInt(100);
	
	for (int i = 0; i < randomStringLength; i++) {
	    randomStringBuilder.append(rng.nextInt(48, 58));
	}
	return randomStringBuilder.toString();
    }
    
    // insertionSort() Tests

    @Test
    void testInsertionSortEmptyArray() {
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(new Integer[] {}, Comparator.naturalOrder()));
	assertArrayEquals(emptyIntArray, new Integer[] {});
    }

    @Test
    void testInsertionSortCharArray() {
	Character[] digits = { '8', '6', '1', '0', '4' };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(digits, Comparator.naturalOrder()));
	assertArrayEquals(digits, new Character[]{'0', '1', '4', '6', '8'});
    }

    @Test
    void testInsertionSortIntegerArray() {
	Integer[] largeIntSortedArray = Arrays.copyOf(largeIntArray, largeIntArray.length);
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(largeIntSortedArray, Comparator.naturalOrder()));
	Arrays.sort(largeIntArray);
	
	assertArrayEquals(largeIntArray, largeIntSortedArray);
    }

    @Test
    void testInsertionSortStringArray() {
	String[] largeStringSortedArray = Arrays.copyOf(largeStringArray, largeStringArray.length);
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(largeStringSortedArray, Comparator.naturalOrder()));
	Arrays.sort(largeStringArray);
	
	assertArrayEquals(largeStringArray, largeStringSortedArray);
    }

    @Test
    void testInsertionSortCharArrayReverseOrder() {
	Character[] digits = { '8', '6', '1', '0', '4' };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(digits, Comparator.reverseOrder()));
	assertArrayEquals(digits, new Character[] { '8', '6', '4', '1', '0' });
    }

    @Test
    void testInsertionSortIntegerArrayReverseOrder() {
	Integer[] ints = { 348, 126, 581, -50, 0 };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(ints, Comparator.reverseOrder()));
	assertArrayEquals(ints, new Integer[] { 581, 348, 126, 0, -50 });
    }

    @Test
    void testInsertionSortStringArrayReversOrder() {
	String[] strings = new String[] { "Hello", "farewell", "Bonjour", "Goodbye", "5", "5", "%" };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(strings, Comparator.reverseOrder()));
	assertArrayEquals(strings, new String[] { "farewell", "Hello", "Goodbye", "Bonjour", "5", "5", "%" });
    }
    
    // findMax Tests
    
    @Test
    void testfindMaxEmpty() {
	assertThrows(ArrayIndexOutOfBoundsException.class,() ->IntegerStringUtility.findMax(emptyIntArray, Comparator.naturalOrder()));
    }
    
    @Test
    void testfindMax() {
	String testMax = IntegerStringUtility.findMax(largeStringArray, Comparator.naturalOrder());
	Collections.max(Arrays.asList(largeStringArray));
	assertEquals(Collections.max(Arrays.asList(largeStringArray)), testMax);
    }
    
    @Test
    void testfindMaxArrayNotAltered() {
	String[] unalteredLargeStringArray = Arrays.copyOf(largeStringArray, largeStringArray.length);
	IntegerStringUtility.findMax(largeStringArray, Comparator.naturalOrder());
	assertArrayEquals(unalteredLargeStringArray, largeStringArray);
    }
    
    // Comparator Tests
    @Test
    void testStringNumericalValueComparatorSort() {
	String[] testStrings = new String[]{"777", "777", "555", "123124", "8675309", "121132411324315356151462646363466"};
	IntegerStringUtility.insertionSort(testStrings, valueComparator);
	assertArrayEquals(new String[] {"555", "777", "777", "123124", "8675309", "121132411324315356151462646363466"}, testStrings);
    }
    
    @Test
    void testStringNumericalValueComparatorSortEmpty() {
	String[] testStrings = new String[]{};
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(testStrings, valueComparator));
	assertArrayEquals(new String[] {}, testStrings);
    }
    
    @Test
    void testStringNumericalValueComparatorFindMax() {
	String[] testStrings = new String[]{"777", "777", "555", "123124", "8675309", "121132411324315356151462646363466"};
	assertEquals("121132411324315356151462646363466", IntegerStringUtility.findMax(testStrings, valueComparator));
    }
    
    @Test
    void testStringSimilarityComparatorSort() {
	String[] testStrings = new String[]{"737", "377", "555", "123124", "8675309", "121132411324315356151462646363466"};
	IntegerStringUtility.insertionSort(testStrings, similarityComparator);
	assertArrayEquals(new String[] {"737", "377", "555", "123124", "8675309", "121132411324315356151462646363466"}, testStrings);
    }
    
    @Test
    void testStringSimilarityComparatorSortEmpty() {
	String[] testStrings = new String[]{};
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(testStrings, similarityComparator));
	assertArrayEquals(new String[] {}, testStrings);
    }
    
    @Test
    void testStringSimilarityComparatorFindMax() {
	String[] testMax = new String[]{"777", "777", "555", "123124", "8675309", "121132411324315356151462646363467",  "121132411324315356151462646363466"};
	assertEquals("121132411324315356151462646363467", IntegerStringUtility.findMax(testMax, similarityComparator));
    } 
    
    @Test
    void testStringSimilarityGroupComparatorSameSize() {
	String[] group1 = new String[]{"1112", "1121", "1211"};
	String[] group2 = new String[]{"1234", "4321", "2314"};
	assertTrue(groupComparator.compare(group1, group2) < 0);
    }
    
    @Test
    void testStringSimilarityGroupComparatorDifferentSizes() {
	String[] group1 = new String[]{"1112", "1121", "1211", "2111"};
	String[] group2 = new String[]{"1234", "4321", "2314"};
	assertTrue(groupComparator.compare(group1, group2) > 0);
    }
    
    @Test
    void testStringSimilarityGroupComparatorEmpty() {
	String[] group1 = new String[]{};
	String[] group2 = new String[]{};
	assertTrue(groupComparator.compare(group1, group2) == 0);
    }
    
    @Test
    void testStringSimilarityGroupComparatorOneEmpty() {
	String[] group1 = new String[]{};
	String[] group2 = new String[]{"1234", "4321", "2314"};
	assertTrue(groupComparator.compare(group1, group2) < 0);
    }
   
    // getSimilarityGroups Tests
    
    @Test
    void testGetSimilarityGroups() {
	String[] numbs = {"7", "8", "1234", "4321", "7", "4231", "12113243253454363246", "12113243253454363246", "12113243253454363246"};
	String[][] groupsUnderTesting = IntegerStringUtility.getSimilarityGroups(numbs);
	String[][] test = new String[][] {{"7","7"},{"1234", "4321", "4231"},{"12113243253454363246", "12113243253454363246", "12113243253454363246"}};
	assertTrue(Arrays.deepEquals(groupsUnderTesting, test));
    }
    
    @Test
    void testGetSimilarityGroupsArrayNotAltered() {
	String[] unalteredLargeStringArray = Arrays.copyOf(largeStringArray, largeStringArray.length);
	IntegerStringUtility.getSimilarityGroups(largeStringArray);
	assertArrayEquals(unalteredLargeStringArray, largeStringArray);
    }
    
    // findMaximumSimilarityGroup Tests
    
    @Test
    void testFindMaximumSimilarityGroups() {
	int[] numbs = {4, 4, 4, 1234, 4321, 9999, 9999, 7};
	String[] max = IntegerStringUtility.findMaximumSimilarityGroup(numbs);
	assertArrayEquals(max, new String[] {"4", "4", "4"});
    }
    
    @Test
    void testFindMaximumSimilarityGroupsArrayNotAltered() {
	int[] numbs = {4, 4, 4, 1234, 4321, 9999, 9999, 7};
	int[] unalteredNumbsArray = Arrays.copyOf(numbs, numbs.length);
	IntegerStringUtility.findMaximumSimilarityGroup(numbs);
	assertArrayEquals(unalteredNumbsArray, numbs);
    }
}
