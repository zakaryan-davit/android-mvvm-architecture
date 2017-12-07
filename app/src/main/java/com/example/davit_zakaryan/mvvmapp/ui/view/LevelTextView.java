package com.example.davit_zakaryan.mvvmapp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.davit_zakaryan.mvvmapp.R;

/**
 * Created by Davit_Zakaryan on 12/6/2017.
 */

public class LevelTextView extends AppCompatTextView {
	private int level;

	// ===========================================================
	// Constants
	// ===========================================================

	public LevelTextView(Context context) {
		super(context);
	}

	public LevelTextView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);


		//get the attributes specified in attrs.xml using the name we included
		TypedArray typedArray = context.getTheme()
				.obtainStyledAttributes(attrs, R.styleable.LevelTextView, 0, 0);

		try {
			level = typedArray.getInteger(R.styleable.LevelTextView_ltv_level, 1);
			//level = typedArray.getInteger(R.styleable., 1);
		} finally {
			typedArray.recycle();
		}
	}

	public LevelTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
