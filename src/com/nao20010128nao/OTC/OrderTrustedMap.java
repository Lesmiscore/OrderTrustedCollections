package com.nao20010128nao.OTC;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OrderTrustedMap<K, V> implements Map<K, V> {
	Node nodes = new Node();
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
		int count = 0;
		while ((curNode = curNode.next) != null) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		// TODO 自動生成されたメソッド・スタブ
		return nodes.next == null;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO 自動生成されたメソッド・スタブ
		if (isEmpty())
			return false;
		Node curNode = nodes;
		while ((curNode = curNode.next) != null) {
			if (key == null) {
				if (curNode.k == null) {
					return true;
				}
			} else {
				if (curNode.k == null) {
					continue;
				}
				if (curNode.k == key) {
					return true;
				}
				if (curNode.k.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO 自動生成されたメソッド・スタブ
		if (isEmpty())
			return false;
		Node curNode = nodes;
		while ((curNode = curNode.next) != null) {
			if (value == null) {
				if (curNode.v == null) {
					return true;
				}
			} else {
				if (curNode.v == null) {
					continue;
				}
				if (curNode.v == value) {
					return true;
				}
				if (curNode.v.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO 自動生成されたメソッド・スタブ
		if (isEmpty())
			return null;
		Node curNode = nodes;
		while ((curNode = curNode.next) != null) {
			if (key == null) {
				if (curNode.k == null) {
					return curNode.v;
				}
			} else {
				if (curNode.k == null) {
					continue;
				}
				if (curNode.k == key) {
					return curNode.v;
				}
				if (curNode.k.equals(key)) {
					return curNode.v;
				}
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO 自動生成されたメソッド・スタブ
		if (containsKey(key))
			return null;
		Node curNode = nodes;
		while ((curNode = curNode.next) != null) {
			if (value == null) {
				if (curNode.k == null) {
					V val = curNode.v;
					curNode.v = value;
					return val;
				}
			} else {
				if (curNode.k == null) {
					continue;
				}
				if (curNode.k == key) {
					V val = curNode.v;
					curNode.v = value;
					return val;
				}
				if (curNode.k.equals(key)) {
					V val = curNode.v;
					curNode.v = value;
					return val;
				}
			}
		}
		Node newNode = new Node();
		newNode.k = key;
		newNode.v = value;
		findLast().next = newNode;
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO 自動生成されたメソッド・スタブ
		if (!containsKey(key))
			return null;
		Node curNode = nodes;
		while ((curNode = curNode.next) != null) {
			if (curNode.next == null) {
				return null;
			}
			if (key == null) {
				if (curNode.next.k == null) {
					curModified++;
					V val = curNode.next.v;
					curNode.next = curNode.next.next;
					return val;
				}
			} else {
				if (curNode.next.k == null) {
					continue;
				}
				if (curNode.next.k == key) {
					curModified++;
					V val = curNode.next.v;
					curNode.next = curNode.next.next;
					return val;
				}
				if (curNode.next.k.equals(key)) {
					curModified++;
					V val = curNode.next.v;
					curNode.next = curNode.next.next;
					return val;
				}
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO 自動生成されたメソッド・スタブ
		for (Map.Entry<? extends K, ? extends V> kv : m.entrySet())
			put(kv.getKey(), kv.getValue());
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ
		nodes.next = null;
	}

	@Override
	public Set<K> keySet() {
		// TODO 自動生成されたメソッド・スタブ
		return new KeySet();
	}

	@Override
	public Collection<V> values() {
		// TODO 自動生成されたメソッド・スタブ
		return new Values();
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO 自動生成されたメソッド・スタブ
		return new EntrySet();
	}

	private Node findLast() {
		Node curNode = nodes;
		while (null != curNode.next) {
			curNode = curNode.next;
		}
		return curNode;
	}

	class Node {
		Node next;
		K k;
		V v;

		class ImmutableEntry implements Map.Entry<K, V> {
			K lK = k;
			V lV = v;

			@Override
			public K getKey() {
				// TODO 自動生成されたメソッド・スタブ
				return lK;
			}

			@Override
			public V getValue() {
				// TODO 自動生成されたメソッド・スタブ
				return lV;
			}

			@Override
			public V setValue(V value) {
				// TODO 自動生成されたメソッド・スタブ
				throw new UnsupportedOperationException();
			}
		}
	}

	abstract class Check {
		protected void check() {
			if (exceptedMod() != curModified)
				throw new ConcurrentModificationException();
		}

		protected abstract int exceptedMod();
	}

	final class KeySet extends Check implements Set<K> {
		int exceptedMod = curModified;

		@Override
		public int size() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.size();
		}

		@Override
		public boolean isEmpty() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.containsKey(o);
		}

		@Override
		public Iterator<K> iterator() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return new KIterator();
		}

		@Override
		public Object[] toArray() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			Object[] o = new Object[size()];
			Node curNode = nodes;
			int offset = 0;
			while ((curNode = curNode.next) != null) {
				o[offset++] = curNode.k;
			}
			return o;
		}

		@Override
		public <T> T[] toArray(T[] o) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			Node curNode = nodes;
			int offset = 0;
			while ((curNode = curNode.next) != null) {
				o[offset++] = (T) curNode.k;
			}
			return o;
		}

		@Override
		public boolean add(K e) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			for (Object k : c)
				if (!contains(k))
					return false;
			return true;
		}

		@Override
		public boolean addAll(Collection<? extends K> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		protected int exceptedMod() {
			// TODO 自動生成されたメソッド・スタブ
			return exceptedMod;
		}

		class KIterator extends Check implements Iterator<K> {
			Node curNode = nodes;

			@Override
			protected int exceptedMod() {
				// TODO 自動生成されたメソッド・スタブ
				return exceptedMod;
			}

			@Override
			public boolean hasNext() {
				// TODO 自動生成されたメソッド・スタブ
				check();
				return curNode.next != null;
			}

			@Override
			public K next() {
				// TODO 自動生成されたメソッド・スタブ
				check();
				return (curNode = curNode.next).k;
			}

			@Override
			public void remove() {
				// TODO 自動生成されたメソッド・スタブ
				throw new UnsupportedOperationException();
			}
		}
	}

	final class Values extends Check implements Collection<V> {
		int exceptedMod = curModified;

		@Override
		public int size() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.size();
		}

		@Override
		public boolean isEmpty() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.containsValue(o);
		}

		@Override
		public Iterator<V> iterator() {
			// TODO 自動生成されたメソッド・スタブ
			return new VIterator();
		}

		@Override
		public Object[] toArray() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			Object[] o = new Object[size()];
			Node curNode = nodes;
			int offset = 0;
			while ((curNode = curNode.next) != null) {
				o[offset++] = curNode.v;
			}
			return o;
		}

		@Override
		public <T> T[] toArray(T[] o) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			Node curNode = nodes;
			int offset = 0;
			while ((curNode = curNode.next) != null) {
				o[offset++] = (T) curNode.v;
			}
			return o;
		}

		@Override
		public boolean add(V e) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			for (Object k : c)
				if (!contains(k))
					return false;
			return true;
		}

		@Override
		public boolean addAll(Collection<? extends V> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		protected int exceptedMod() {
			// TODO 自動生成されたメソッド・スタブ
			return exceptedMod;
		}

		class VIterator extends Check implements Iterator<V> {
			Node curNode = nodes;

			@Override
			protected int exceptedMod() {
				// TODO 自動生成されたメソッド・スタブ
				return exceptedMod;
			}

			@Override
			public boolean hasNext() {
				// TODO 自動生成されたメソッド・スタブ
				check();
				return curNode.next != null;
			}

			@Override
			public V next() {
				// TODO 自動生成されたメソッド・スタブ
				check();
				return (curNode = curNode.next).v;
			}

			@Override
			public void remove() {
				// TODO 自動生成されたメソッド・スタブ
				throw new UnsupportedOperationException();
			}
		}
	}

	final class EntrySet extends Check implements Set<Map.Entry<K, V>> {
		int exceptedMod = curModified;

		@Override
		public int size() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.size();
		}

		@Override
		public boolean isEmpty() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return OrderTrustedMap.this.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			if (o instanceof Map.Entry) {
				if (containsKey(((Map.Entry) o).getKey())) {
					if (((Map.Entry) o).getValue() == null) {
						if (get(((Map.Entry) o).getKey()) == null) {
							return true;
						}
					} else {
						if (get(((Map.Entry) o).getKey()) == null) {
							return false;
						}
						if (get(((Map.Entry) o).getKey()) == ((Map.Entry) o).getValue()) {
							return true;
						}
						if (get(((Map.Entry) o).getKey()).equals(((Map.Entry) o).getValue())) {
							return true;
						}
					}
				} else {
					return false;
				}
			}
			return false;
		}

		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			return new KVIterator();
		}

		@Override
		public Object[] toArray() {
			// TODO 自動生成されたメソッド・スタブ
			check();
			Object[] o = new Object[size()];
			Node curNode = nodes;
			int offset = 0;
			while ((curNode = curNode.next) != null) {
				o[offset++] = curNode.k;
			}
			return o;
		}

		@Override
		public <T> T[] toArray(T[] o) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			Node curNode = nodes;
			int offset = 0;
			while ((curNode = curNode.next) != null) {
				o[offset++] = (T) curNode.k;
			}
			return o;
		}

		@Override
		public boolean add(Map.Entry<K, V> e) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			check();
			for (Object k : c)
				if (!contains(k))
					return false;
			return true;
		}

		@Override
		public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			// TODO 自動生成されたメソッド・スタブ
			throw new UnsupportedOperationException();
		}

		@Override
		protected int exceptedMod() {
			// TODO 自動生成されたメソッド・スタブ
			return exceptedMod;
		}

		class KVIterator extends Check implements Iterator<Map.Entry<K, V>> {
			Node curNode = nodes;

			@Override
			protected int exceptedMod() {
				// TODO 自動生成されたメソッド・スタブ
				return exceptedMod;
			}

			@Override
			public boolean hasNext() {
				// TODO 自動生成されたメソッド・スタブ
				check();
				return curNode.next != null;
			}

			@Override
			public Map.Entry<K, V> next() {
				// TODO 自動生成されたメソッド・スタブ
				check();
				return (curNode = curNode.next).new ImmutableEntry();
			}

			@Override
			public void remove() {
				// TODO 自動生成されたメソッド・スタブ
				throw new UnsupportedOperationException();
			}
		}
	}
}
