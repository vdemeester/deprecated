package org.shortbrain.util.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class HashMapTable<V, K> implements
		Table<HashMap<K, V>, K, V> {

	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;

	public String caption;
	public String description;
	public int type;
	public HashMap<K, V> map = new HashMap<K, V>();

	@Override
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public HashMap<K, V> getMap() {
		return map;
	}

	public void setMap(HashMap<K, V> map) {
		this.map = map;
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public V get(Object key) {
		return map.get(key);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public V put(K key, V value) {
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		map.putAll(m);
	}

	@Override
	public V remove(Object key) {
		return map.remove(key);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<V> values() {
		return map.values();
	}

}
