package cn.zhoujianwen.huolajianshen.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.utils.DrawerToolUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.utils.DrawerToolUtils;

public class AutoPlanActivity extends BaseActivity {

    @Bind(cn.zhoujianwen.huolajianshen.R.id.toolbar)
    Toolbar toolbar;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_line_figure)
    TextView tvLineFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_muscle_figure)
    TextView tvMuscleFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_weight_figure)
    TextView tvWeightFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_chest_figure)
    TextView tvChestFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_loin_figure)
    TextView tvLoinFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_left_arm_figure)
    TextView tvLeftArmFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_right_arm_figure)
    TextView tvRightArmFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_shoulder_figure)
    TextView tvShoulderFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_manual_figure)
    TextView tvManualFigure;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.navigation)
    NavigationView navigation;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.zhoujianwen.huolajianshen.R.layout.activity_auto_plan);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    private void initView() {
        DrawerToolUtils.initToolbar(this, toolbar, "生成计划");
        DrawerToolUtils.interactorNavigation(this, toolbar, navigation, drawerLayout);

    }

    private void initListener() {

        tvLineFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.LINE_FIGURE_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvMuscleFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.MUSCLE_FIGURE_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvWeightFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.WEIGHT_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvChestFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.CHEST_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvLoinFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.LOIN_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvLeftArmFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.LEFT_ARM_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvRightArmFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.RIGHT_ARM_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvShoulderFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.SHOULDER_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });
        tvManualFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("actionType", ResultCode.MANUAL_ADD_CODE);
                readyGo(InsertPlanActivity.class, bundle);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        navigation.setCheckedItem(cn.zhoujianwen.huolajianshen.R.id.navigation_new_plan);
    }
}
