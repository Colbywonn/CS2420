package assign03;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedArrayListTest {
    SortedList<String> emptyList;
    SortedList<Integer> intList;

    @BeforeEach
    void setUp() {
	emptyList = new SortedArrayList<>();
	intList = new SortedArrayList<>();
	intList.insertAll(List.of(57, 0, -5, 3));
    }

    @Test
    void testEmptyInsert() {
	emptyList.insert("Hello");
	assertTrue(Arrays.deepEquals(new String[] { "Hello" }, (emptyList.toArray())));
    }

    @Test
    void testEmptyInsertAll() {
	emptyList.insertAll(List.of("Hello", "farewell", "Bonjour", "Goodbye", "5"));
	assertTrue(Arrays.deepEquals(new String[] { "5", "Bonjour", "Goodbye", "Hello", "farewell" },
		emptyList.toArray()));
    }
    
    @Test
    void testInsertNewMin() {
	// SortedList with even number of elements
	intList.insert(-6);
	assertTrue(Arrays.deepEquals(new Integer[] {-6, -5, 0, 3, 57},
		intList.toArray()));
	
	// SortedList with odd number of elements
	intList.insert(-7);
	assertTrue(Arrays.deepEquals(new Integer[] {-7, -6, -5, 0, 3, 57},
		intList.toArray()));
    }
    
    @Test
    void testInsertNewMinDuplicate() {
	// SortedList with even number of elements
	intList.insert(-5);
	assertTrue(Arrays.deepEquals(new Integer[] {-6, -5, 0, 3, 57},
		intList.toArray()));
	
	// SortedList with odd number of elements
	intList.insert(-5);
	assertTrue(Arrays.deepEquals(new Integer[] {-7, -6, -5, 0, 3, 57},
		intList.toArray()));
    }
    
    @Test
    void testInsertNewMax() {
	// Test with SortedList with even number of elements
	intList.insert(59);
	assertTrue(Arrays.deepEquals(new Integer[] {-5, 0, 3, 57, 59},
		intList.toArray()));
	
	// Test with SortedList with odd number of elements
	intList.insert(6002);
	assertTrue(Arrays.deepEquals(new Integer[] {-7, -6, -5, 0, 3, 57, 59, 6002},
		intList.toArray()));
    }
    
    @Test
    void testInsertNewMaxDuplicate() {
	// Test with SortedList with even number of elements
	intList.insert(57);
	assertTrue(Arrays.deepEquals(new Integer[] {-5, 0, 3, 57, 57},
		intList.toArray()));
	
	// Test with SortedList with odd number of elements
	intList.insert(57);
	assertTrue(Arrays.deepEquals(new Integer[] {-7, -6, -5, 0, 3, 57, 57, 57},
		intList.toArray()));
    }
    
    @Test
    void testInsertAllDuplicates() {
	// Test with SortedList with even number of elements
	emptyList.insertAll(List.of("a", "a", "a", "a", "a", "a"));
	assertTrue(Arrays.deepEquals(new String[] {"a", "a", "a", "a", "a", "a"},
		emptyList.toArray()));
	
	emptyList.clear();
	// Test with SortedList with odd number of elements
	emptyList.insertAll(List.of("a", "a", "a", "a", "a", "a", "a"));
	assertTrue(Arrays.deepEquals(new String[] {"a", "a", "a", "a", "a", "a", "a"},
		emptyList.toArray()));
    }
    
    


    @Test
    void testInsertSort() {
	intList.clear();
	for (int i = 570; i >= 0; i--) {
	    if (i != 566) {
		intList.insert(i);
	    }
	}
	assertFalse(intList.contains(566));
    }

    @Test
    void testClear() {
	intList.clear();
	assertTrue(Arrays.deepEquals(new Integer[] {}, (intList.toArray())));
	assertTrue(intList.size() == 0);
    }

    @Test
    void testClearWhenClear() {
	emptyList.clear();
	assertTrue(Arrays.deepEquals(new String[] {}, (emptyList.toArray())));
	assertTrue(emptyList.size() == 0);
    }
    
    @Test
    void testEmptyContains() {
	assertFalse(emptyList.contains("dshfs"));
    }
    
    @Test
    void testContainsAndNotContains() {
	assertTrue(intList.contains(-5));
	assertFalse(intList.contains(91));
    }
    
    
}