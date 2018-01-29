package com.example.davit_zakaryan.mvvmapp.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.ui.elements.ItemElementViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter<ITEM_T> extends RecyclerView.Adapter<ItemElementViewHolder> {

	protected List<ITEM_T> items;

	public BaseAdapter() {
		items = new ArrayList<>();
	}

	@Override
	public ItemElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(ItemElementViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return items != null ? items.size() : 0;
	}
}
