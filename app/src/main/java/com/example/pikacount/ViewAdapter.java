package com.example.pikacount;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.pikacount.viewStructure.PageView;

import java.util.ArrayList;

public class ViewAdapter extends PagerAdapter {
    private ArrayList<String> tabName;
    private ArrayList<PageView> appViews;

    public ViewAdapter(ArrayList<PageView> viewList, ArrayList<String> names) {
        appViews = viewList;
        tabName = names;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public View instantiateItem(ViewGroup container, int position) {
        View view = appViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return appViews.size();
    }

    public CharSequence getPageTitle(int position) {
        return tabName.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
