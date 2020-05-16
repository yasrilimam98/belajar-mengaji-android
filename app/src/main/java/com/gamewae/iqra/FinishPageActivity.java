package com.gamewae.iqra;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamewae.iqra.entity.Score;
import com.gamewae.iqra.entity.section;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.sqlite.ds.ScoreDataSource;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;

public class FinishPageActivity extends Activity implements OnClickListener {
	private int playmode = Constant.PLAY_MODE_CHILD; 
	private  ArrayList<section> selectedSection = new ArrayList<section>();
	private int childID = -1;
	private Button btnBack,btnNext;
	private TextView txtScore;
	private ImageView imgStar;
	private int allsectionScore = 0;
	private int starcount = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish_book);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			playmode = extras.getInt(Constant.PLAY_MODE);
			selectedSection = extras.getParcelableArrayList(Constant.ARG_SECTION);
			childID = extras.getInt(Constant.ARG_CHILD_ID);
		}
		
		btnBack = (Button)findViewById(R.id.btnBack);
		btnNext = (Button)findViewById(R.id.btnNext);
		txtScore = (TextView)findViewById(R.id.txtscore);
		imgStar = (ImageView)findViewById(R.id.imgStar);
		
		TextView t1 = (TextView)findViewById(R.id.textView1);
		btnBack.setTypeface(Shared.appfont);
		btnNext.setTypeface(Shared.appfont);
		txtScore.setTypeface(Shared.appfontBold);
		t1.setTypeface(Shared.appfontLight);
		
		btnBack.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		
		calcualteScore();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
      	 switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;
		case R.id.btnNext:
			finish();
			break;
		default:
			break;
		}
	}
	
	private void calcualteScore()
	{
		int sectionCount = selectedSection.size();
		allsectionScore = 0;
		for (int i = 0; i < sectionCount; i++) {
			section sc = selectedSection.get(i);
			allsectionScore += sc.getScore();
			
			Score scr = new Score();
			scr.setChildId(childID);
			scr.setIqroId(sc.getIqroId());
			scr.setPagesId(sc.getPageId());
			scr.setSectionId(sc.getSectionId());
			scr.setScore(sc.getScore());
			
			SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
			ScoreDataSource DS = new ScoreDataSource(db);
			if(DS.cekCode(scr))
				DS.update(scr);
			else
				DS.insert(scr);
			DatabaseManager.getInstance().closeDatabase();
		}
		
		
		starcount = 0;
		if(allsectionScore != 0)
		{
			starcount = Math.round(allsectionScore / selectedSection.size());
		}
		
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ScoreCounter counter = new ScoreCounter();
				counter.execute(allsectionScore,starcount);
			}
		}, 500);
		
	//	txtScore.setText(String.valueOf(allsectionScore));
		
	}
	
	private class ScoreCounter extends AsyncTask<Integer, Integer, Integer> {
		int i = 0;
        @Override
        protected Integer doInBackground(Integer... params) {
        	
        	for (i = 0; i < params[0]; i++) {
        		runOnUiThread(new Runnable() {
					public void run() {
						txtScore.setText(String.valueOf(i+1));
					}
				});
    			
    			try {
    				Thread.sleep(50);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			
    		}
        	
        	if(params[1] != 0)
        	{
        		for (i = 0; i < params[1]; i++) {
            		runOnUiThread(new Runnable() {
    					public void run() {
    						if(i == 0 )
    							imgStar.setImageResource(R.drawable.star_1);
    						else if(i == 1 )
    							imgStar.setImageResource(R.drawable.star_2);
    						else if(i == 2 )
    							imgStar.setImageResource(R.drawable.star_3);
    					}
    				});
        			
        			try {
        				Thread.sleep(500);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        			
        		}
        	}
        	
        	runOnUiThread(new Runnable() {
				public void run() {
					btnNext.setVisibility(View.VISIBLE);
				}
			});
        	
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
        }

        @Override
        protected void onPreExecute() {
        }

    }
}
