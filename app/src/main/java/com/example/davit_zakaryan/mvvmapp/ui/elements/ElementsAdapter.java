package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.databinding.ItemElementBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davit_Zakaryan on 12/5/2017.
 */

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ViewHolder> {

	// ===========================================================
	// Fields
	// ===========================================================

	private List<Element> elements = new ArrayList<>();


	public ElementsAdapter(List<Element> elements) {
		this.elements = elements;
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		ItemElementBinding elementBinding = DataBindingUtil
				.inflate(layoutInflater, R.layout.item_element, parent, false);
		return new ViewHolder(elementBinding);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Element element = elements.get(position);
		holder.bind(element);
	}

	@Override
	public int getItemCount() {
		return elements != null ? elements.size() : 0;
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		private ItemElementBinding itemElementBinding;

		ViewHolder(ItemElementBinding elementBinding) {
			super(elementBinding.getRoot());
			this.itemElementBinding = elementBinding;
		}

		void bind(@NonNull Element element) {
			itemElementBinding.setElement(element);
			itemElementBinding.executePendingBindings();
		}
	}
}
