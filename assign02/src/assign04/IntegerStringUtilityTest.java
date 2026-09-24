package assign04;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign04.IntegerStringUtility.StringSimilarityComparator;
import assign04.IntegerStringUtility.StringSimilarityGroupComparator;


public class IntegerStringUtilityTest {

    Integer[] emptyIntArray;
    
    Integer[] largeIntArray;
    
    String[] largeStringArray;
    
    StringSimilarityComparator similarityComparator = new StringSimilarityComparator();
    StringSimilarityGroupComparator groupComparator = new StringSimilarityGroupComparator();
    
    Random rng;

    @BeforeEach
    void setUp() {
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

    @Test
    void testInsertionSortEmptyArray() {
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(new Integer[] {}, Comparator.naturalOrder()));
	assertTrue(Arrays.deepEquals(emptyIntArray, new Integer[] {}));
    }

    @Test
    void testInsertionSortCharArray() {
	Character[] digits = { '8', '6', '1', '0', '4' };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(digits, Comparator.naturalOrder()));
	assertTrue(Arrays.equals(digits, new Character[]{'0', '1', '4', '6', '8'}));
    }

    @Test
    void testInsertionSortIntegerArray() {
	Integer[] largeIntSortedArray = Arrays.copyOf(largeIntArray, largeIntArray.length);
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(largeIntSortedArray, Comparator.naturalOrder()));
	Arrays.sort(largeIntArray);
	
	assertTrue(Arrays.deepEquals(largeIntArray, largeIntSortedArray));
    }

    @Test
    void testInsertionSortStringArray() {
	String[] largeStringSortedArray = Arrays.copyOf(largeStringArray, largeStringArray.length);
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(largeStringSortedArray, Comparator.naturalOrder()));
	Arrays.sort(largeStringArray);
	
	assertTrue(Arrays.deepEquals(largeStringArray, largeStringSortedArray));
    }

    @Test
    void testInsertionSortCharArrayReverseOrder() {
	Character[] digits = { '8', '6', '1', '0', '4' };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(digits, Comparator.reverseOrder()));
	assertTrue(Arrays.deepEquals(digits, new Character[] { '8', '6', '4', '1', '0' }));
    }

    @Test
    void testInsertionSortIntegerArrayReverseOrder() {
	Integer[] ints = { 348, 126, 581, -50, 0 };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(ints, Comparator.reverseOrder()));
	assertTrue(Arrays.deepEquals(ints, new Integer[] { 581, 348, 126, 0, -50 }));
    }

    @Test
    void testInsertionSortStringArrayReversOrder() {
	String[] strings = new String[] { "Hello", "farewell", "Bonjour", "Goodbye", "5", "5", "%" };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(strings, Comparator.reverseOrder()));
	assertTrue(
		Arrays.deepEquals(strings, new String[] { "farewell", "Hello", "Goodbye", "Bonjour", "5", "5", "%" }));
    }
    
    //TODO findMax method
    
    @Test
    void findMaxNullTest() {
	assertThrows(ArrayIndexOutOfBoundsException.class,() ->IntegerStringUtility.findMax(emptyIntArray, Comparator.naturalOrder()));
    }
    
    @Test
    void findMaxTest() {
	String testMax = IntegerStringUtility.findMax(largeStringArray, Comparator.naturalOrder());
	String max = "";
	for(String s : largeStringArray) {
	    if(s.compareTo(max) > 0) {
		max = s;
	    }
	}
	assertEquals(max, testMax);
    }
    
    @Test
    void findMaxDifferentComparatorTest() {
	String[] testMax = new String[]{"777", "777", "555", "123124", "8675309", "-412", "121132411324315356151462646363466"};
	assertEquals("121132411324315356151462646363466", IntegerStringUtility.findMax(testMax, similarityComparator));
    }
    
    @Test
    void findMaxWhenInputIsNonNumeric() {
	String[] testMax = new String[]{"777", "7$7", "12", "644"};
	String test = IntegerStringUtility.findMax(testMax, Comparator.naturalOrder());
	assertEquals("777", test);
    }    
    
    
    //TODO comparators
    
    
    //TODO more similarity groups ones
    
    @Test
    void testSimilarityGroupArray() {
	String[] numbs = {"7", "8", "1234", "4321", "7", "4231", "12113243253454363246", "12113243253454363246", "12113243253454363246"};
	String[][] groupsUnderTesting = IntegerStringUtility.getSimilarityGroups(numbs);
	String[][] test = new String[][] {{"7","7"},{"1234", "4321", "4231"},{"12113243253454363246", "12113243253454363246", "12113243253454363246"}};
	assertTrue(Arrays.deepEquals(groupsUnderTesting, test));
    }
    
    //TODO more max similarity groups ones
    
    @Test
    void testMaximumSimilarityGroups() {
	int[] numbs = {4, 4, 4, 1234, 4321, 9999, 9999, 7};
	String[] max = IntegerStringUtility.findMaximumSimilarityGroup(numbs);
	assertTrue(Arrays.deepEquals(max, new String[] {"4", "4", "4"}));
    }
}
