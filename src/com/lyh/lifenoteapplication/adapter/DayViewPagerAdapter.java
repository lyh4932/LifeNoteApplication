package com.lyh.lifenoteapplication.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class DayViewPagerAdapter extends PagerAdapter {

    private List<View> mListViews;

    public DayViewPagerAdapter(List<View> listViews) {
        this.mListViews = listViews;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));// 删除页卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) { // 这种方法用来实例化页卡
        container.addView(mListViews.get(position), 0);
        return mListViews.get(position);
    }
    @Override
    public int getCount() {
        return mListViews.size();// 返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

}
