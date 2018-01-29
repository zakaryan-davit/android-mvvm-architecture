package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Davit_Zakaryan on 12/5/2017.
 */

public class ElementsAdapter extends RecyclerView.Adapter<ItemElementViewHolder> {

	// ===========================================================
	// Fields
	// ===========================================================

	private PublishSubject<Element> itemClickSubject = PublishSubject.create();
	private List<Element> elements = new ArrayList<>();


	@Inject
	ElementsAdapter() {
	}

	public PublishSubject<Element> getItemClickSubject() {
		return itemClickSubject;
	}

	public void setElements(List<Element> elements) {
		this.elements = new ArrayList<>();
		this.elements.addAll(elements);
		notifyDataSetChanged();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public ItemElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		return ItemElementViewHolder.newInstance(layoutInflater, parent, index -> itemClickSubject.onNext(elements.get(index)));
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
