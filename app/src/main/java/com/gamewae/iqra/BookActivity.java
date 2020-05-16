package com.gamewae.iqra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.gamewae.iqra.adapter.BookPagerAdapter;
import com.gamewae.iqra.entity.iqro;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;

public class BookActivity extends Activity implements OnClickListener {
	private ViewPager pager;
	private BookPagerAdapter adapter;
	private TextView title;
	private int playmode = Constant.PLAY_MODE_CHILD;
	private int childID = -1;
	private Button btnBack,btnStart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			playmode = extras.getInt(Constant.PLAY_MODE);
			childID = extras.getInt(Constant.ARG_CHILD_ID);
		}
		
		pager = (ViewPager)findViewById(R.id.viewPager);
		int margin = (int) getResources().getDimension(R.dimen.margin100);
		
		pager.setPadding(margin, 0, margin, 0);
		pager.setClipToPadding(false);
		pager.setPageMargin(0);
		
		adapter = new BookPagerAdapter(this,playmode,childID);
		pager.setAdapter(adapter);
		
		title = (TextView)findViewById(R.id.textView1);
		title.setTypeface(Shared.appfontLight);
		
		btnBack = (Button)findViewById(R.id.btnBack);
		btnStart = (Button)findViewById(R.id.btnMulai);
		
		btnBack.setTypeface(Shared.appfont);
		btnStart.setTypeface(Shared.appfont);
		
		btnBack.setOnClickListener(this);
		btnStart.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;
		case R.id.btnMulai:
			
			iqro iq = adapter.getItem(pager.getCurrentItem());
			Intent intent = new Intent(BookActivity.this, PageListActivity.class);
			intent.putExtra(Constant.PLAY_MODE,playmode);
			intent.putExtra(Constant.SELECTED_BOOK, iq.getId());
			intent.putExtra(Constant.ARG_CHILD_ID, childID);
			startActivity(intent);
			
			break;
		default:
			break;
		}
	}
	

}
