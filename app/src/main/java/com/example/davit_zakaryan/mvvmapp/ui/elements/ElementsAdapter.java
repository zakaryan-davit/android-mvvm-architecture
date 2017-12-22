package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.databinding.ItemElementBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.OnElementSelectionChangeListener;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davit_Zakaryan on 12/5/2017.
 */

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ViewHolder> {

	// ===========================================================
	// Fields
	// ===========================================================

	private OnElementSelectionChangeListener changeListener;
	private List<Element> elements = new ArrayList<>();
	private int chosenType;

	public ElementsAdapter(List<Element> elements, int chosenType) {
		this.elements = elements;
		this.chosenType = chosenType;
	}

	public ElementsAdapter(List<Element> elements) {
		this.elements = elements;
	}

	public void setChosenType(int chosenType) {
		this.chosenType = chosenType;
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
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		ItemElementBinding elementBinding = DataBindingUtil
				.inflate(layoutInflater, R.layout.item_element, parent, false);
		return new ViewHolder(elementBinding);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Element element = elements.get(position);
		holder.bindTo(element);
	}

	@Override
	public int getItemCount() {
		return elements != null ? elements.size() : 0;
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private ItemElementBinding itemElementBinding;

		ViewHolder(ItemElementBinding elementBinding) {
			super(elementBinding.getRoot());
			elementBinding.getRoot().setOnClickListener(this);
			this.itemElementBinding = elementBinding;
		}

		void bindTo(@NonNull Element element) {
			itemElementBinding.setElement(element);
			itemElementBinding.executePendingBindings(); //
		}

		@Override
		public void onClick(View v) {

			//changeListener.onSelectionChanged(getAdapterPosition());

//			Bundle bundle = new Bundle();
//			bundle.putInt(Constants.EXTRA_CHOSEN_TYPE, chosenType);
//			ElementDetailsFragment fragment = new ElementDetailsFragment();
//			fragment.setArguments(bundle);
			//TODO add fragment transaction
		}
	}
}
