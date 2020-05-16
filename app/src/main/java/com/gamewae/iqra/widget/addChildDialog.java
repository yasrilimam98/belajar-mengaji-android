package com.gamewae.iqra.widget;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gamewae.iqra.R;
import com.gamewae.iqra.entity.Child;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.sqlite.ds.ChildDataSource;
import com.gamewae.iqra.utils.Shared;

public class addChildDialog extends DialogFragment implements OnClickListener{
	private onComplete _on;
	public interface onComplete
	{
		public void onCompleted();
	}
	
	public void setOnCompleteListener(onComplete on)
	{
		_on = on;
	}
	private EditText txtname;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		  final Dialog dialog = new Dialog(getActivity());  
		  dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);  
		  dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
		  WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		  dialog.setContentView(R.layout.add_child_item);  
		  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  
		  
		  txtname  = (EditText)dialog.findViewById(R.id.txtname);
	
		  Button ok = (Button)dialog.findViewById(R.id.btnOk);
		  Button cancel = (Button)dialog.findViewById(R.id.btnCancel);
		  
		  txtname.setTypeface(Shared.appfontThin);
		  ok.setTypeface(Shared.appfont);
		  cancel.setTypeface(Shared.appfont);
		  
		  ok.setOnClickListener(this);
		  cancel.setOnClickListener(this);
	    
	    return dialog;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnOk:
			save();
			break;
		case R.id.btnCancel:
			this.dismiss();
			break;
		default:
			break;
		}
	}

	private void save()
	{
		if(txtname.getText().toString().equals(""))
		{
			Toast.makeText(getActivity(), R.string.please_enter_child_name, Toast.LENGTH_SHORT).show();	
			return;
		}
		
		boolean exist = false;
		SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
		Child c = new Child();
		c.setName(txtname.getText().toString());
		c.setCurrentIqro("1");
		ChildDataSource DS = new ChildDataSource(db);
		if(!DS.cekName(c.getName()))
			DS.insert(c);
		else
			exist = true;
			
		DatabaseManager.getInstance().closeDatabase();
		if(!exist)
		{
			if(_on != null)
				_on.onCompleted();
			
			this.dismiss();
		}
		else
			Toast.makeText(getActivity(), R.string.name_already_exist, Toast.LENGTH_SHORT).show();
	}

}
