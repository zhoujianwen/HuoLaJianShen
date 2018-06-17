package cn.zhoujianwen.huolajianshen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.activity.ModifyTextActivity;
import cn.zhoujianwen.huolajianshen.base.BaseFragment;
import cn.zhoujianwen.huolajianshen.bean.FigureType;
import cn.zhoujianwen.huolajianshen.bean.GoalInfo;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.ToastUtil;
import cn.zhoujianwen.huolajianshen.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujianwen.huolajianshen.base.BaseFragment;
import cn.zhoujianwen.huolajianshen.bean.FigureType;
import cn.zhoujianwen.huolajianshen.bean.GoalInfo;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.ToastUtil;
import cn.zhoujianwen.huolajianshen.utils.TimeUtil;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class InsertPlanFragment extends BaseFragment {


    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_choose_type)
    TextView tvChooseType;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_choose_goal_value)
    TextView tvChooseGoalValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_choose_current_value)
    TextView tvChooseCurrentValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_choose_goal_time)
    TextView tvChooseGoalTime;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_choose_current_time)
    TextView tvChooseCurrentTime;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_choose_describe)
    TextView tvChooseDescribe;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_choose_type)
    LinearLayout llChooseType;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_choose_goal_value)
    LinearLayout llChooseGoalValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_choose_current_value)
    LinearLayout llChooseCurrentValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_choose_goal_time)
    LinearLayout llChooseGoalTime;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_choose_current_time)
    LinearLayout llChooseCurrentTime;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_choose_describe)
    LinearLayout llChooseDescribe;

    private TimePickerView pvTime;
    private OptionsPickerView pvOptions;
    private OptionsPickerView pvOptionsType;

    private boolean isGoalTime = true;
    private boolean isGoalValue = true;

    private ArrayList<Integer> options1Items = new ArrayList<>();
    private ArrayList<FigureType> options1ItemTypes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> options2Items = new ArrayList<>();

    public GoalInfo goalInfo = new GoalInfo();
    private TimePickerView pvGoalTime;


    @Override
    protected int getLayoutResource() {
        return cn.zhoujianwen.huolajianshen.R.layout.fragment_insert_plan;
    }

    @Override
    protected void onBindFragment(View view) {


        initView();
        initlistener();
        fillData();
    }

    @Override
    protected void lazyLoad() {

    }


    private void initView() {
        initOptionFigure();
        initOptionTime();
        initOptionGoalTime();
        initOptionPlanType();
    }


    private void initlistener() {

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                float value = Float.parseFloat(String.format("%s.%s", options1Items.get(options1), options2Items.get(options1).get(option2)));
                if (isGoalValue) {
                    goalInfo.setStopGoal(value);
                    tvChooseGoalValue.setText(String.valueOf(value) + "kg");
                } else {
                    goalInfo.setStartGoal(value);
                    tvChooseCurrentValue.setText(String.valueOf(value) + "kg");
                }

                ToastUtil.showToast("目标完成");
            }
        });
        //点击弹出选项选择器
        llChooseGoalValue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isGoalValue = true;
                pvOptions.show();
            }
        });

        //点击弹出选项选择器
        llChooseCurrentValue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isGoalValue = false;
                pvOptions.show();
            }
        });


        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {


                goalInfo.setStartTime(date.getTime());
                tvChooseCurrentTime.setText(TimeUtil.getTime(date));
            }
        });

        pvGoalTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                if (date.getTime() < System.currentTimeMillis() + 1000 * 60 * 10) {
                    ToastUtil.showToast("目标时间不能小于当前时间");
                    return;
                }
                goalInfo.setStopTime(date.getTime());
                tvChooseGoalTime.setText(TimeUtil.getTime(date));

            }
        });

        //弹出时间选择器
        llChooseGoalTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isGoalTime = true;
                pvGoalTime.show();
            }
        });

        //弹出时间选择器
        llChooseCurrentTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isGoalTime = false;
                pvTime.show();
            }
        });

        pvOptionsType.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                FigureType figureType = options1ItemTypes.get(options1);
                int type = figureType.getFigureType();
                goalInfo.setGoalType(type);
                tvChooseType.setText(figureType.getPickerViewText());
                ToastUtil.showToast("选择类型完成");
            }
        });

        llChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptionsType.show();
            }
        });

        llChooseDescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModifyTextActivity.class);
                startActivityForResult(intent, ResultCode.MODIFY_DESCRIBE);
            }
        });


    }


    // 添加默认数据
    private void fillData() {
        goalInfo.setGoalDescribe("我一定要完成,加油");
        goalInfo.setStopGoal(60);
        goalInfo.setStartGoal(60);
        goalInfo.setStartTime(System.currentTimeMillis());
        goalInfo.setStopTime(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000);
        goalInfo.setGoalType(ResultCode.WEIGHT_CODE);
    }


    private void initOptionFigure() {
        //选项选择器
        pvOptions = new OptionsPickerView(getActivity());
        for (int i = 0; i < 300; i++) {
            options1Items.add(i);
        }

        ArrayList<Integer> optionsItemsNumber = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            optionsItemsNumber.add(i);
        }
        for (int i = 0; i < 300; i++) {
            options2Items.add(optionsItemsNumber);
        }


        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, true);
