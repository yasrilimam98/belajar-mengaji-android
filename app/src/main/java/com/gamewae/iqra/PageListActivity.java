package com.gamewae.iqra;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.gamewae.iqra.adapter.PageAdapter;
import com.gamewae.iqra.entity.page;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.jsonParser;

public class PageListActivity extends Activity {
	private GridView gridView;
	private int iqro  = 1;
	private int playmode = Constant.PLAY_MODE_CHILD;
	private PageAdapter adapter;
	private int childID = -1;
	private ProgressDialog loader;
	public static int FINIS_BOOK = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page_list);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			iqro = extras.getInt(Constant.SELECTED_BOOK);
			playmode = extras.getInt(Constant.PLAY_MODE);
			childID = extras.getInt(Constant.ARG_CHILD_ID);
		}
		
		gridView = (GridView) findViewById(R.id.gridView1);
		adapter = new PageAdapter(this,playmode,childID);
		gridView.setAdapter(adapter);
		loader = new ProgressDialog(this);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub
				page p = (page) adapter.getItem(position);
				
				Intent intent = new Intent(PageListActivity.this, SectionViewActivity.class);
				intent.putParcelableArrayListExtra(Constant.ARG_SECTION, p.getSections());
				intent.putExtra(Constant.PLAY_MODE, playmode);
				intent.putExtra(Constant.ARG_CHILD_ID, childID);
				intent.putExtra(Constant.ARG_SELECTED_PAGE, position);
				startActivityForResult(intent, SectionActivity.FINIS_BOOK);
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadAsyc load = new loadAsyc();
		load.execute();
	}
	
	public class loadAsyc extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			loader.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
				runOnUiThread(new  Runnable() {
					public void run() {
						jsonParser json = new jsonParser(PageListActivity.this, "iqro_"+iqro,childID);
						json.parse();
						adapter.set( json.getAllPage());
					}
				});
				return "";
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			loader.dismiss();
			
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			loader.dismiss();
		}
	}
}
