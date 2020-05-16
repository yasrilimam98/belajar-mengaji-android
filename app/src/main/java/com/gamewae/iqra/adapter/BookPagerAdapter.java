package com.gamewae.iqra.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gamewae.iqra.BookActivity;
import com.gamewae.iqra.PageListActivity;
import com.gamewae.iqra.R;
import com.gamewae.iqra.entity.iqro;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.widget.CustomImageButton;

public class BookPagerAdapter extends PagerAdapter {
    private List<iqro> dtlist ;
    private Activity context;
    private View layout;
    private int playmode = Constant.PLAY_MODE_CHILD;
    private int childID = -1;
    public BookPagerAdapter(Activity context) {
        this.context = context;
        dtlist = new ArrayList<iqro>();
        for (int i = 0; i < 6; i++) {
			iqro iq = new iqro();
			iq.setId(i+1);
			
			iq.setName("IQRO "+ (i+1));
			
			if((i+1) == 1)
				iq.setCover(R.drawable.cover_12);
			else if((i+1) == 2)
				iq.setCover(R.drawable.cover_13);
			else if((i+1) == 3)
				iq.setCover(R.drawable.cover_14);
			else if((i+1) == 4)
				iq.setCover(R.drawable.cover_15);
			else if((i+1) == 5)
				iq.setCover(R.drawable.cover_16);
			else if((i+1) == 6)
				iq.setCover(R.drawable.cover_17);
			
			dtlist.add(iq);
		}
    }
    
    public BookPagerAdapter(Activity context,int playmode,int childID) {
    	this.context = context;
        dtlist = new ArrayList<iqro>();
        this.playmode = playmode;
        this.childID = childID;
        
        for (int i = 0; i < 6; i++) {
			iqro iq = new iqro();
			iq.setId(i+1);
			iq.setName("IQRO "+ (i+1));
			
			if((i+1) == 1)
				iq.setCover(R.drawable.cover_12);
			else if((i+1) == 2)
				iq.setCover(R.drawable.cover_13);
			else if((i+1) == 3)
				iq.setCover(R.drawable.cover_14);
			else if((i+1) == 4)
				iq.setCover(R.drawable.cover_15);
			else if((i+1) == 5)
				iq.setCover(R.drawable.cover_16);
			else if((i+1) == 6)
				iq.setCover(R.drawable.cover_17);
			
			dtlist.add(iq);
		}
    }
    
    public void set(List<iqro> list) {
        this.dtlist = list;
        notifyDataSetChanged();
    }
    
    public iqro getItem(int position) {
        return this.dtlist.get(position);
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dtlist.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return dtlist.get(position).getName();
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
	    layout = inflater.inflate(R.layout.book_item, null);
	    ImageView img = (ImageView) layout.findViewById(R.id.imageView1);
	    final iqro iq = dtlist.get(position);
	    img.setImageResource(iq.getCover());
	    ((ViewPager) container).addView(layout, 0);
	   
	    img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, PageListActivity.class);
				intent.putExtra(Constant.PLAY_MODE,playmode);
				intent.putExtra(Constant.SELECTED_BOOK, iq.getId());
				intent.putExtra(Constant.ARG_CHILD_ID, childID);
				context.startActivity(intent);
			}
		});
		return layout;
	}

}