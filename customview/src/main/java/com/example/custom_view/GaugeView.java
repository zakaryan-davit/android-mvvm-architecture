package com.example.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Davit_Zakaryan on 12/1/2017.
 */

public class GaugeView extends View {

	// ===========================================================
	// Constants
	// ===========================================================

	protected static final String LOG_TAG = GaugeView.class.getSimpleName();
	private final int SCALE_START_ANGLE = 150;
	private final int SCALE_WHOLE_ANGLE = 240;
	private final float SCALE_DIVIDER_ANGLE = 2;
	private final int SCALE_PADDING = DisplayUtils.convertDpToPixel(8);
	private final float THICKNESS = 0.4f; //percent
	private final int ARROW_TRIANGLE_BASE = DisplayUtils.convertDpToPixel(3);
	private final int ARROW_RADIUS = DisplayUtils.convertDpToPixel(6);


	// ===========================================================
	// Fields
	// ===========================================================

	// Attribute fields
	private int level;
	private int count;

	// Calculated fields
	private int desiredWidth = DisplayUtils.convertDpToPixel(100);
	private int desiredHeight = DisplayUtils.convertDpToPixel(100);
	private int centerX;
	private int centerY;
	private int scaleOuterRadius;
	private float scaleInnerRadius;
	private float sweepAngle;
	private int arrowLength;

	// Drawing tools
	private Paint paint;
	private RectF outerRectF;
	private RectF innerRectF;
	private Path path;
	private float[] hsv = new float[3];
	private Point p1, p2, p3;
	private Bitmap cachedBitmap;
	private boolean initialDrawingIsPerformed;


	// ===========================================================
	// Constructors
	// ===========================================================

	public GaugeView(Context context) {
		super(context);
		setupPaint();
	}

	public GaugeView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		//get the attributes specified in attrs.xml using the name we included
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.GaugeView, 0, 0);

		try {
			count = typedArray.getColor(R.styleable.GaugeView_gv_scalesCount, 10);
			level = typedArray.getInteger(R.styleable.GaugeView_gv_level, 1);
		} finally {
			typedArray.recycle();
		}
		setupPaint();
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if (level <= count) {
			this.level = level;
			invalidate();
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		invalidate();
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

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

		centerX = width / 2;
		centerY = height / 2;
		scaleOuterRadius = Math.min(width - SCALE_PADDING, height - SCALE_PADDING) / 2;
		scaleInnerRadius = (scaleOuterRadius * THICKNESS);
		arrowLength = (int) Math.abs((scaleOuterRadius + scaleInnerRadius) / 1.5); //arrow length is in the middle. ??TODO why it is negative
		outerRectF.set(centerX - scaleOuterRadius, centerY - scaleOuterRadius, centerX + scaleOuterRadius, centerY + scaleOuterRadius);
		innerRectF.set(centerX - scaleInnerRadius, centerY - scaleInnerRadius, centerX + scaleInnerRadius, centerY + scaleInnerRadius);

		// Calling this method determines the measured width and height
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		drawWithCashingBitmapSolution(canvas);
	}

	@Nullable
	@Override
	protected Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		SavedState savedState = new SavedState(superState);
		savedState.levelState = this.level;
		return savedState;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (!(state instanceof SavedState)) {
			super.onRestoreInstanceState(state);
			return;
		}

		SavedState savedState = (SavedState) state;
		super.onRestoreInstanceState(savedState.getSuperState());

		this.level = savedState.levelState;
	}


	// ===========================================================
	// Methods
	// ===========================================================

	private void setupPaint() {
		paint = new Paint();
		innerRectF = new RectF();
		outerRectF = new RectF();
		path = new Path();
		p1 = new Point();
		p2 = new Point();
		p3 = new Point();
		sweepAngle = (SCALE_WHOLE_ANGLE - SCALE_DIVIDER_ANGLE * (count - 1)) / count;
		Log.d(LOG_TAG, "Sweep Angle of each = " + sweepAngle);
	}

	private void drawWithCashingBitmapSolution(Canvas canvas) {
		if (!initialDrawingIsPerformed) {

			this.cachedBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
					Bitmap.Config.ARGB_8888);
			Canvas cacheCanvas = new Canvas(this.cachedBitmap);

			// doInitialDrawing
			drawScale(cacheCanvas);
			drawArrow(cacheCanvas);

			canvas.drawBitmap(this.cachedBitmap, 0, 0, new Paint());
			initialDrawingIsPerformed = true;
		} else {
			canvas.drawBitmap(this.cachedBitmap, 0, 0, new Paint());

			// doPartialRedraws;
			drawArrow(canvas);
		}
	}

	private void drawScale(Canvas canvas) {
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		for (float i = 0; i < count; i++) {
			paint.setColor(getColorByLevel(i, count, hsv));
			float arcOffset = SCALE_START_ANGLE + i * (sweepAngle + SCALE_DIVIDER_ANGLE);
			path.reset();
			path.arcTo(outerRectF, arcOffset, sweepAngle);
			path.arcTo(innerRectF, arcOffset + sweepAngle, -sweepAngle);
			path.close();
			canvas.drawPath(path, paint);
		}
	}

	private void drawArrow(Canvas canvas) {
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL);

		paint.setStrokeWidth(4);

		int arrowDegree = 180 - SCALE_START_ANGLE;

		int x1 = (int) (arrowLength * Math.cos(Math.toRadians(arrowDegree)));
		int y1 = (int) (arrowLength * Math.sin(Math.toRadians(arrowDegree)));

		int x2 = (int) (ARROW_TRIANGLE_BASE * Math.cos(Math.toRadians(90 - arrowDegree)));
		int y2 = (int) (ARROW_TRIANGLE_BASE * Math.sin(Math.toRadians(90 - arrowDegree)));

		int x3 = (int) (ARROW_TRIANGLE_BASE * Math.cos(Math.toRadians(90 - arrowDegree)));
		int y3 = (int) (ARROW_TRIANGLE_BASE * Math.sin(Math.toRadians(90 - arrowDegree)));

		p1.set(centerX - x1, centerY + y1);
		p2.set(centerX - x2, centerY - y2);
		p3.set(centerX + x3, centerY + y3);

		path.reset();
		path.moveTo(p1.x, p1.y);
		path.lineTo(p2.x, p2.y);
		path.lineTo(p3.x, p3.y);
		path.close();

		canvas.rotate((level - 1) * (sweepAngle + SCALE_DIVIDER_ANGLE) + sweepAngle / 2, centerX, centerY);
		canvas.drawPath(path, paint);

		paint.setColor(Color.BLACK);
		canvas.drawCircle(centerX, centerY, ARROW_RADIUS, paint);
	}

	@ColorInt
	public static int getColorByLevel(int level, int count) {
		return getColorByLevel(level, count, null);
	}

	@ColorInt
	public static int getColorByLevel(float level, int count, @Size(3) float hsv[]) {
		if (hsv == null) {
			hsv = new float[3];
		}
		hsv[0] = (324f / count) * (count - 1 - level);
		hsv[1] = 1;
		hsv[2] = 1;
		return Color.HSVToColor(hsv);
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static class SavedState extends BaseSavedState {
		int levelState;

		SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			this.levelState = in.readInt();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(this.levelState);
		}

		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
			@Override
			public SavedState createFromParcel(Parcel source) {
				return new SavedState(source);
			}

			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}
}
