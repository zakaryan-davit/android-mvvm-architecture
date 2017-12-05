package com.example.davit_zakaryan.mvvmapp.util;

import android.databinding.BindingAdapter;

import com.example.custom_view.GaugeView;
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
}
