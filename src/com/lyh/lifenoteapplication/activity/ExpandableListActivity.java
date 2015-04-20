package com.lyh.lifenoteapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.lyh.lifenoteapplication.R;

public class ExpandableListActivity extends Activity {
    private final static String TAG = "ExpandableListActivity";

    private ExpandableListView mListView;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);
        mListView = (ExpandableListView) findViewById(R.id.listView);
        mLayoutInflater = LayoutInflater.from(this);
        mListView.setAdapter(mListAdapter);
        mListView.setGroupIndicator(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListAdapter.notifyDataSetChanged();
    }

    private final BaseExpandableListAdapter mListAdapter = new BaseExpandableListAdapter() {
        
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return false;
        }
        
        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }
        
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            Log.d(TAG, "convertView:"+groupPosition);
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.group_item, null);
            }
            return convertView;
        }
        
        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }
        
        @Override
        public int getGroupCount() {
            Log.d(TAG, "getGroupCount");
            return 40;
        }
        
        @Override
        public Object getGroup(int groupPosition) {
            // TODO Auto-generated method stub
            return null;
        }
        
        @Override
        public int getChildrenCount(int groupPosition) {
            Log.d(TAG, "getChildrenCount");
            return 0;
        }
        
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return null;
        }
        
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return 0;
        }
        
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return null;
        }
    };
}
