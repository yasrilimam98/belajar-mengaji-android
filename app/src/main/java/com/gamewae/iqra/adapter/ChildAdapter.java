package com.gamewae.iqra.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gamewae.iqra.R;
import com.gamewae.iqra.entity.Child;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.sqlite.ds.ChildDataSource;
import com.gamewae.iqra.sqlite.ds.ScoreDataSource;
import com.gamewae.iqra.utils.Shared;

public class ChildAdapter extends BaseAdapter {	
	private Activity activity;
	private LayoutInflater inflater;
	private List<Child> dtlist ;
	
	public ChildAdapter(Activity activity) {
		this.activity = activity;
		dtlist = new ArrayList<Child>();
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {
		return dtlist.size();
	}
	@Override
	public Object getItem(int location) {
		return dtlist.get(location);
	}
	
	public void add(Child item) {
	
		dtlist.add(item);
		notifyDataSetChanged();
	}
	
	public List<Child> getAll() {
		
		return dtlist;
	}
	
	public void add(List<Child> item){
	
		for (int i = 0; i < item.size(); i++) {
			dtlist.add(item.get(i));
		}
		notifyDataSetChanged();
	}
	
	public void set(List<Child> data) {
		dtlist = data;
		notifyDataSetChanged();
	}
	
	public void insert(Child item,int pos) {
	
		dtlist.add(pos, item);
		notifyDataSetChanged();
	}
	
	public void remove(Child item) {
		dtlist.remove(item);
		notifyDataSetChanged();
	}

	public void remove(int position) {
		dtlist.remove(position);
		notifyDataSetChanged();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder{
	     public TextView text;
	     public TextView text2;
	     public ImageButton btn;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
        ViewHolder holder;
        
        if(convertView==null){
            vi = inflater.inflate(R.layout.child_item, null);
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textView1);
            holder.text2 = (TextView) vi.findViewById(R.id.textView2);
            holder.btn = (ImageButton) vi.findViewById(R.id.imageButton1);
            vi.setTag(holder);
        }
        else
            holder=(ViewHolder)vi.getTag();
        
        
        final Child data = this.dtlist.get(position);
        holder.text.setText(data.getName());
        holder.text2.setText("Iqro "+data.getCurrentIqro());
        
        holder.text.setTypeface(Shared.appfont);
        holder.text2.setTypeface(Shared.appfontLight);
        holder.btn.setFocusable(false);
        holder.btn.setFocusableInTouchMode(false);
        
        if(data.getCurrentIqro().equals("1"))
        	 holder.text2.setTextColor(activity.getResources().getColor(R.color.red));
        else if(data.getCurrentIqro().equals("2"))
       	 	holder.text2.setTextColor(activity.getResources().getColor(R.color.green));
        else if(data.getCurrentIqro().equals("3"))
       	 	holder.text2.setTextColor(activity.getResources().getColor(R.color.blue));
        else if(data.getCurrentIqro().equals("4"))
       	 	holder.text2.setTextColor(activity.getResources().getColor(R.color.oren));
        else if(data.getCurrentIqro().equals("5"))
       	 	holder.text2.setTextColor(activity.getResources().getColor(R.color.violet));
        else if(data.getCurrentIqro().equals("6"))
       	 	holder.text2.setTextColor(activity.getResources().getColor(R.color.brown));
        
        holder.btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
		        alertDialog.setTitle(R.string.confirmation);
		        alertDialog.setMessage(R.string.are_you_sure_want_to_delete);
		        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog,int which) {
		            	SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
						ChildDataSource DS = new ChildDataSource(db);
						ScoreDataSource DS2 = new ScoreDataSource(db);
						DS.delete(data.getId());
						DS2.deleteByChild(data.getId());
						dtlist.remove(position);
						DatabaseManager.getInstance().closeDatabase();
						notifyDataSetChanged();
		            }
		        });
		 
		        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	dialog.cancel();
		            }
		        });
		 
		        alertDialog.show();
		        
			}
		});
        
		return vi;
	}
	
}
