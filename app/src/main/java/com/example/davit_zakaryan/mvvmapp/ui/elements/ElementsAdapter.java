package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.ui.base.OnElementSelectionChangeListener;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Davit_Zakaryan on 12/5/2017.
 */

public class ElementsAdapter extends RecyclerView.Adapter<ItemElementViewHolder> {

	// ===========================================================
	// Fields
	// ===========================================================

	private OnElementSelectionChangeListener changeListener;
	private List<Element> elements = new ArrayList<>();


	@Inject
	ElementsAdapter() {
	}

	public void setChangeListener(OnElementSelectionChangeListener changeListener) {
		this.changeListener = changeListener;
	}

	public void setElements(List<ItemModel> elements) {
		this.elements = new ArrayList<>();
		for (ItemModel item : elements) {
			// TODO need to create more clean solution
			this.elements.add(ModelDaoConverter.convertItem(item));
		}
		notifyDataSetChanged();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public ItemElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		return ItemElementViewHolder.create(layoutInflater, parent);
	}

	@Override
	public void onBindViewHolder(ItemElementViewHolder holder, int position) {
		Element element = elements.get(position);
		holder.bindTo(element);
	}

	@Override
	public int getItemCount() {
		return elements != null ? elements.size() : 0;
	}
}
