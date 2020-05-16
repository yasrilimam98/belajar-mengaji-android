package com.gamewae.iqra;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.gamewae.iqra.adapter.ChildAdapter;
import com.gamewae.iqra.entity.Child;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.sqlite.ds.ChildDataSource;
import com.gamewae.iqra.utils.Constant;
import com.gamewae.iqra.utils.Shared;
import com.gamewae.iqra.widget.addChildDialog;
import com.gamewae.iqra.widget.addChildDialog.onComplete;

public class ChildListActivity extends FragmentActivity implements OnClickListener {
	private ListView lv;
	private ChildAdapter adapter;
	private TextView txtaddchild;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_child_list);
		lv = (ListView)findViewById(R.id.listView1);
		adapter = new ChildAdapter(this);
		
		SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
	
		ChildDataSource DS = new ChildDataSource(db);
		adapter.set(DS.getAll());
		DatabaseManager.getInstance().closeDatabase();
		
		txtaddchild = (TextView)findViewById(R.id.txtaddchild);
		if(adapter.getCount() == 0)
		{
			txtaddchild.setVisibility(View.VISIBLE);
		}
		
		
		findViewById(R.id.btnAdd).setOnClickListener(this);
		findViewById(R.id.btnBack).setOnClickListener(this);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub
				Child c = (Child) adapter.getItem(position);
				Intent intent = new Intent(ChildListActivity.this, BookActivity.class);
				intent.putExtra(Constant.PLAY_MODE, Constant.PLAY_MODE_PARENT);
				intent.putExtra(Constant.ARG_CHILD_ID,c.getId());
				startActivity(intent);
			}
		});
		
		
		lv.setAdapter(adapter);
		
		TextView t1 = (TextView)findViewById(R.id.title);
		t1.setTypeface(Shared.appfont);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAdd:
			add();
			break;
		case R.id.btnBack:
			finish();
		default:
			break;
		}
	}
	
	private void add()
	{
		addChildDialog dialog = new addChildDialog();
		dialog.setOnCompleteListener(new onComplete() {
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
				ChildDataSource DS = new ChildDataSource(db);
				adapter.set(DS.getAll());
				txtaddchild.setVisibility(View.GONE);
				DatabaseManager.getInstance().closeDatabase();
			}
		});
		
		dialog.show(getSupportFragmentManager(), "");
	}
	
	
}
