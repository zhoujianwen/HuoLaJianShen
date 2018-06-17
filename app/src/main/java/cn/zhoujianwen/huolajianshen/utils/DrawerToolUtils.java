package cn.zhoujianwen.huolajianshen.utils;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.activity.AutoPlanActivity;
import cn.zhoujianwen.huolajianshen.activity.ChangeActivity;
import cn.zhoujianwen.huolajianshen.activity.KnowMeActivity;
import cn.zhoujianwen.huolajianshen.activity.MaterialMainActivity;
import cn.zhoujianwen.huolajianshen.activity.ModifyUserActivity;
import cn.zhoujianwen.huolajianshen.activity.MyPlanActivity;
import cn.zhoujianwen.huolajianshen.activity.RecordFigureActivity;
import cn.zhoujianwen.huolajianshen.activity.SearchActivity;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.config.UserConfig;

import cn.zhoujianwen.huolajianshen.activity.ChangeActivity;
import cn.zhoujianwen.huolajianshen.activity.KnowMeActivity;
import cn.zhoujianwen.huolajianshen.activity.MaterialMainActivity;
import cn.zhoujianwen.huolajianshen.activity.MyPlanActivity;
import cn.zhoujianwen.huolajianshen.activity.RecordFigureActivity;
import cn.zhoujianwen.huolajianshen.activity.SearchActivity;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.config.UserConfig;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public  class DrawerToolUtils {


    private static ImageView ivAvatar;

    public static void interactorNavigation(final AppCompatActivity act, Toolbar toolbar, final NavigationView navigation, final DrawerLayout drawerLayout) {


        if (navigation.getHeaderCount() > 0) {
            View headerView = navigation.getHeaderView(0);
            ivAvatar = (ImageView) headerView.findViewById(R.id.iv_avatar);

            displayAvatar();
            TextView tv_name = (TextView) headerView.findViewById(R.id.tv_name);
            TextView tv_describe = (TextView) headerView.findViewById(R.id.tv_describe);
            ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(act, ModifyUserActivity.class);
                    act.startActivity(intent);
                }
            });
        }

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(act, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Intent intent;
                switch (menuItem.getItemId()) {


                    case R.id.navigation_mian:

                        intent = new Intent(act, MaterialMainActivity.class);
                        act.startActivity(intent);
                        act.finish();

                        break;
                    case R.id.navigation_new_plan:

                        intent = new Intent(act, AutoPlanActivity.class);
                        act.startActivity(intent);
                        isFinish();

                        break;

                    case R.id.navigation_plan:
                        intent = new Intent(act, MyPlanActivity.class);
                        act.startActivity(intent);
                        isFinish();
                        break;
                    case R.id.navigation_record_figure:
                        intent = new Intent(act, RecordFigureActivity.class);
                        act.startActivity(intent);
                        isFinish();
                        break;
                    case R.id.navigation_search_food:
                        intent = new Intent(act, SearchActivity.class);
                        intent.putExtra("SearchType", ResultCode.FOOD_CODE);
                        act.startActivity(intent);
                        isFinish();
                        break;

                    case R.id.navigation_search_sport:
                        intent = new Intent(act, SearchActivity.class);
                        intent.putExtra("SearchType", ResultCode.SPORTS_CODE);
                        act.startActivity(intent);
                        isFinish();
                        break;
                    case R.id.navigation_change:
                        intent = new Intent(act, ChangeActivity.class);
                        act.startActivity(intent);
                        isFinish();
                        break;
                    case R.id.navigation_know_me:
                        intent = new Intent(act, KnowMeActivity.class);
                        act.startActivity(intent);
                        isFinish();
                        break;

                }
                drawerLayout.closeDrawers();


                return true;
            }

            // 是否关闭由传入的 Activity 是否不是 MaterialMainActivity的实例决定
            private void isFinish() {

                if (!(act instanceof MaterialMainActivity)){
                    act.finish();
                }

            }
        });
    }

    public static void displayAvatar() {
        int sex = SharedPreferencesUtil.getInstance().getInt(UserConfig.SEX, UserConfig.MAN);
        if (sex == UserConfig.MAN){
            ivAvatar.setImageResource(R.mipmap.male_icon);
        }else {
            ivAvatar.setImageResource(R.mipmap.female_icon);
        }
    }


    public static void initToolbar(final AppCompatActivity act, Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        act.setSupportActionBar(toolbar);
        if (act.getSupportActionBar() != null) {
            act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.onBackPressed();
            }
        });
    }



}
