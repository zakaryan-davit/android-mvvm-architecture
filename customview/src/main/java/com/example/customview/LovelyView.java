package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
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

	private int centerX;
	private int centerY;

	private Path path;

	private int desiredWidth = 1000;
	private int desiredHeight = 400;


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
		//setupArrow();
	}

	private void setupArrow(int centerX , int centerY) {
		Point p1 = null, p2 = null, p3 = null;
		p1 = new Point(centerX - 6, centerY);
		p2 = new Point(centerX + 6, centerY);
		p3 = new Point(p1.x, 40);
		path = new Path();
		path.moveTo(p1.x, p1.y);
		path.lineTo(p2.x, p2.y);
		path.lineTo(p3.x, p3.y);
		path.lineTo(p1.x, p1.y);
		path.close();
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
		//simpleDraw(canvas);

		paint.setColor(Color.BLACK);
		canvas.drawCircle(getMeasuredHeight()/2, getMeasuredHeight()/2, 16, paint);

		//draw line
		//paint.setStyle(Paint.Style.FILL);
		//path.setFillType(Path.FillType.EVEN_ODD);
		setupArrow(getMeasuredHeight()/2, getMeasuredHeight()/2);
		canvas.drawPath(path, paint);

		paint.setColor(Color.RED);
		canvas.drawArc(0, 0, getMeasuredWidth(), getMeasuredHeight(), 12, -28,true, paint);

		paint.setColor(Color.BLUE);
		canvas.drawArc(0, 0, getMeasuredWidth(), getMeasuredHeight(), -32, -52,true, paint);




		Paint paint = new Paint();
		final RectF rect = new RectF();
		//Example values
		int mWidth = getMeasuredHeight();
		int mRadius = 150;
		int mHeight = getMeasuredHeight();
		rect.set(mWidth/2- mRadius, mHeight/2 - mRadius, mWidth/2 + mRadius, mHeight/2 + mRadius);
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(30);
		paint.setAntiAlias(true);
		paint.setStrokeCap(Paint.Cap.BUTT);
		paint.setStyle(Paint.Style.STROKE);



		int size = 3;
		for (int i = 0; i < size; i++) {
			int R = (255 * i) / 100;
			int G = (255 * (100 - i)) / 100;
			int B = 0;
			//canvas.drawArc(rect,  -360 - i* 10, -15 - i*10, false, paint);
		}

		canvas.drawArc(rect,  15, 0, false, paint);


		// green
		setGradient(0xff84BC3D,0xff5B8829);
		drawDonut(canvas,paint, 0,60);

		//red
		setGradient(0xffe04a2f,0xffB7161B);
		drawDonut(canvas,paint, 60,60);

		// blue
		setGradient(0xff4AB6C1,0xff2182AD);
		drawDonut(canvas,paint, 120,60);

		// yellow
		setGradient(0xffFFFF00,0xfffed325);
		drawDonut(canvas,paint, 180,180);

		//paint.setColor(Color.BLUE);
		//canvas.drawArc(rect,  -35, -50, false, paint);
	}

	public void drawDonut(Canvas canvas, Paint paint, float start,float sweep){

//		myPath.reset();
//		myPath.arcTo(outterCircle, start, sweep, false);
//		myPath.arcTo(innerCircle, start+sweep, -sweep, false);
//		myPath.close();
		canvas.drawPath(path, paint);
	}

	public void setGradient(int sColor, int eColor){
		paint.setShader(new RadialGradient(5, 5, 5-5,
				new int[]{sColor,eColor},
				new float[]{.6f,.95f}, Shader.TileMode.CLAMP) );
	}

	int getTrafficlightColor(double value){
		return android.graphics.Color.HSVToColor(new float[]{(float)value*120f,1f,1f});
	}

	private void simpleDraw(Canvas canvas) {
		// ------------------------------ Draw circle
		paint.setColor(Color.RED);
		//get half of the width and height as we are working with a circle
		int viewWidthHalf = this.getMeasuredWidth() / 2;
		int viewHeightHalf = this.getMeasuredHeight() / 2;

		//get the radius as half of the width or height, whichever is smaller
		//subtract ten so that it has some space around it
		int radius = 0;
		if (viewWidthHalf > viewHeightHalf) {
			radius = viewHeightHalf - 10;
		} else {
			radius = viewWidthHalf - 10;
		}

		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, paint);
		// ------------------------------

		//canvas.drawColor(color);
		paint.setColor(Color.RED);
		canvas.drawText("Level " + level, 20, 20, paint);
		paint.setColor(Color.BLACK);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			canvas.drawOval(0, 0, 25, 50, paint);
		}

		//canvas.drawRect(0, 0, shapeWidth, shapeHeight, paintShape);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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

		centerX = this.getMeasuredWidth() / 2;
		centerY = this.getMeasuredHeight() / 2;

		// Calling this method determines the measured width and height
		setMeasuredDimension(width, height);
	}
}
