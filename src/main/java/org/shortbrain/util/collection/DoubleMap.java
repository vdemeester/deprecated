package org.shortbrain.util.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Two Dimension Map. An object that maps 2 keys for 1 value. A DoubleMap cannot
 * contain duplicate keys (by key1/key2). This means each couple (K1,K2) can map
 * to at most one value.
 * 
 * @author vincent
 * 
 */
public interface DoubleMap<K1, K2, V> {

	/**
	 * Returns the value which the specified couple is mapped, or null if this map
	 * contains no mapping for this couple.
	 * 
	 * @param key1
	 * @param key2
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public V get(K1 key1, K2 key2) throws ClassCastException,
			NullPointerException;

	/**
	 * Returns the map which the specified key is mapped, or null if this map
	 * contains no mapping for this key.
	 * 
	 * @param key
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public Map<K2, V> getMapByKey1(K1 key) throws ClassCastException,
			NullPointerException;

	/**
	 * Returns the map which the specified key is mapped, or null if this map
	 * contains no mapping for this key.
	 * 
	 * @param key
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public Map<K1, V> getMapByKey2(K2 key) throws ClassCastException,
			NullPointerException;

	/**
	 * Removes all of the mappings from this doubleMap. The map will be empty
	 * after the returns call.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void clear() throws UnsupportedOperationException;

	/**
	 * Returns <code>true</code> if this map contains a mapping for the specified
	 * key (in either dimension).
	 * 
	 * @param key
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public boolean containsKey(Object key) throws ClassCastException,
			NullPointerException;

	/**
	 * Returns <code>true</code> if this map contains a mapping for the specified
	 * couple (in either dimension).
	 * 
	 * @param key
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public boolean containsKeys(Object key1, Object key2)
			throws ClassCastException, NullPointerException;;

	/**
	 * Returns <code>true</code> if this map contains a mapping for the specified
	 * key (in the K1 dimension).
	 * 
	 * @param key
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public boolean containsKey1(Object key) throws ClassCastException,
			NullPointerException;;

	/**
	 * Returns <code>true</code> if this map contains a mapping for the specified
	 * key (in the K2 dimension).
	 * 
	 * @param key
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public boolean containsKey2(Object key) throws ClassCastException,
			NullPointerException;;

	/**
	 * Returns a <code>Set</code> view of the keys contained in this map.
	 * 
	 * @return
	 */
	public Set<K1> key1Set();

	/**
	 * Returns a <code>Set</code> view of the keys contained in this map.
	 * 
	 * @return
	 */
	public Set<K2> key2Set();

	/**
	 * Returns <code>true</code> if this map maps one or more couple to the
	 * specified value.
	 * 
	 * @param value
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @return
	 */
	public boolean containsValue(Object value) throws ClassCastException,
			NullPointerException;

	/**
	 * Returns <code>true</code> if this DoubleMap contains no couple-value
	 * mappings.
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * 
	 * Associates the specified value with the specified couple of keys in this
	 * DoubleMap. If the map previously contained a mapping for this couple of
	 * keys, the old value is replaced by the specified value.
	 * 
	 * @param key1
	 * @param key2
	 * @param value
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public V put(K1 key1, K2 key2, V value) throws UnsupportedOperationException,
			ClassCastException, NullPointerException, IllegalArgumentException;

	/**
	 * 
	 * Associates the specified map with the specified key in this DoubleMap. If
	 * the map previously contained a mapping for this key, the old value is
	 * replaced by the specified value.
	 * 
	 * @param key
	 * @param m
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Map<K2, V> putByKey1(K1 key, Map<K2, V> m)
			throws UnsupportedOperationException, ClassCastException,
			NullPointerException, IllegalArgumentException;

	/**
	 * 
	 * Associates the specified map with the specified key in this DoubleMap. If
	 * the map previously contained a mapping for this key, the old value is
	 * replaced by the specified value.
	 * 
	 * @param key
	 * @param m
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Map<K1, V> putByKey2(K2 key, Map<K1, V> m)
			throws UnsupportedOperationException, ClassCastException,
			NullPointerException, IllegalArgumentException;

	/**
	 * Copies all ef the mappings from the specified doubleMap to this doubleMap.
	 * 
	 * @param m
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public void putAll(DoubleMap<? extends K1, ? extends K2, ? extends V> m)
			throws UnsupportedOperationException, ClassCastException,
			NullPointerException, IllegalArgumentException;

	/**
	 * Removes the mapping for a couple of key from this map if it is present.
	 * 
	 * Returns the value to which this map previously associated the couple of
	 * key, or <code>null</code> if the map contained no mapping for this couple.
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 */
	public V remove(Object key1, Object key2)
			throws UnsupportedOperationException, ClassCastException,
			NullPointerException;

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * Returns the value to which this map previously associated the key, or
	 * <code>null</code> if the map contained no mapping for this key.
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 */
	public Map<K2, V> removeByKey1(K1 key) throws UnsupportedOperationException,
			ClassCastException, NullPointerException;

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * Returns the value to which this map previously associated the key, or
	 * <code>null</code> if the map contained no mapping for this key.
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 */
	public Map<K1, V> removeByKey2(K2 key) throws UnsupportedOperationException,
			ClassCastException, NullPointerException;

	/**
	 * Returns the number of couple-value mappings in this map.
	 * 
	 * For example, if the map is 4 couple if keys, it will be size of 4.
	 * 
	 * @return
	 */
	public int size();

	/**
	 * Returns the size of one dimension.
	 * 
	 * For example, if the map is 4 couple if keys, it will be 2.
	 * 
	 * @return
	 */
	public int sizeByKey1();

	/**
	 * Returns the size of one dimension.
	 * 
	 * For example, if the map is 4 couple if keys, it will be 2.
	 * 
	 * @return
	 */
	public int sizeByKey2();

	/**
	 * Returns a <code>Collection</code> view of the values contained by this
	 * double map.
	 * 
	 * @return
	 */
	public Collection<V> values();

	/**
	 * Returns a <code>Collection</code> view of the values contained by this
	 * double map for a key.
	 * 
	 * @return
	 */
	public Collection<V> valuesByKey1(K1 key);

	/**
	 * Returns a <code>Collection</code> view of the values contained by this
	 * double map for a key.
	 * 
	 * @return
	 */
	public Collection<V> valuesByKey2(K2 key);
}
