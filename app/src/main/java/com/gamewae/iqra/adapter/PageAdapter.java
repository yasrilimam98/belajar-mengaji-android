package com.gamewae.iqra.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gamewae.iqra.R;
import com.gamewae.iqra.SectionActivity;
import com.gamewae.iqra.SectionViewActivity;
import com.gamewae.iqra.entity.page;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;

public class PageAdapter extends BaseAdapter {	
	private Activity activity;
	private LayoutInflater inflater;
	private ArrayList<page> dtlist ;
	private int playmode = Constant.PLAY_MODE_CHILD;
	private int childID = -1;
	private int screenWidth;
	public PageAdapter(Activity activity) {
		this.activity = activity;
		dtlist = new ArrayList<page>();
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		screenWidth = Shared.getDisplayWidth();
	}
	
	public PageAdapter(Activity activity,int playmode,int childID) {
		this.activity = activity;
		this.childID = childID;
		dtlist = new ArrayList<page>();
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.playmode = playmode;
		screenWidth = Shared.getDisplayWidth();
	}
	
	@Override
	public int getCount() {
		return dtlist.size();
	}
	@Override
	public Object getItem(int location) {
		return dtlist.get(location);
	}
	
	public void add(page item) {
	
		dtlist.add(item);
		notifyDataSetChanged();
	}
	
	public ArrayList<page> getAll() {
		
		return dtlist;
	}
	
	public void add(ArrayList<page> item){
	
		for (int i = 0; i < item.size(); i++) {
			dtlist.add(item.get(i));
		}
		notifyDataSetChanged();
	}
	
	public void set(ArrayList<page> data) {
		dtlist = data;
		notifyDataSetChanged();
	}
	
	public void insert(page item,int pos) {
	
		dtlist.add(pos, item);
		notifyDataSetChanged();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder{
	     public TextView text;
	     public ImageView star;
	     public LinearLayout wrapper;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
        ViewHolder holder;
        
        if(convertView==null){
            vi = inflater.inflate(R.layout.page_item, null);
            
            holder = new ViewHolder();
            
            holder.text = (TextView) vi.findViewById(R.id.textView1);
            holder.star = (ImageView) vi.findViewById(R.id.imageView1);
            holder.wrapper = (LinearLayout)vi.findViewById(R.id.gridWrapper);
            vi.setTag(holder);
        }
        else
            holder=(ViewHolder)vi.getTag();
        
        
        final page data = this.dtlist.get(position);
        holder.text.setText(data.getLabel());
        holder.text.setTypeface(Shared.appfontLight);
        
        
        LayoutParams param = new LayoutParams(screenWidth/4, screenWidth/4);
        holder.wrapper.setLayoutParams(param);
        
        if(playmode == Constant.PLAY_MODE_CHILD)
    	   holder.star.setVisibility(View.GONE);
        else
        	holder.star.setVisibility(View.VISIBLE);
        

        if(childID != -1)
        {
        	if(data.getScore() == 1)
            {
            	 holder.star.setImageResource(R.drawable.star_1);
            }
            else if(data.getScore() == 2)
            {
            	holder.star.setImageResource(R.drawable.star_2);
            }
            else if(data.getScore() == 3)
            {
            	holder.star.setImageResource(R.drawable.star_3);
            }
            
        }
        
		return vi;
	}
	
}
