package com.lyh.lifenoteapplication.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lyh.lifenoteapplication.R;
import com.lyh.lifenoteapplication.adapter.DayViewPagerAdapter;
import com.lyh.lifenoteapplication.util.PageIndicator;
import com.lyh.lifenoteapplication.util.TitlePageIndicator;
import com.lyh.lifenoteapplication.util.UnderlinePageIndicator;

public class MainActivity extends Activity {
    private ArrayList<View> mViewList;
    private ViewPager mPager;
    private UnderlinePageIndicator mIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mPager = (ViewPager) findViewById(R.id.pager);
        mViewList = new ArrayList<View>();
        mViewList.add(getLayoutInflater().inflate(R.layout.activity_ab, null));
        mViewList.add(getLayoutInflater().inflate(R.layout.activity_ab2, null));
        mViewList.add(getLayoutInflater().inflate(R.layout.activity_ab3, null));
        mPager.setAdapter(new DayViewPagerAdapter(mViewList));
        mIndicator.setViewPager(mPager);
//        Button startFloatWindow = (Button) findViewById(R.id.start_float_window);
//        startFloatWindow.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
//                startService(intent);
//                finish();
//            }
//        });
    }
}
