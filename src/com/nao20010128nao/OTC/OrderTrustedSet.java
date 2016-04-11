package com.nao20010128nao.OTC;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class OrderTrustedSet<E> extends AbstractSet<E> implements Set<E> {
	static final Object PRESENT = new Object();
	OrderTrustedMap<E, Object> base = new OrderTrustedMap<E, Object>();

	public OrderTrustedSet() {

	}

	public OrderTrustedSet(Collection<E> values) {
		addAll(values);
	}

	@Override
	public boolean add(E e) {
		// TODO 自動生成されたメソッド・スタブ
		return base.put(e, PRESENT) == null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return base.remove(o) == null;
	}

	@Override
	public boolean isEmpty() {
		return base.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return base.containsKey(o);
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ
		base.clear();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO 自動生成されたメソッド・スタブ
		return base.keySet().iterator();
	}

	@Override
	public int size() {
		// TODO 自動生成されたメソッド・スタブ
		return base.size();
	}
}
