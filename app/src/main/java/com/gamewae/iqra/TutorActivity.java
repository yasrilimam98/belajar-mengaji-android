package com.gamewae.iqra;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gamewae.iqra.adapter.TutorPagerAdapter;
import com.gamewae.iqra.entity.Tutor;
import com.gamewae.iqra.entity.page;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.CirclePageIndicator;
import com.gamewae.iqra.widget.PageIndicator;

public class TutorActivity extends Activity implements OnClickListener {
	private ViewPager pager;
	private TutorPagerAdapter adapter;
	private  PageIndicator mIndicator;
	private MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutor);
		
		mIndicator = (CirclePageIndicator)findViewById(R.id.indicators);
		pager = (ViewPager)findViewById(R.id.viewPager);
		adapter = new TutorPagerAdapter(this);
		pager.setAdapter(adapter);
		mIndicator.setViewPager(pager);
		
		populateTutorList();
		
		findViewById(R.id.btnExit).setOnClickListener(this);
	    Button  btnplaySound = (Button)findViewById(R.id.btnPlaySound);
	    btnplaySound.setTypeface(Shared.appfont);
	    btnplaySound.setOnClickListener(this);
		 
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mp != null)
			mp.release();
		
		switch (v.getId()) {
		case R.id.btnExit:
			finish();
			break;
		case R.id.btnPlaySound:
			Tutor t = adapter.getItem(pager.getCurrentItem());
			mp = MediaPlayer.create(this, t.getSound());
	    	mp.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					if(mp != null)
						mp.release();
				}
			});
			mp.start();
			break;
		default:
			break;
		}
	}
	
	private void populateTutorList()
	{
		ArrayList<Tutor> tlist = new ArrayList<Tutor>();
		Tutor t1 = new Tutor();
		t1.setHijaiyah("ا");
		t1.setImg(R.drawable.tutor_3);
		t1.setSound(R.raw.ba);
		t1.setTitle("Dua bibir menempel");
		t1.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t2 = new Tutor();
		t2.setHijaiyah("ب");
		t2.setImg(R.drawable.tutor_9);
		t2.setSound(R.raw.ba);
		t2.setTitle("Dua bibir menempel");
		t2.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t3 = new Tutor();
		t3.setHijaiyah("ت");
		t3.setImg(R.drawable.tutor_8);
		t3.setSound(R.raw.ba);
		t3.setTitle("Dua bibir menempel");
		t3.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t4 = new Tutor();
		t4.setHijaiyah("ث");
		t4.setImg(R.drawable.tutor_4);
		t4.setSound(R.raw.ba);
		t4.setTitle("Dua bibir menempel");
		t4.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t5 = new Tutor();
		t5.setHijaiyah("ج");
		t5.setImg(R.drawable.tutor_7);
		t5.setSound(R.raw.ba);
		t5.setTitle("Dua bibir menempel");
		t5.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t6 = new Tutor();
		t6.setHijaiyah("ح");
		t6.setImg(R.drawable.tutor_3);
		t6.setSound(R.raw.ba);
		t6.setTitle("Dua bibir menempel");
		t6.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		
		Tutor t7 = new Tutor();
		t7.setHijaiyah("خ");
		t7.setImg(R.drawable.tutor_3);
		t7.setSound(R.raw.ba);
		t7.setTitle("Dua bibir menempel");
		t7.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t8 = new Tutor();
		t8.setHijaiyah("د");
		t8.setImg(R.drawable.tutor_8);
		t8.setSound(R.raw.ba);
		t8.setTitle("Dua bibir menempel");
		t8.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t9 = new Tutor();
		t9.setHijaiyah("ذ");
		t9.setImg(R.drawable.tutor_4);
		t9.setSound(R.raw.ba);
		t9.setTitle("Dua bibir menempel");
		t9.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t10 = new Tutor();
		t10.setHijaiyah("ر");
		t10.setImg(R.drawable.tutor_5);
		t10.setSound(R.raw.ba);
		t10.setTitle("Dua bibir menempel");
		t10.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		
		Tutor t11 = new Tutor();
		t11.setHijaiyah("ز");
		t11.setImg(R.drawable.tutor_1);
		t11.setSound(R.raw.ba);
		t11.setTitle("Dua bibir menempel");
		t11.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		
		Tutor t12 = new Tutor();
		t12.setHijaiyah("س");
		t12.setImg(R.drawable.tutor_1);
		t12.setSound(R.raw.ba);
		t12.setTitle("Dua bibir menempel");
		t12.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t13 = new Tutor();
		t13.setHijaiyah("ش");
		t13.setImg(R.drawable.tutor_3);
		t13.setSound(R.raw.ba);
		t13.setTitle("Dua bibir menempel");
		t13.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t14 = new Tutor();
		t14.setHijaiyah("ص");
		t14.setImg(R.drawable.tutor_1);
		t14.setSound(R.raw.ba);
		t14.setTitle("Dua bibir menempel");
		t14.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t15 = new Tutor();
		t15.setHijaiyah("ض");
		t15.setImg(R.drawable.tutor_2);
		t15.setSound(R.raw.ba);
		t15.setTitle("Dua bibir menempel");
		t15.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t16 = new Tutor();
		t16.setHijaiyah("ط");
		t16.setImg(R.drawable.tutor_8);
		t16.setSound(R.raw.ba);
		t16.setTitle("Dua bibir menempel");
		t16.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t17 = new Tutor();
		t17.setHijaiyah("ظ");
		t17.setImg(R.drawable.tutor_4);
		t17.setSound(R.raw.ba);
		t17.setTitle("Dua bibir menempel");
		t17.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t18 = new Tutor();
		t18.setHijaiyah("ع");
		t18.setImg(R.drawable.tutor_3);
		t18.setSound(R.raw.ba);
		t18.setTitle("Dua bibir menempel");
		t18.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t19 = new Tutor();
		t19.setHijaiyah("غ");
		t19.setImg(R.drawable.tutor_3);
		t19.setSound(R.raw.ba);
		t19.setTitle("Dua bibir menempel");
		t19.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t20 = new Tutor();
		t20.setHijaiyah("ف");
		t20.setImg(R.drawable.tutor_9);
		t20.setSound(R.raw.ba);
		t20.setTitle("Dua bibir menempel");
		t20.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t21 = new Tutor();
		t21.setHijaiyah("ق");
		t21.setImg(R.drawable.tutor_6);
		t21.setSound(R.raw.ba);
		t21.setTitle("Dua bibir menempel");
		t21.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t22 = new Tutor();
		t22.setHijaiyah("ك");
		t22.setImg(R.drawable.tutor_6);
		t22.setSound(R.raw.ba);
		t22.setTitle("Dua bibir menempel");
		t22.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t23 = new Tutor();
		t23.setHijaiyah("ل");
		t23.setImg(R.drawable.tutor_5);
		t23.setSound(R.raw.ba);
		t23.setTitle("Dua bibir menempel");
		t23.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t24 = new Tutor();
		t24.setHijaiyah("م");
		t24.setImg(R.drawable.tutor_9);
		t24.setSound(R.raw.ba);
		t24.setTitle("Dua bibir menempel");
		t24.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t25 = new Tutor();
		t25.setHijaiyah("ن");
		t25.setImg(R.drawable.tutor_3);
		t25.setSound(R.raw.ba);
		t25.setTitle("Dua bibir menempel");
		t25.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t26 = new Tutor();
		t26.setHijaiyah("و");
		t26.setImg(R.drawable.tutor_5);
		t26.setSound(R.raw.ba);
		t26.setTitle("Dua bibir menempel");
		t26.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t28 = new Tutor();
		t28.setHijaiyah("ه");
		t28.setImg(R.drawable.tutor_3);
		t28.setSound(R.raw.ba);
		t28.setTitle("Dua bibir menempel");
		t28.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		Tutor t29 = new Tutor();
		t29.setHijaiyah("ي");
		t29.setImg(R.drawable.tutor_7);
		t29.setSound(R.raw.ba);
		t29.setTitle("Dua bibir menempel");
		t29.setText("Nafas ditahan, dan ketika disukun harus dibedalkan");
		
		tlist.add(t1);
		tlist.add(t2);
		tlist.add(t3);
		tlist.add(t4);
		tlist.add(t5);
		tlist.add(t6);
		tlist.add(t7);
		tlist.add(t8);
		tlist.add(t9);
		tlist.add(t10);
		tlist.add(t11);
		tlist.add(t12);
		tlist.add(t13);
		tlist.add(t14);
		tlist.add(t15);
		tlist.add(t16);
		tlist.add(t17);
		tlist.add(t18);
		tlist.add(t19);
		tlist.add(t20);
		tlist.add(t21);
		tlist.add(t22);
		tlist.add(t23);
		tlist.add(t24);
		tlist.add(t25);
		tlist.add(t26);
		tlist.add(t28);
		tlist.add(t29);
		
		adapter.set(tlist);
		
	}
}
