package com.gamewae.iqra;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.gamewae.iqra.sqlite.DatabaseHelper;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.CustomImageButton;

public class MainActivity extends Activity implements OnClickListener{
	private LinearLayout topWrapper;
	private LinearLayout bottomWrapper;
	private CustomImageButton btnDonasi,btnSendiri,btnSetting,btnPengajar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DatabaseManager.initializeInstance(new DatabaseHelper(this));
		setContentView(R.layout.activity_main);
		
		TextView t1 = (TextView)findViewById(R.id.textView1);
		TextView t2 = (TextView)findViewById(R.id.textView2);
		TextView t3 = (TextView)findViewById(R.id.textView3);
		TextView t4 = (TextView)findViewById(R.id.textView4);
		
		t1.setTypeface(Shared.appfont);
		t2.setTypeface(Shared.appfont);
		t3.setTypeface(Shared.appfont);
		t4.setTypeface(Shared.appfont);
		
		topWrapper = (LinearLayout)findViewById(R.id.mainTop);
		bottomWrapper = (LinearLayout)findViewById(R.id.mainBottom);
		
		final int width = Shared.getDisplayWidth();
		final int height = Shared.getDisplayHeight();
		
		LayoutParams paramBottom = new LayoutParams(width, width); 
		paramBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		bottomWrapper.setLayoutParams(paramBottom);
		
		bottomWrapper.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				LayoutParams paramTop = new LayoutParams(width, height - bottomWrapper.getHeight());
				topWrapper.setLayoutParams(paramTop);
			}
		});
		
		
		btnDonasi = (CustomImageButton)findViewById(R.id.btnDonasi);
		btnSendiri = (CustomImageButton)findViewById(R.id.btnSendiri);
		btnPengajar = (CustomImageButton)findViewById(R.id.btnPengajar);
		btnSetting = (CustomImageButton)findViewById(R.id.btnPengaturan);
		
		
		btnSetting.setOnClickListener(this);
		btnSendiri.setOnClickListener(this);
		btnPengajar.setOnClickListener(this);
		btnDonasi.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnDonasi:
			intent = new Intent(MainActivity.this, DonasiActivity.class);
			break;
		case R.id.btnSendiri:
			intent = new Intent(MainActivity.this, BookActivity.class);
			intent.putExtra(Constant.PLAY_MODE, Constant.PLAY_MODE_CHILD);
			break;
		case R.id.btnPengajar:
			intent = new Intent(MainActivity.this, LoginActivity.class);
			intent.putExtra(Constant.PLAY_MODE, Constant.PLAY_MODE_PARENT);
			break;
		case R.id.btnPengaturan:
			intent = new Intent(MainActivity.this, SettingActivity.class);
			break;
		default:
			break;
		}
		
		if(intent != null)
			startActivity(intent);
	}

}
