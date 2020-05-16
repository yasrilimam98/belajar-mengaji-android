package com.gamewae.iqra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText txtpin;
	private EditText txtpinConfirm;
	private int playmode = Constant.PLAY_MODE_PARENT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			playmode = extras.getInt(Constant.PLAY_MODE);
		}
		
		Button btnOK = (Button)findViewById(R.id.btnMulai);
		btnOK.setOnClickListener(this);
		
		txtpin = (EditText)findViewById(R.id.editText1);
		txtpinConfirm = (EditText)findViewById(R.id.editText2);
		
		btnOK.setTypeface(Shared.appfont);
		txtpin.setTypeface(Shared.appfontLight);
		txtpinConfirm.setTypeface(Shared.appfontLight);
		
		TextView t1 = (TextView)findViewById(R.id.textView1);
		t1.setTypeface(Shared.appfontLight);
		
		
		if(Shared.read(Constant.FLAG_FIRSTTIMELOGIN,"0").equals("0"))
		{
			t1.setText(R.string.buat_pin_pengajar);
			txtpinConfirm.setVisibility(View.VISIBLE);
		}
		else
		{
			t1.setText(R.string.login);
			txtpinConfirm.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(Shared.read(Constant.FLAG_FIRSTTIMELOGIN,"0").equals("0"))
		{
			if(txtpin.getText().toString().equals(""))
			{
				Toast.makeText(this,R.string.field_cant_be_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(!txtpin.getText().toString().equals(txtpinConfirm.getText().toString()))
			{
				Toast.makeText(this,R.string.pin_confirm_not_match, Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(txtpin.getText().toString().length() < 4)
			{
				Toast.makeText(this,R.string.pin_minimal, Toast.LENGTH_SHORT).show();
				return;
			}
			
			
			
			Shared.write(Constant.SETTING_PIN,txtpin.getText().toString());
			Shared.write(Constant.FLAG_FIRSTTIMELOGIN,"1");
			Toast.makeText(this,R.string.pin_created, Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, ChildListActivity.class);
			intent.putExtra(Constant.PLAY_MODE,playmode);
			startActivity(intent);
			finish();
		}
		else
		{
			if(txtpin.getText().toString().equals(Shared.read(Constant.SETTING_PIN,"")))
			{
				Intent intent = new Intent(this, ChildListActivity.class);
				intent.putExtra(Constant.PLAY_MODE,playmode);
				startActivity(intent);
				finish();
			}
			else
			{
				Toast.makeText(this, R.string.invalid_pin, Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}
	
}
