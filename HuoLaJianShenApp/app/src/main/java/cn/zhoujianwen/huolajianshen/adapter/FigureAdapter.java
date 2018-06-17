package cn.zhoujianwen.huolajianshen.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class FigureAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"体重","胸围","腰围","左臂围","右臂围","肩宽"};
    private Context context;
    private List<Fragment> fragments;


    public FigureAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
