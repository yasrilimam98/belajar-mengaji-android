package com.gamewae.iqra.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

public class CustomImageButton extends ImageButton {

	public CustomImageButton(Context context) {
	    super(context);
	}
	
	public CustomImageButton(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    int maskedAction = event.getActionMasked();
	    if (maskedAction == MotionEvent.ACTION_DOWN)
	        setColorFilter(Color.argb(150, 155, 155, 155), PorterDuff.Mode.DST_IN);
	    else if (maskedAction == MotionEvent.ACTION_UP)
	        setColorFilter(null);
	    return super.onTouchEvent(event);
	}
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		// TODO Auto-generated method stub
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if(!gainFocus)
			 setColorFilter(null);
	}
}

