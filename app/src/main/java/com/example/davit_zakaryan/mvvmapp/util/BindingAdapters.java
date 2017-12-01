package com.example.davit_zakaryan.mvvmapp.util;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.example.customview.GaugeView;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by Davit_Zakaryan on 11/23/2017.
 */

public class BindingAdapters {


	@BindingAdapter({"bind:imageUrl"})
	public static void loadImage(ImageView imageView, String url) {
//		Picasso.with(imageView.getContext())
//				.load(url)
//				.into(imageView);
	}

	//	@BindingAdapter({"android:src", "app:error"})
	public static void loadImage(ImageView view, String url, Drawable error) {
//		Picasso.with(view.getContext())
//				.load(url)
//				.error(error)
//				.into(view);
	}

	@BindingAdapter({"app:imageUrl"})
	public static void loadImage(SimpleDraweeView view, String imageUrl) {
		Uri uri = Uri.parse(imageUrl);
		System.out.println(imageUrl);
		//SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
		//draweeView.setImageURI(uri);

		view.setImageURI(imageUrl);
	}

	@BindingAdapter({"app:gv_level"})
	public static void setLevel(GaugeView gaugeView, ObservableInt level) {
		gaugeView.setLevel(level.get());
	}
}
