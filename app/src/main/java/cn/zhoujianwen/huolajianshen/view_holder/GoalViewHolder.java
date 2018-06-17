package cn.zhoujianwen.huolajianshen.view_holder;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.zhoujianwen.huolajianshen.R;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class GoalViewHolder extends RecyclerView.ViewHolder {

    public ViewPager vp_home_plan;
    public TabLayout tabs_plan;

    public GoalViewHolder(View itemView) {
        super(itemView);
        vp_home_plan = (ViewPager) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.vp_home_plan);
        tabs_plan = (TabLayout) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.tabs_plan);
    }
}
