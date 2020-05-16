package com.gamewae.iqra;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.gamewae.iqra.utils.Shared;

public class DonasiActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donasi);
		
		TextView t1 = (TextView)findViewById(R.id.textView1);
		TextView t2 = (TextView)findViewById(R.id.textView2);
		TextView t3 = (TextView)findViewById(R.id.textView3);
		
		t1.setTypeface(Shared.appfontLight);
		t2.setTypeface(Shared.appfont);
		t3.setTypeface(Shared.appfont);
		
		
		findViewById(R.id.btnPaypal).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnPaypal:
			String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=CPC3R7RBRH2BN&lc=ID&item_name=New%20Iqro%20App&item_number=com%2egamewae%2eiqro&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
			break;
		default:
			break;
		}
	}

}
