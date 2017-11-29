package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Davit_Zakaryan on 11/29/2017.
 */

public class LovelyView extends View {

	private Context context;
	private AttributeSet attrs;
	//	private int styleAttr;
	@ColorInt
	private int color;
	private int level;

	private Paint paint;
	private Rect rect;


	private int desiredWidth;
	private int desiredHeight;


	public LovelyView(Context context) {
		super(context);
		this.context = context;
		//setupPaint();
	}

	public LovelyView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.attrs = attrs;

		//get the attributes specified in attrs.xml using the name we included
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.LovelyView, 0, 0);

		try {
			color = typedArray.getColor(R.styleable.LovelyView_color, Color.CYAN);
			level = typedArray.getInteger(R.styleable.LovelyView_level, 2);
		} finally {
			typedArray.recycle();
		}
		setupPaint();
	}

	private void setupPaint() {
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(color);
		paint.setTextSize(30);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//canvas.drawColor(color);
		canvas.drawText("Level " + level, 20, 20, paint);
		paint.setColor(Color.BLACK);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			canvas.drawOval(0, 0, 25, 50, paint);
		}

		//canvas.drawRect(0, 0, shapeWidth, shapeHeight, paintShape);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec); //TODO check if needed
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			width = Math.min(desiredWidth, widthSize);
		} else {
			width = desiredWidth;
		}


		int height;
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			height = Math.min(desiredHeight, heightSize);
		} else {
			height = desiredHeight;
		}


		// Calling this method determines the measured width and height
		setMeasuredDimension(width, height);
	}
}
