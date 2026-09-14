package assign03;

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
	intList.insertAll(List.of(-5, 0, 3, 57));
    }
    @Test
    void testEmptyInsert() {
	emptyList.insert("Hello");
	assertTrue(Arrays.deepEquals(new String[]{"Hello"}, emptyList.toArray()));    
    }
    
    @Test
    void testEmptyInsertAll() {
	emptyList.insertAll(List.of("Hello", "farewell", "Bonjour", "Goodbye", "5"));
	assertTrue(Arrays.deepEquals(new String[]{"5", "Bonjour", "Goodbye", "Hello", "farewell"}, emptyList.toArray()));    
    }
}
