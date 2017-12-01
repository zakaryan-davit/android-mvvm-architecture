package com.example.davit_zakaryan.mvvmapp.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;

/**
 * Created by Davit_Zakaryan on 12/1/2017.
 */

public class DisplayUtils {

	// ===========================================================
	// Constructors
	// ===========================================================

	private DisplayUtils() {
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static int convertDpToPixel(int paramInt) {
		return (int) (1.0D * paramInt * Resources.getSystem().getDisplayMetrics().densityDpi / 160.0D);
	}

	public static int convertPixelToDp(float paramFloat) {
		return (int) (paramFloat / (Resources.getSystem().getDisplayMetrics().densityDpi / 160));
	}

	public static int convertPixelToSp(float paramFloat) {
		return (int) (paramFloat / Resources.getSystem().getDisplayMetrics().scaledDensity);
	}

	public static int convertSpToPixel(int paramInt) {
		return (int) (paramInt * Resources.getSystem().getDisplayMetrics().scaledDensity);
	}

}
