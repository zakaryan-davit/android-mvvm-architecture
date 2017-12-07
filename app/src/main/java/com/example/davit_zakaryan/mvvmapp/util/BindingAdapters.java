package com.example.davit_zakaryan.mvvmapp.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

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

	@BindingAdapter({"level_background"})
	public static void setBackgroundByLevel(TextView textView, int level) {
		GradientDrawable shapeDrawable = (GradientDrawable) textView.getBackground();
		shapeDrawable.setColor(GaugeView.getColorByLevel(level, 10));
	}
}
