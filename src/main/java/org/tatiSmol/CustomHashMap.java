package org.tatiSmol;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * CustomHashMap class implements Map and Iterable interfaces.
 * This is a custom implementation of HashMap class.
 *
 * @param <K> the type of keys maintained by this map.
 * @param <V> the type of mapped values.
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

    /**
     * Returns the number of key-value pairs in the map.
     *
     * @return the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the map is empty.
     *
     * @return true, if size equals 0.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Computes the hash code for the given key.
     *
     * @param key the key to compute the hash code for. Must be not null.
     * @return the hash code of the key.
     */
    public int hash(@NotNull K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return Math.abs(hash);
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

    /**
     * Associates the specified value with the specified key in the map.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return the previous value associated with the key, or null if there was no mapping for the key.
     */
    @Override
    public V put(K key, V value) {
        if ((float) size / (float) table.length >= LOAD_FACTOR) {
            resize();
        }

        int index = hash(key) % table.length;
        Node<K, V> node = table[index];

        if (node == null) {
            table[index] = new Node<>(key, value);
            size++;
            return null;
        }

        while (node != null) {
            if (node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
            node = node.getNext();
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.setNext(table[index]);
        table[index] = newNode;
        size++;

        return null;
    }

    /**
     * Doubles the size of the hash table and moves all existing nodes into a new, larger table..
     */
    private void resize() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];

        for (Node<K, V> oldNode : table) {
            if (oldNode != null) {
                Node<K, V> node = oldNode;
                while (node != null) {
                    int index = hash(node.getKey()) % newCapacity;
                    Node<K, V> nextNode = node.next;
                    node.next = newTable[index];
                    newTable[index] = node;
                    node = nextNode;
                }
            }
        }

        table = newTable;
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

    /**
     * The Node inner class implements the Map.Entry interface.
     * It represents a node in MyHashMap table.
     *
     * @param <K> the type of keys maintained by this map.
     * @param <V> the type of mapped values.
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        private Node<K, V> next;
        private final K key;
        private V value;

        /**
         * Creates a new node with the specified key and value.
         *
         * @param key key for the new node.
         * @param value value for the new node.
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return key.
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.
         *
         * @return value.
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified value.
         *
         * @param value new value to be stored in this entry.
         * @return old value corresponding to the entry.
         */
        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * Returns the next node in the list.
         *
         * @return the next node in the list.
         */
        public Node<K, V> getNext() {
            return next;
        }

        /**
         * Sets the next node in the list.
         *
         * @param next the next node to be set in the list.
         */
        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        /**
         * Returns a hash code for this node.
         *
         * @return a hash code value for this object.
         */
        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        /**
         * Compares the specified object with this entry for equality.
         *
         * @param obj object to be compared for equality with this map entry.
         * @return true, if the specified object is equal to this map entry.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Node<?, ?> node = (Node<?, ?>) obj;
            return Objects.equals(key, node.getKey()) && Objects.equals(value, node.getValue());
        }
    }
}
