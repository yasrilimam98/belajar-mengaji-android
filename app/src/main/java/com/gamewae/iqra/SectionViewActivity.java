package com.gamewae.iqra;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gamewae.iqra.adapter.SectionPagerAdapter;
import com.gamewae.iqra.entity.Score;
import com.gamewae.iqra.entity.section;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.sqlite.ds.ScoreDataSource;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.CustomImageButton;
import com.gamewae.iqra.widget.CustomPager;

public class SectionViewActivity extends Activity implements OnClickListener {
	private CustomPager pager;
	private SectionPagerAdapter adapter;
	private int playmode = Constant.PLAY_MODE_CHILD; 
	private  ArrayList<section> selectedSection = new ArrayList<section>();
	private TextView title;
	private int childID = -1;
	private int selected = 0;
	
	private CustomImageButton btnBack;
	private Button btnPrev;
	private Button btnNext;
	private Button btnKurang;
	private Button btnCukup;
	private Button btnBagus;
	private CustomImageButton btnInfo;
	
	private ImageButton arrowBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_section_view);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			playmode = extras.getInt(Constant.PLAY_MODE);
			selectedSection = extras.getParcelableArrayList(Constant.ARG_SECTION);
			childID = extras.getInt(Constant.ARG_CHILD_ID);
		}
		
		RelativeLayout btnWrapper = (RelativeLayout)findViewById(R.id.btnScoreWrapper);
		RelativeLayout btnNav = (RelativeLayout)findViewById(R.id.btnNavWrapper);
		
		btnBack = (CustomImageButton)findViewById(R.id.btnBack);
		btnPrev = (Button)findViewById(R.id.btnPrev);
		btnKurang = (Button)findViewById(R.id.btnKurang);
		btnCukup = (Button)findViewById(R.id.btnCukup);
		btnBagus = (Button)findViewById(R.id.btnBagus);
		btnNext = (Button)findViewById(R.id.btnNext);
		btnInfo = (CustomImageButton)findViewById(R.id.btnInfo);
		
		arrowBack = (ImageButton)findViewById(R.id.arrowBack);
		
		btnPrev.setTypeface(Shared.appfont);
		btnKurang.setTypeface(Shared.appfont);
		btnCukup.setTypeface(Shared.appfont);
		btnBagus.setTypeface(Shared.appfont);
		btnNext.setTypeface(Shared.appfont);
		
		
		pager = (CustomPager)findViewById(R.id.viewPager);
		adapter = new SectionPagerAdapter(this);
		adapter.set(selectedSection);
		pager.setAdapter(adapter);
		
		
		if(playmode == Constant.PLAY_MODE_CHILD)
		{
			btnWrapper.setVisibility(View.GONE);
			btnNav.setVisibility(View.VISIBLE);
			
		}
		else
		{
			btnWrapper.setVisibility(View.VISIBLE);
			btnNav.setVisibility(View.GONE);
		}
		
		title = (TextView)findViewById(R.id.title);
	//	title.setTypeface(Shared.mequran);
		
		
		
		pager.addOnPageChangeListener(pageOnchange);
		pager.setCurrentItem(selected, false);
		pager.setCanSlide(true);
		
		btnPrev.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		
		btnKurang.setOnClickListener(this);
		btnCukup.setOnClickListener(this);
		btnBagus.setOnClickListener(this);
		
		btnNext.setOnClickListener(this);
		
		btnInfo.setOnClickListener(this);
		
		btnPrev.setVisibility(View.GONE);
		arrowBack.setVisibility(View.GONE);
	}
	
	private OnPageChangeListener pageOnchange = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
		
			btnPrev.setVisibility(View.VISIBLE);
			arrowBack.setVisibility(View.VISIBLE);
			if(arg0 == 0)
			{
				btnPrev.setVisibility(View.GONE);
				arrowBack.setVisibility(View.GONE);
			}
			
			title.setText(adapter.getPageTitle(arg0));
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}
	};
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;
		case R.id.btnInfo:
			Intent intent = new Intent(this, TutorActivity.class);
			startActivity(intent);
			break;
		case R.id.btnNext:
			next(0);
			break;
		case R.id.btnPrev:
			back();
			break;
		case R.id.btnBagus:
			next(3);		
			break;
		case R.id.btnCukup:
			next(2);
			break;
		case R.id.btnKurang:
			next(1);
			break;
		default:
			break;
		}
	}
	
	private void next(int score)
	{
		if(playmode == Constant.PLAY_MODE_PARENT)
			addScore(score);
		
		if(pager.getCurrentItem() == adapter.getCount() -1)
		{
			Intent intent = new Intent(this, FinishPageActivity.class);
			intent.putExtra(Constant.PLAY_MODE, playmode);
			intent.putExtra(Constant.ARG_CHILD_ID, childID);
			intent.putParcelableArrayListExtra(Constant.ARG_SECTION, selectedSection);
	      	startActivity(intent);
	      	setResult(RESULT_OK);
	      	finish();
		}
		else
			pager.setCurrentItem(pager.getCurrentItem()+1);
	}
	
	private void back()
	{
		pager.setCurrentItem(pager.getCurrentItem()-1);
	}
	
	private void addScore(int score)
	{
		section sc = selectedSection.get(pager.getCurrentItem());
		if(score != -1)
			sc.setScore(score);
		
		selectedSection.set(pager.getCurrentItem(), sc);
	}
}
