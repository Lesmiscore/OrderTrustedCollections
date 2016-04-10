package com.nao20010128nao.OTC;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class OrderTrustedMap<K, V> implements Map<K, V> {
	Node nodes;
	int curModified = 0;

	public OrderTrustedMap() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int size() {
		// TODO 自動生成されたメソッド・スタブ
		if (isEmpty())
			return 0;
		Node curNode = nodes;
		int count = 1;
		while ((curNode = curNode.next) != null) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		// TODO 自動生成されたメソッド・スタブ
		return nodes == null;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO 自動生成されたメソッド・スタブ
		if (isEmpty())
			return false;

	}

	@Override
	public boolean containsValue(Object value) {
		// TODO 自動生成されたメソッド・スタブ
		if (isEmpty())
			return false;
	}

	@Override
	public V get(Object key) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Set<K> keySet() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	class Node {
		Node next;
		K k;
		V v;
	}
}
