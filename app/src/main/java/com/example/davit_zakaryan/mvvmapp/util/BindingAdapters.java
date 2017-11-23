package com.example.davit_zakaryan.mvvmapp.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Davit_Zakaryan on 11/23/2017.
 */

public class BindingAdapters {


	@BindingAdapter({"bind:imageUrl"})
	public static void loadImage(ImageView imageView, String url) {
		Picasso.with(imageView.getContext())
				.load(url)
				.into(imageView);
	}

	//	@BindingAdapter({"android:src", "app:error"})
	public static void loadImage(ImageView view, String url, Drawable error) {
		Picasso.with(view.getContext())
				.load(url)
				.error(error)
				.into(view);
	}
}
