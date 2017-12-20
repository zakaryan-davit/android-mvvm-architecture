package com.example.davit_zakaryan.mvvmapp.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.custom_view.GaugeView;
import com.example.davit_zakaryan.mvvmapp.ui.base.RecyclerViewViewModel;
import com.facebook.drawee.view.SimpleDraweeView;

public class BindingAdapters {

	@BindingAdapter({"imageUrl"})
	public static void loadImage(SimpleDraweeView view, String imageUrl) {
		view.setImageURI(imageUrl);
	}

	@BindingAdapter({"gv_level"})
	public static void setLevel(GaugeView gaugeView, int level) {
		gaugeView.setLevel(level);
	}

	@BindingAdapter({"level_background"})
	public static void setBackgroundByLevel(TextView textView, int level) {
		GradientDrawable shapeDrawable = (GradientDrawable) textView.getBackground();
		shapeDrawable.setColor(GaugeView.getColorByLevel(level - 1, 10)); //Level -1 because colors begin from 0
	}

	@BindingAdapter({"setup_recyclerView"})
	public static void setupRecyclerView(RecyclerView recyclerView, RecyclerViewViewModel recyclerViewViewModel) {
		recyclerView.setLayoutManager(recyclerViewViewModel.getLayoutManager());
		recyclerView.setAdapter(recyclerViewViewModel.getAdapter());
	}

}
