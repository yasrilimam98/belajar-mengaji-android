package com.gamewae.iqra.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomPager extends ViewPager {
	private boolean isCanSlide = false;
    public CustomPager(Context context) {
        super(context);
    }

    public CustomPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
    	if (this.isCanSlide) {
            return super.onInterceptTouchEvent(event);
        }

        return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
    	 if (this.isCanSlide) {
             return super.onTouchEvent(event);
         }

         return false;
    }

	public boolean isCanSlide() {
		return isCanSlide;
	}

	public void setCanSlide(boolean isCanSlide) {
		this.isCanSlide = isCanSlide;
	}
    
    
}