package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;

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
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.item_element, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Element element = elements.get(position);
		holder.title.setText(element.name);
		holder.subtitle.setText(element.shortDesc);
		holder.level.setText(String.valueOf(element.level.get()));
	}

	@Override
	public int getItemCount() {
		return elements != null ? elements.size() : 0;
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		TextView title, subtitle, level;

		ViewHolder(View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.item_element_title_text);
			subtitle = (TextView) itemView.findViewById(R.id.item_element_subtitle_text);
			level = (TextView) itemView.findViewById(R.id.item_element_level_text);
		}
	}
}
