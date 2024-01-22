import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tatiSmol.CustomHashMap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {
    CustomHashMap<Integer, String> map;

    @BeforeEach
    public void setup() {
        map = new CustomHashMap<>();
        for (int i = 1; i <= 1_000_000; i++) {
            map.put(i, "value" + i);
        }
    }

    @Test
    public void testConstructorWithCollection() {
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        Map<Integer, String> anotherMap = new HashMap<>();
        for (int i = 1; i <= 1_000_000; i++) {
            anotherMap.put(i, "value" + i);
        }

        map = new CustomHashMap<>(anotherMap);
        assertEquals(anotherMap.size(), map.size());
    }

    @Test
    public void testGet() {
        for (int i = 1; i <= 1_000_000; i++) {
            assertEquals("value" + i, map.get(i));
        }
    }

    @Test
    public void testRemove() {
        for (int i = 1; i <= 1_000_000; i++) {
            map.remove(i);
        }

        for (int i = 1; i <= 1_000_000; i++) {
            assertNull(map.get(i));
        }
    }

    @Test
    public void testContainsKey() {
        for (int i = 1; i <= 1_000_000; i++) {
            assertTrue(map.containsKey(i));
        }
    }

    @Test
    public void testContainsValue() {
        for (int i = 1; i <= 1_000_000; i += 200) {
            assertTrue(map.containsValue("value" + i));
        }
    }

    @Test
    public void testKeySet() {
        Set<Integer> keys = map.keySet();
        assertTrue(keys.contains(10));
        assertTrue(keys.contains(500_001));
    }

    @Test
    public void testValues() {
        Collection<String> values = map.values();
        assertTrue(values.contains("value10"));
        assertTrue(values.contains("value500001"));
    }

    @Test
    public void testEntrySet() {
        map.clear();

        for (int i = 0; i < 100_000; i++) {
            map.remove(i);
            map.put(i, "value" + i);
        }

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            assertTrue(map.containsKey(entry.getKey()));
            assertTrue(map.containsValue(entry.getValue()));
        }
    }

    @Test
    public void testIterator() {
        map.clear();

        for (int i = 0; i < 100_000; i++) {
            map.remove(i);
            map.put(i, "value" + i);
        }

        for (Map.Entry<Integer, String> entry : map) {
            assertTrue(map.containsKey(entry.getKey()));
            assertTrue(map.containsValue(entry.getValue()));
        }
    }

    @Test
    public void testCollision() {
        String key1 = "FB";
        String key2 = "Ea";

        assertEquals(key1.hashCode(), key2.hashCode());

        map.put(1, key1);
        map.put(2, key2);

        assertEquals(key1, map.get(1));
        assertEquals(key2, map.get(2));
    }
}
