package cn.zhoujianwen.huolajianshen.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.adapter.HomeAdapter;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.base.Helper;
import cn.zhoujianwen.huolajianshen.bean.ConsumeRecordInfo;
import cn.zhoujianwen.huolajianshen.bean.GoalInfo;
import cn.zhoujianwen.huolajianshen.config.API;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.HttpRequest;
import cn.zhoujianwen.huolajianshen.http.HttpRequestListener;
import cn.zhoujianwen.huolajianshen.view.PullLoadMoreRecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujianwen.huolajianshen.adapter.HomeAdapter;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.base.Helper;
import cn.zhoujianwen.huolajianshen.bean.ConsumeRecordInfo;
import cn.zhoujianwen.huolajianshen.bean.GoalInfo;
import cn.zhoujianwen.huolajianshen.config.API;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.HttpRequest;
import cn.zhoujianwen.huolajianshen.http.HttpRequestListener;
import cn.zhoujianwen.huolajianshen.view.PullLoadMoreRecyclerView;

public class HomeActivity extends BaseActivity {


    List<ConsumeRecordInfo> consumeRecords = new ArrayList<>();
    @Bind(cn.zhoujianwen.huolajianshen.R.id.toolbar)
    Toolbar toolbar;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.plmrv_home)
    PullLoadMoreRecyclerView plmrvHome;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.navigation)
    NavigationView navigation;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private HomeAdapter homeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.zhoujianwen.huolajianshen.R.layout.activity_home);
        ButterKnife.bind(this);

        initView();
        initListener();

        fillConsumeRecordData();
        fillGoalData();
//        ShakeListenerUtils shakeListenerUtils = new ShakeListenerUtils(this);
//
//        shakeListenerUtils.setOnShakeListener(new ShakeListenerUtils.onShakeListener() {
//            @Override
//            public void onShake(int i) {
//                ToastUtil.showToast("您抖动了" + i);
//
//            }
//        });

    }

    private void initView() {
        initToolbar();
        initRecyclerView();
        initFab();


    }

    private void initFab() {
        // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(cn.zhoujianwen.huolajianshen.R.drawable.button_action_dark));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(cn.zhoujianwen.huolajianshen.R.mipmap.ic_search_white));
        rlIcon2.setImageDrawable(getResources().getDrawable(cn.zhoujianwen.huolajianshen.R.mipmap.ic_search_white));
        rlIcon3.setImageDrawable(getResources().getDrawable(cn.zhoujianwen.huolajianshen.R.drawable.button_action));
        rlIcon4.setImageDrawable(getResources().getDrawable(cn.zhoujianwen.huolajianshen.R.drawable.button_action_dark_touch));

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                .attachTo(rightLowerButton)
                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });

        rlIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                intent.putExtra("SearchType", ResultCode.FOOD_CODE);
                startActivity(intent);
            }
        });

        rlIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                intent.putExtra("SearchType", ResultCode.SPORTS_CODE);
                startActivity(intent);
            }
        });

        rlIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RecordFigureActivity.class);
                startActivity(intent);
            }
        });


    }


    private void initListener() {


    }


    private void fillGoalData() {

        String url = String.format(API.GET_GOAL_PLAN_URI, Helper.getUserId());
        HttpRequest.get(url, new HttpRequestListener() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<GoalInfo>>(){}.getType();
                List<GoalInfo> tempList = gson.fromJson(json, listType);
                homeAdapter.appendGoalData(tempList);
                homeAdapter.notifyDataSetChanged();


            }
        });

    }


    private void fillConsumeRecordData() {

        homeAdapter.clear();

        loadConsumeRecordData(ResultCode.FIRST_PAGE_CODE);

    }

    private void loadConsumeRecordData(int currentPage) {

        String url = String.format(API.CONSUME_RECORD_URI, Helper.getUserId());
        HttpRequest.get(url, new HttpRequestListener() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ConsumeRecordInfo>>() {
                }.getType();
                consumeRecords = gson.fromJson(json, listType);
                homeAdapter.appendData(consumeRecords);
                homeAdapter.notifyDataSetChanged();
                plmrvHome.setPullLoadMoreCompleted();
            }
        });
    }


    private void initToolbar() {
        toolbar.setTitle("火辣健身");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, cn.zhoujianwen.huolajianshen.R.string.drawer_open,
                cn.zhoujianwen.huolajianshen.R.string.drawer_close);
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
//                Snackbar.make(MainActivity.this, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                Intent intent;
                switch (menuItem.getItemId()) {
                    case cn.zhoujianwen.huolajianshen.R.id.navigation_mian:
                        intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case cn.zhoujianwen.huolajianshen.R.id.navigation_new_plan:
                        intent = new Intent(HomeActivity.this, InsertPlanActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case cn.zhoujianwen.huolajianshen.R.id.navigation_plan:
                        intent = new Intent(HomeActivity.this, MyPlanActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case cn.zhoujianwen.huolajianshen.R.id.navigation_record_figure:
                        intent = new Intent(HomeActivity.this, RecordFigureActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case cn.zhoujianwen.huolajianshen.R.id.navigation_search_food:
                        intent = new Intent(HomeActivity.this, SearchActivity.class);
                        intent.putExtra("SearchType", ResultCode.FOOD_CODE);
                        startActivity(intent);
                        break;

                    case cn.zhoujianwen.huolajianshen.R.id.navigation_search_sport:
                        intent = new Intent(HomeActivity.this, SearchActivity.class);
                        intent.putExtra("SearchType", ResultCode.SPORTS_CODE);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    int currentPage = 1;

    private void initRecyclerView() {
        homeAdapter = new HomeAdapter(this, cn.zhoujianwen.huolajianshen.R.layout.item_plan, cn.zhoujianwen.huolajianshen.R.layout.item_consume_record, consumeRecords);
        plmrvHome.setAdapter(homeAdapter);
        plmrvHome.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                fillConsumeRecordData();
            }

            @Override
            public void onLoadMore() {

                loadConsumeRecordData(++currentPage);

            }
        });
        plmrvHome.setLinearLayout();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
