package com.gamewae.iqra;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import com.gamewae.iqra.adapter.SectionAdapter;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.jsonParser;

public class SectionActivity extends Activity {
	private GridView gridView;
	private int iqro  = 1;
	private int playmode = Constant.PLAY_MODE_CHILD;
	private SectionAdapter adapter;
	private int childID = -1;
	private ProgressDialog loader;
	public static int FINIS_BOOK = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_section);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			iqro = extras.getInt(Constant.SELECTED_BOOK);
			playmode = extras.getInt(Constant.PLAY_MODE);
			childID = extras.getInt(Constant.ARG_CHILD_ID);
		}
		
		gridView = (GridView) findViewById(R.id.gridView1);
		adapter = new SectionAdapter(this,playmode,childID);
		gridView.setAdapter(adapter);
		loader = new ProgressDialog(this);
		
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
						jsonParser json = new jsonParser(SectionActivity.this, "iqro_"+iqro,childID);
						json.parse();
						adapter.set( json.getAllSection());
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == SectionActivity.FINIS_BOOK)
		{
			if(resultCode == RESULT_OK)
				finish();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