//        pvOptions.setLabels(".", "cm/kg");
        pvOptions.setTitle("目标值");
        pvOptions.setCyclic(true, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮

        pvOptions.setSelectOptions(50, 0);

    }


    private void initOptionPlanType() {
        //选项选择器
        pvOptionsType = new OptionsPickerView(getActivity());
        options1ItemTypes.add(new FigureType(ResultCode.WEIGHT_CODE, "体重"));
        options1ItemTypes.add(new FigureType(ResultCode.CHEST_CODE, "胸围"));
        options1ItemTypes.add(new FigureType(ResultCode.LOIN_CODE, "腰围"));
        options1ItemTypes.add(new FigureType(ResultCode.LEFT_ARM_CODE, "左臂围"));
        options1ItemTypes.add(new FigureType(ResultCode.RIGHT_ARM_CODE, "右臂围"));
        options1ItemTypes.add(new FigureType(ResultCode.SHOULDER_CODE, "肩宽"));

        //三级联动效果
        pvOptionsType.setPicker(options1ItemTypes);
        pvOptionsType.setTitle("目标类型");
        pvOptionsType.setCyclic(false);
        pvOptionsType.setSelectOptions(0);

    }

    public void setDefaultSelect(int actionType, float goalRecordData) {
        pvOptionsType.setSelectOptions(actionType - 1);
        int defaultValue = 60;
        String defaultName = "体重";
        String unit = "kg";
        switch (actionType) {

            case ResultCode.WEIGHT_CODE:
                defaultValue = 60;
                defaultName = "体重";
                unit = "cm";
                break;

            case ResultCode.CHEST_CODE:
                defaultName = "胸围";
                defaultValue = 75;
                unit = "cm";
                break;
            case ResultCode.LOIN_CODE:
                defaultName = "腰围";
                defaultValue = 75;
                unit = "cm";
                break;
            case ResultCode.LEFT_ARM_CODE:
                defaultName = "左臂围";
                defaultValue = 30;
                unit = "cm";
                break;
            case ResultCode.RIGHT_ARM_CODE:
                defaultName = "右臂围";
                defaultValue = 30;
                unit = "cm";
                break;
            case ResultCode.SHOULDER_CODE:
                defaultName = "肩宽";
                defaultValue = 40;
                unit = "cm";
                break;

        }
        if (goalRecordData > 2) {
            pvOptions.setSelectOptions((int) goalRecordData - 2, 0);
            tvChooseType.setText(defaultName);
            tvChooseCurrentValue.setText(String.format("%s%s", goalRecordData, unit));
            tvChooseGoalValue.setText(String.format("%s%s", goalRecordData - 2, unit));
        } else {


            pvOptions.setSelectOptions(defaultValue, 0);

            tvChooseType.setText(defaultName);
            tvChooseCurrentValue.setText(String.format("%s%s", defaultValue, unit));
            tvChooseGoalValue.setText(String.format("%s%s", defaultValue - 2, unit));
        }

    }


    private void initOptionTime() {
        //时间选择器
//            pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
    }

    private void initOptionGoalTime() {
        //时间选择器
//            pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvGoalTime = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        pvGoalTime.setTime(new Date());
        pvGoalTime.setCyclic(false);
        pvGoalTime.setCancelable(true);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case ResultCode.MODIFY_DESCRIBE:
                String modifyResult = data.getStringExtra("modifyResult");
                tvChooseDescribe.setText(modifyResult);
                goalInfo.setGoalDescribe(modifyResult);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
