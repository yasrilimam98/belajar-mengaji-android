package com.gamewae.iqra.adapter;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public abstract class ArrayAdapter<T> extends BaseAdapter {

	private ArrayList<T> mItems;

	public ArrayAdapter() {
		this(null);
	}

	public ArrayAdapter(List<T> items) {
	    mItems = new ArrayList<T>();
		if (items != null) {
			mItems.addAll(items);
		}
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public T getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void add(T item) {
		mItems.add(item);
		notifyDataSetChanged();
	}

	public void add(int position, T item) {
		mItems.add(position, item);
		notifyDataSetChanged();
	}


	public void addAll(Collection<? extends T> items) {
		mItems.addAll(items);
		notifyDataSetChanged();
	}

	public void addAll(T... items) {
        Collections.addAll(mItems, items);
		notifyDataSetChanged();
	}

	public void addAll(int position, Collection<? extends T> items) {
		mItems.addAll(position, items);
		notifyDataSetChanged();
	}


	public void addAll(int position, T... items) {
		for (int i = position; i < (items.length + position); i++) {
			mItems.add(i, items[i]);
		}
		notifyDataSetChanged();
	}

	public void clear() {
		mItems.clear();
		notifyDataSetChanged();
	}

	public void set(int position, T item) {
		mItems.set(position, item);
		notifyDataSetChanged();
	}

	
	public void remove(T item) {
		mItems.remove(item);
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mItems.remove(position);
		notifyDataSetChanged();
	}

	public void removePositions(Collection<Integer> positions) {
		ArrayList<Integer> positionsList = new ArrayList<Integer>(positions);
		Collections.sort(positionsList);
		Collections.reverse(positionsList);
		for (int position : positionsList) {
			mItems.remove(position);
		}
		notifyDataSetChanged();
	}

	public void removeAll(Collection<T> items) {
		mItems.removeAll(items);
		notifyDataSetChanged();
	}
	public void retainAll(Collection<T> items) {
		mItems.retainAll(items);
		notifyDataSetChanged();
	}

	public int indexOf(T item) {
		return mItems.indexOf(item);
	}

}
