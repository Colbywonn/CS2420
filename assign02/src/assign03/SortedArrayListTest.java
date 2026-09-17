package assign03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
    void testClear() {
	intList.clear();
	assertTrue(Arrays.deepEquals(new Integer[] {}, (intList.toArray())));
	assertTrue(intList.size() == 0);
    }
    
    @Test
    void testClearWhenClear() {
	emptyList.clear();
	assertTrue(Arrays.deepEquals(new String[] {}, (emptyList.toArray())));
	assertTrue(emptyList.isEmpty());
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
	assertTrue(Arrays.deepEquals(new Integer[] {-5, -5, 0, 3, 57},
		intList.toArray()));
	
	// SortedList with odd number of elements
	intList.insert(-5);
	assertTrue(Arrays.deepEquals(new Integer[] {-5, -5, -5, 0, 3, 57},
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
	assertTrue(Arrays.deepEquals(new Integer[] {-5, 0, 3, 57, 59, 6002},
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
	assertTrue(Arrays.deepEquals(new Integer[] {-5, 0, 3, 57, 57, 57},
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
    void insertCheckContains() {
	intList.insert(57);
	assertTrue(intList.contains(57));
    }
    
    @Test
    void testContainsNull() {
	assertThrows(NullPointerException.class, () -> intList.insert(null));
    }
    
    @Test
    void testContainsAll() {
	assertTrue(intList.containsAll(List.of(57, 0, 0, -5, 3, 3, 3, 3)));
    }
    @Test
    void testFalseContainsAll() {
	assertFalse(intList.containsAll(List.of(57, 0, 0, -5, 3, 3, 3, 3, 792, 424)));
    }
    
    @Test
    void insertThenContainsAll() {
	intList.insert(57);
	assertTrue(intList.containsAll(List.of(57, 0, 0, -5, 3, 3, 3, 3)));
    }

    @Test
    void testCountEntries() {
	assertEquals(1, intList.countEntries(57));
	intList.insertAll(List.of(57, 57, 57, 57, 57));
	assertEquals(6, intList.countEntries(57));
    }
    
    @Test
    void testCountEntriesDNE() {
	intList.insertAll(List.of(57, 57, 57, 57, 57));
	assertEquals(0, intList.countEntries(58));
	intList.clear();
	assertEquals(0, intList.countEntries(57));
    }

    @Test
    void testIsEmptyWhenIsEmpty() {
	assertTrue(emptyList.isEmpty());
    }
    
    @Test
    void testIsEmptyWhenIsNotEmpty() {
	assertFalse(intList.isEmpty());
	intList.clear();
	assertTrue(intList.isEmpty());
    }
    
    @Test
    void maxIsMax() {
	assertEquals(57, intList.max());
	intList.insert(999);
	assertEquals(999, intList.max());
    }
    
    void emptyArrayMax() {
	assertThrows(NoSuchElementException.class, () -> emptyList.max());
    }
    
    @Test
    void medianIsMedian() {
	assertEquals(3, intList.median());
	intList.insert(999);
	assertEquals(3, intList.median());
	intList.insert(999);
	assertEquals(57, intList.median());
	intList.insert(-63);
	assertEquals(3, intList.median());
    }
    
    void emptyArrayMedian() {
	assertThrows(NoSuchElementException.class, () -> emptyList.median());
    }
    
    @Test
    void minIsMin() {
	emptyList.insertAll(List.of("Fortnite", "Among Us", "Zelda", "Minecraft II"));
	assertEquals("Among Us", emptyList.min());
	emptyList.insert("Aaron Simulator");
	assertEquals("Aaron Simulator", emptyList.min());
    }
    
    void emptyArrayMin() {
	assertThrows(NoSuchElementException.class, () -> emptyList.min());
    }
    
    @Test
    void sizeIsZero() {
	assertEquals(0, emptyList.size());
    }
    
    @Test
    void sizeUpdate() {
	emptyList.insert("H");
	assertEquals(1, emptyList.size());
	emptyList.clear();
	assertEquals(0, emptyList.size());
    }
    
    @Test
    void toArrayEmpty() {
	
    }
   
}