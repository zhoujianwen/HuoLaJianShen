package cn.zhoujianwen.huolajianshen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class MaterialPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;
    public MaterialPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "健康指数";
        }
            return "计划 "+ position;
    }


}
