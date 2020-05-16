package com.gamewae.iqra.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gamewae.iqra.R;
import com.gamewae.iqra.entity.section;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.AutoResizeTextView;

public class SectionPagerAdapter extends PagerAdapter {
 
    private List<section> dtlist ;
    private Activity context;
    private View layout;
    
    public SectionPagerAdapter(Activity context) {
        this.context = context;
        dtlist = new ArrayList<section>();
    }
    
    public void set(List<section> list) {
        this.dtlist = list;
        notifyDataSetChanged();
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dtlist.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return dtlist.get(position).getPageName();
	}
	
	@Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View) arg1);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     layout = inflater.inflate(R.layout.section_view_item, null);
	     AutoResizeTextView text = (AutoResizeTextView) layout.findViewById(R.id.textView1);	     
	     text.setSingleLine(true);
	    // text.setTypeface(Shared.mequran);
	     
	     text.setText(dtlist.get(position).getText());
	     ((ViewPager) container).addView(layout, 0);
	     
		return layout;
	}

}