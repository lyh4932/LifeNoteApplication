package com.lyh.lifenoteapplication.activity;

import com.lyh.lifenoteapplication.R;
import com.lyh.lifenoteapplication.view.GridViewItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Activity1 extends Activity implements OnClickListener {
	private GridViewItem mInputItem;
	private GridViewItem mScanItem;
	private GridViewItem mHistoryItem;
	private GridViewItem mSettingItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1);
		mInputItem = (GridViewItem) findViewById(R.id.menu_input);
		mScanItem = (GridViewItem) findViewById(R.id.menu_scan);
		mHistoryItem = (GridViewItem) findViewById(R.id.menu_history);
		mSettingItem = (GridViewItem) findViewById(R.id.menu_setting);
		mInputItem.setOnClickListener(this);
		mScanItem.setOnClickListener(this);
		mHistoryItem.setOnClickListener(this);
		mSettingItem.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == mInputItem) {
			startActivity(new Intent(Activity1.this, Activity2.class));
		} else if (v == mScanItem) {
			startActivity(new Intent(Activity1.this, Activity3.class));
		} else if (v == mHistoryItem) {
			startActivity(new Intent(Activity1.this, Activity4.class));
		} else if (v == mSettingItem) {
			startActivity(new Intent(Activity1.this, Activity5.class));
		} else {
			Toast.makeText(this, "error item clicked", Toast.LENGTH_SHORT).show();
		}
	}
}
