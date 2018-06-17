package cn.zhoujianwen.huolajianshen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.adapter.PlanAdapter;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.base.Helper;
import cn.zhoujianwen.huolajianshen.bean.GoalInfo;
import cn.zhoujianwen.huolajianshen.config.API;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.HttpRequest;
import cn.zhoujianwen.huolajianshen.http.HttpRequestListener;
import cn.zhoujianwen.huolajianshen.utils.DrawerToolUtils;
import cn.zhoujianwen.huolajianshen.view.PullLoadMoreRecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyPlanActivity extends BaseActivity {


    List<GoalInfo> goalLists = new ArrayList<>();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.plmrv_my_plan)
    PullLoadMoreRecyclerView plmrvMyPlan;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.fab_my_plan)
    FloatingActionButton fabMyPlan;
    private PlanAdapter planAdapter;

    private int mScrollOffset = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        ButterKnife.bind(this);

        initView();
        initListener();
        fillData();
    }


    private void initView() {
        DrawerToolUtils.initToolbar(this, toolbar, "我的计划");
        navigation.setCheckedItem(R.id.navigation_plan);
        DrawerToolUtils.interactorNavigation(this, toolbar, navigation, drawerLayout);
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        navigation.setCheckedItem(R.id.navigation_plan);
        super.onStart();
    }

    private void initListener() {

        fabMyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPlanActivity.this, InsertPlanActivity.class);
                startActivity(intent);
            }
        });
    }


    private void fillData() {

        String url = String.format(API.GET_GOAL_PLAN_URI, Helper.getUserId(), ResultCode.ALL_GOAL_RECORED_CODE);
        HttpRequest.get(url, new HttpRequestListener() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<GoalInfo>>() {
                }.getType();
                List<GoalInfo> tempList = gson.fromJson(json, listType);
                planAdapter.appendData(tempList);
                planAdapter.notifyDataSetChanged();
                plmrvMyPlan.setPullLoadMoreCompleted();
            }
        });
    }


    private void initRecyclerView() {

        planAdapter = new PlanAdapter(this, R.layout.item_plan, goalLists);
        plmrvMyPlan.setAdapter(planAdapter);
        plmrvMyPlan.setLinearLayout();
        plmrvMyPlan.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > mScrollOffset) {
                    if (dy > 0) {
                        fabMyPlan.hide(true);
                    } else {
                        fabMyPlan.show(true);
                    }
                }
            }
        });
    }


}
