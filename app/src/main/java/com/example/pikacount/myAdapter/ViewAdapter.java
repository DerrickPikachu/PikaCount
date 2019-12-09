package com.example.pikacount.myAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.pikacount.viewStructure.PageView;

import java.util.ArrayList;

public class ViewAdapter extends PagerAdapter {

    ArrayList<PageView> pageList;
    ArrayList<String> titleName;

    public ViewAdapter (ArrayList<PageView> list, ArrayList<String> title) {
        this.pageList = list;
        this.titleName = title;
    }

    @Override
    public int getCount() {
        return pageList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(pageList.get(position));
        return pageList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleName.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }
}
