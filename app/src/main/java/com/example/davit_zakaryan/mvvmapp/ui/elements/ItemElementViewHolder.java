package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.databinding.ItemElementBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.OnElementSelectionChangeListener;

/**
 * Created by Davit_Zakaryan on 12/20/2017.
 */
public class ItemElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private ItemElementBinding itemElementBinding;
	private OnElementSelectionChangeListener changeListener;


	private ItemElementViewHolder(ItemElementBinding elementBinding,
	                              OnElementSelectionChangeListener changeListener) {
		super(elementBinding.getRoot());
		elementBinding.getRoot().setOnClickListener(this);
		this.itemElementBinding = elementBinding;
		this.changeListener = changeListener;
	}

	static ItemElementViewHolder newInstance(LayoutInflater layoutInflater, ViewGroup parent,
	                                         OnElementSelectionChangeListener changeListener) {
		ItemElementBinding binding = ItemElementBinding.inflate(layoutInflater, parent, false);
		return new ItemElementViewHolder(binding, changeListener);
	}

	public void bindTo(@NonNull Element element) {
		itemElementBinding.setViewModel(element);
		itemElementBinding.executePendingBindings();
	}

	@Override
	public void onClick(View v) {
		int position = getAdapterPosition();
		if (position != RecyclerView.NO_POSITION) {
			changeListener.onSelectionChanged(getAdapterPosition());
		}
	}
}

