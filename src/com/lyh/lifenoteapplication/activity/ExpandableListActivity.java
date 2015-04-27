package com.lyh.lifenoteapplication.activity;

import com.lyh.lifenoteapplication.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

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
        mListView.addHeaderView(mLayoutInflater.inflate(R.layout.advertisement_view, null));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private final BaseExpandableListAdapter mListAdapter = new BaseExpandableListAdapter() {

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.group_item, null);
                holder = new ViewHolder();
                holder.groupIcon = (ImageView) convertView.findViewById(R.id.group_icon);
                holder.groupName = (TextView) convertView.findViewById(R.id.group_name);
                holder.groupInfo = (TextView) convertView.findViewById(R.id.group_info);
                holder.groupArrow = (ImageView) convertView.findViewById(R.id.group_arrow);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.groupName.setText("标题" + groupPosition);
            Log.d(TAG, "convertView:" + groupPosition);
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
            return null;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }
    };

    class ViewHolder {
        ImageView groupIcon;
        TextView groupName;
        TextView groupInfo;
        ImageView groupArrow;
    }
}
