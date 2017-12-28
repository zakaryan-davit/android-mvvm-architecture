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

	static ItemElementViewHolder create(LayoutInflater layoutInflater, ViewGroup parent) {
		ItemElementBinding binding = ItemElementBinding.inflate(layoutInflater, parent, false);
		return new ItemElementViewHolder(binding);
	}

	private ItemElementBinding itemElementBinding;
	private OnElementSelectionChangeListener changeListener;

	private ItemElementViewHolder(ItemElementBinding elementBinding) {
		super(elementBinding.getRoot());
		elementBinding.getRoot().setOnClickListener(this);
		this.itemElementBinding = elementBinding;
	}

	public void bindTo(@NonNull Element element) {
		itemElementBinding.setViewModel(element);
		itemElementBinding.executePendingBindings();
	}

	@Override
	public void onClick(View v) {

		int position = getAdapterPosition();
		if (position != RecyclerView.NO_POSITION) {
			//changeListener.onSelectionChanged(getAdapterPosition());
		}

		// TODO add fragment transaction
		//  Bundle bundle = new Bundle();
		//  bundle.putInt(Constants.EXTRA_CHOSEN_TYPE, chosenType);
		//	ElementDetailsFragment fragment = new ElementDetailsFragment();
		//	fragment.setArguments(bundle);
	}
}

