package org.tatiSmol;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * CustomHashMap class implements Map and Iterable interfaces.
 * This is a custom implementation of HashMap class.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class CustomHashMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int size = 0;

    /**
     * Constructs an empty CustomHashMap with the default initial capacity (16).
     */
    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty CustomHashMap with the custom initial capacity.
     *
     * @param initialCapacity the initial capacity of the hash map. Must be non-negative.
     * @throws IllegalArgumentException if capacity less than 0.
     */
    public CustomHashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity can't be negative");
        }
        table = new Node[initialCapacity];
    }

    /**
     * Constructs CustomHashMap with the default initial capacity (16) and
     * adds all key-value pairs from the specified map.
     *
     * @param m map containing the key-value pairs that will be added to the new hash map.
     */
    public CustomHashMap(Map<? extends K, ? extends V> m) {
        this();
        putAll(m);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    static class Node<K, V> implements Map.Entry<K, V> {

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }
}
