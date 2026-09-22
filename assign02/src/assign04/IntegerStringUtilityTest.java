package assign04;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerStringUtilityTest {

    Integer[] emptyIntArray;

    @BeforeEach
    void setUp() {
	emptyIntArray = new Integer[0];
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
	Integer[] ints = { 348, 126, 581, -50, 0 };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(ints, Comparator.naturalOrder()));
	assertTrue(Arrays.deepEquals(ints, new Integer[] { -50, 0, 126, 348, 581 }));
    }

    @Test
    void testInsertionSortStringArray() {
	String[] strings = new String[] { "Hello", "farewell", "Bonjour", "Goodbye", "5", "%" };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(strings, Comparator.naturalOrder()));
	assertTrue(
		Arrays.deepEquals(strings, new String[] { "%", "5", "Bonjour", "Goodbye", "Hello", "farewell" }));
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
	String[] strings = new String[] { "Hello", "farewell", "Bonjour", "Goodbye", "5", "%" };
	assertDoesNotThrow(() -> IntegerStringUtility.insertionSort(strings, Comparator.reverseOrder()));
	assertTrue(
		Arrays.deepEquals(strings, new String[] { "farewell", "Hello", "Goodbye", "Bonjour", "5", "%" }));
    }


}
