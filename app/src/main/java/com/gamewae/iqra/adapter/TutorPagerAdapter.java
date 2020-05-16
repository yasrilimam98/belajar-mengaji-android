package com.gamewae.iqra.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamewae.iqra.R;
import com.gamewae.iqra.entity.Tutor;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.CustomImageButton;

public class TutorPagerAdapter extends PagerAdapter {
    private List<Tutor> dtlist ;
    private Activity context;
    private View layout;
    private MediaPlayer mp;
    public TutorPagerAdapter(Activity context) {
        this.context = context;
        dtlist = new ArrayList<Tutor>();
    }
    
    public void set(List<Tutor> list) {
        this.dtlist = list;
        notifyDataSetChanged();
    }
    
    public Tutor getItem(int position) {
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
		return dtlist.get(position).getTitle();
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
	    layout = inflater.inflate(R.layout.tutor_item, null);
	  
	    ImageView img2 = (ImageView) layout.findViewById(R.id.imageView2);
	    TextView t1 = (TextView) layout.findViewById(R.id.txthinttitle);
	    TextView t2 = (TextView) layout.findViewById(R.id.txthinttext);
	    TextView t3 = (TextView) layout.findViewById(R.id.textView1);
	    
	    t1.setTypeface(Shared.appfont);
	    t2.setTypeface(Shared.appfontLight);
	    
	    final Tutor iq = dtlist.get(position);
	    t3.setText(iq.getHijaiyah());
	    img2.setImageResource(iq.getImg());
	    t1.setText(iq.getTitle());
	    t2.setText(iq.getText());
	    
	  
	   
	   ((ViewPager) container).addView(layout, 0);
	   
		return layout;
	}

}