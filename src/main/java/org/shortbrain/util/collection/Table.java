package org.shortbrain.util.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author vincent
 * 
 * @param <T>
 */
public interface Table<T extends Map<? extends K, ? extends V>, K, V> {

	/**
	 * 
	 * @return
	 */
	public String getCaption();

	/**
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * 
	 * @return
	 */
	public T getMap();

	/**
	 * 
	 * @return
	 */
	public int getType();

	/**
	 * @see java.util.Map.containsKey(Object key)
	 */
	public boolean containsKey(Object key);

	/**
	 * @see java.util.Map.containsValue(Object value);
	 */
	public boolean containsValue(Object value);

	/**
	 * @see java.util.Map.entrySet();
	 */
	public Set<Entry<K, V>> entrySet();

	/**
	 * @see java.util.Map.get(Object key);
	 */
	public V get(Object key);

	/**
	 * @see java.util.Map.isEmpty();
	 */
	public boolean isEmpty();

	/**
	 * @see java.util.Map.keySet();
	 */
	public Set<K> keySet();

	/**
	 * @see java.util.Map.put(K key, V value);
	 */
	public V put(K key, V value);

	/**
	 * @see java.util.Map.putAll(Map<? extends K, ? extends V> m);
	 */
	public void putAll(Map<? extends K, ? extends V> m);

	/**
	 * @see java.util.Map.remove(Object key);
	 */
	public V remove(Object key);

	/**
	 * @see java.util.Map.size();
	 */
	public int size();

	/**
	 * @see java.util.Map.values()
	 */
	public Collection<V> values();
}
