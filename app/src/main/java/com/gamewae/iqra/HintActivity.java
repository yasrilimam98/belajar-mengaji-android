package com.gamewae.iqra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gamewae.iqra.adapter.HintFragmentAdapter;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.CirclePageIndicator;
import com.gamewae.iqra.widget.PageIndicator;

public class HintActivity extends FragmentActivity implements OnClickListener {
	private HintFragmentAdapter adapter;
	private  PageIndicator mIndicator;
	private ViewPager mPager;
	private Button btnNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Shared.initialize(getBaseContext());
		setContentView(R.layout.activity_hint);
		
		mPager = (ViewPager)findViewById(R.id.viewPager);
		mIndicator = (CirclePageIndicator)findViewById(R.id.indicators);
		
		adapter = new HintFragmentAdapter(getSupportFragmentManager());
		mPager.setAdapter(adapter);
		
		mIndicator.setViewPager(mPager);
		
		btnNext = (Button)findViewById(R.id.btnNext);
		btnNext.setTypeface(Shared.appfont);
		
		btnNext.setOnClickListener(this);
		mPager.setPageTransformer(false, new FadePageTransformer());
		mPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if(arg0 == adapter.getCount() - 1)
					btnNext.setText(R.string.start);
				else
					btnNext.setText(R.string.next2);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mPager.getCurrentItem() == adapter.getCount() - 1 )
		{
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		else
		{
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
		}
	}
	
	private static class FadePageTransformer implements ViewPager.PageTransformer {
	    public void transformPage(View view, float position) {
	    	 view.setTranslationX(view.getWidth() * -position);

	            if(position <= -1.0F || position >= 1.0F) {
	                view.setAlpha(0.0F);
	            } else if( position == 0.0F ) {
	                view.setAlpha(1.0F);
	            } else { 
	                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
	                view.setAlpha(1.0F - Math.abs(position));
	            }
	    }
	}
}
