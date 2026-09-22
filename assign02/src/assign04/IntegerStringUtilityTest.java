package assign04;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerStringUtilityTest {

    Integer[] emptyIntArray;
    
    Integer[] largeIntArray;
    
    String[] largeStringArray;
    
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
}
