package com.gamewae.iqra.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gamewae.iqra.R;
import com.gamewae.iqra.SectionActivity;
import com.gamewae.iqra.SectionViewActivity;
import com.gamewae.iqra.entity.section;
import com.gamewae.iqra.utils.Constant;

public class SectionAdapter extends BaseAdapter {	
	private Activity activity;
	private LayoutInflater inflater;
	private ArrayList<section> dtlist ;
	private int playmode = Constant.PLAY_MODE_CHILD;
	private int childID = -1;
	public SectionAdapter(Activity activity) {
		this.activity = activity;
		dtlist = new ArrayList<section>();
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public SectionAdapter(Activity activity,int playmode,int childID) {
		this.activity = activity;
		this.childID = childID;
		dtlist = new ArrayList<section>();
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.playmode = playmode;
	}
	
	@Override
	public int getCount() {
		return dtlist.size();
	}
	@Override
	public Object getItem(int location) {
		return dtlist.get(location);
	}
	
	public void add(section item) {
	
		dtlist.add(item);
		notifyDataSetChanged();
	}
	
	public ArrayList<section> getAll() {
		
		return dtlist;
	}
	
	public void add(ArrayList<section> item){
	
		for (int i = 0; i < item.size(); i++) {
			dtlist.add(item.get(i));
		}
		notifyDataSetChanged();
	}
	
	public void set(ArrayList<section> data) {
		dtlist = data;
		notifyDataSetChanged();
	}
	
	public void insert(section item,int pos) {
	
		dtlist.add(pos, item);
		notifyDataSetChanged();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder{
	     public TextView text;
	     public RelativeLayout background;
	     public ImageView img;
	     public ImageView star1;
	     public ImageView star2;
	     public ImageView star3;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
        ViewHolder holder;
        
        if(convertView==null){
            vi = inflater.inflate(R.layout.section_item, null);
            
            holder = new ViewHolder();
            
            holder.text = (TextView) vi.findViewById(R.id.textView1);
            holder.img = (ImageView) vi.findViewById(R.id.imageView1);
          //  holder.star1 = (ImageView) vi.findViewById(R.id.imageView2);
            holder.star2 = (ImageView) vi.findViewById(R.id.imageView3);
          //  holder.star3 = (ImageView) vi.findViewById(R.id.imageView4);
          //  holder.background = (RelativeLayout) vi.findViewById(R.id.btnWrapper);
            
            vi.setTag(holder);
        }
        else
            holder=(ViewHolder)vi.getTag();
        
        final section data = this.dtlist.get(position);
        holder.text.setText(data.getName());
        
        holder.star1.setVisibility(View.GONE);
        holder.star2.setVisibility(View.GONE);
        holder.star3.setVisibility(View.GONE);
        
        if(childID != -1)
        {
        	if(data.getScore() == 1)
            {
            	 holder.star1.setVisibility(View.VISIBLE);
            }
            else if(data.getScore() == 2)
            {
            	 holder.star1.setVisibility(View.VISIBLE);
            	 holder.star2.setVisibility(View.VISIBLE);
            }
            else if(data.getScore() == 3)
            {
            	 holder.star1.setVisibility(View.VISIBLE);
            	 holder.star2.setVisibility(View.VISIBLE);
            	 holder.star3.setVisibility(View.VISIBLE);
            }
            
        }
        
      /*  if(playmode == Constant.PLAY_MODE_CHILD)
        	holder.background.setBackgroundResource(R.drawable.btn_section_child_state);
        else
        	holder.background.setBackgroundResource(R.drawable.btn_section_state);
       
        */
        holder.background.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, SectionViewActivity.class);
				intent.putParcelableArrayListExtra(Constant.ARG_SECTION, dtlist);
				intent.putExtra(Constant.PLAY_MODE, playmode);
				intent.putExtra(Constant.ARG_CHILD_ID, childID);
				intent.putExtra(Constant.ARG_SELECTED_SECTION, position);
				activity.startActivityForResult(intent, SectionActivity.FINIS_BOOK);;
			}
		});
       
        
		return vi;
	}
	
}
