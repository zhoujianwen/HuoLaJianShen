package cn.zhoujianwen.huolajianshen.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseFragment;
import cn.zhoujianwen.huolajianshen.base.Helper;
import cn.zhoujianwen.huolajianshen.bean.GoalRecordInfo;
import cn.zhoujianwen.huolajianshen.config.API;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.HttpRequest;
import cn.zhoujianwen.huolajianshen.http.HttpRequestListener;
import cn.zhoujianwen.huolajianshen.http.ToastUtil;
import cn.zhoujianwen.huolajianshen.utils.LogUtils;
import cn.zhoujianwen.huolajianshen.utils.RiseNumberTextView;
import cn.zhoujianwen.huolajianshen.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujianwen.huolajianshen.base.BaseFragment;
import cn.zhoujianwen.huolajianshen.base.Helper;
import cn.zhoujianwen.huolajianshen.bean.GoalRecordInfo;
import cn.zhoujianwen.huolajianshen.config.API;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.HttpRequest;
import cn.zhoujianwen.huolajianshen.http.HttpRequestListener;
import cn.zhoujianwen.huolajianshen.http.ToastUtil;
import cn.zhoujianwen.huolajianshen.utils.LogUtils;
import cn.zhoujianwen.huolajianshen.utils.RiseNumberTextView;
import cn.zhoujianwen.huolajianshen.utils.TimeUtil;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class FigureFragment extends BaseFragment {


    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_figure_title)
    TextView tvFigureTitle;

    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_figure_time)
    TextView tvFigureTime;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.btn_figure_complete)
    Button btnFigureComplete;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.rntv_figure_number)
    RiseNumberTextView rntvFigureNumber;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_figure_old_time)
    TextView tvFigureOldTime;

    // 标志位，标志已经初始化完成。isPrepared是为了确保contentView已经加载好
    private boolean isPrepared;

    private ArrayList<Integer> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> options2Items = new ArrayList<>();

    private int type;
    private TimePickerView pvTime;
    private OptionsPickerView pvOptions;
    private float newFigure;
    private long newFigureTime =System.currentTimeMillis();

    public static FigureFragment newInstanceFragment(int type) {
        FigureFragment fragment = new FigureFragment();
        fragment.type = type;
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return cn.zhoujianwen.huolajianshen.R.layout.fragment_figure;
    }

    @Override
    protected void onBindFragment(View view) {
        isPrepared = true;
        lazyLoad();
        LogUtils.e(">>>>>>>>>>>onBindFragment(View view)");

    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared ||!isVisible) {
            return;
        }
        initView();
        initListener();
        fillData();

    }

    private void initView() {

        initOptionTime();
        initOptionFigure();


    }

    private void initOptionFigure() {
        //选项选择器
        pvOptions = new OptionsPickerView(getActivity());
        for (int i = 0;i< 300 ;i++){
            options1Items.add(i);
        }



        ArrayList<Integer> optionsItemsNumber = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            optionsItemsNumber.add(i);
        }
        for (int i =0;i<300;i++){
            options2Items.add(optionsItemsNumber);
        }


        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, true);
        if (type == ResultCode.WEIGHT_CODE){
            pvOptions.setLabels(".", "kg");
        }else {
            pvOptions.setLabels(".", "cm");
        }
        pvOptions.setTitle("添加记录");
        pvOptions.setCyclic(true, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
//        String format = String.format("%.2f", newFigure);
//        String[] split = format.split(".");
//        pvOptions.setSelectOptions(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        pvOptions.setSelectOptions(50, 0);

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

    private void initListener() {

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                newFigure = Float.parseFloat(String.format("%s.%s", options1Items.get(options1), options2Items.get(options1).get(option2)));
                //返回的分别是三个级别的选中位置
                rntvFigureNumber.withNumber(newFigure)
                        .setDuration(3000)
                        .start();
            }
        });
        //点击弹出选项选择器
        if (rntvFigureNumber!=null){
            rntvFigureNumber.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pvOptions.show();
                }
            });
        }




        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                newFigureTime = date.getTime();
                if (date.getTime()> System.currentTimeMillis()){
                    ToastUtil.showToast("您选择的不能超过当前时间");
                    return;
                }

                tvFigureTime.setText(TimeUtil.getTime(date));
            }
        });

        //弹出时间选择器
        if (tvFigureTime != null){
            tvFigureTime.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pvTime.show();
                }
            });
        }



        // 添加记录
        if (btnFigureComplete!=null){
            btnFigureComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitFigure();


                }
            });
        }

    }


    private void fillData() {

        if (tvFigureTime!=null){
            tvFigureTime.setText(TimeUtil.getTime(new Date()));
        }


        String url = String.format(API.FIRST_RECORD_FIGUTR_URI, Helper.getUserId(), type);
        HttpRequest.get(url, new HttpRequestListener() {
                    @Override
                    public void onSuccess(String json) {
                        Gson gson = new Gson();
                        GoalRecordInfo goalRecordInfo = gson.fromJson(json, GoalRecordInfo.class);

                        String typeStr = "体重";
                        int goalRecordType = goalRecordInfo.getGoalRecordType();
                        if (goalRecordType == ResultCode.CHEST_CODE) {
                            typeStr = "胸围";
                        } else if (goalRecordType == ResultCode.LOIN_CODE) {
                            typeStr = "腰围";
                        } else if (goalRecordType == ResultCode.LEFT_ARM_CODE) {
                            typeStr = "左臂围";
                        } else if (goalRecordType == ResultCode.RIGHT_ARM_CODE) {
                            typeStr = "右臂围";
                        } else if (goalRecordType == ResultCode.SHOULDER_CODE) {
                            typeStr = "肩宽";
                        }
                        tvFigureTitle.setText(String.format("您最近一次记录%s", typeStr));

                        long goalRecordTime = goalRecordInfo.getGoalRecordTime();
                        if (goalRecordTime == 0) {
                            tvFigureOldTime.setText(String.format("上次记录时间%s", TimeUtil.timestampToData(goalRecordTime)));

                        } else {
                            tvFigureOldTime.setText("您暂无此记录");
                        }

                        newFigure = goalRecordInfo.getGoalRecordData();
                        rntvFigureNumber.withNumber(newFigure)
                                .setDuration(3000)
                                .start();

                    }
                }

        );

}

    private void submitFigure() {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId",String.valueOf(Helper.getUserId()));
        map.put("authToken",Helper.getToken());
        map.put("goalRecordTime",String.valueOf(newFigureTime));
        map.put("goalRecordType",String.valueOf(type));
        map.put("goalRecordData",String.valueOf(newFigure));

        HttpRequest.post(API.SUBMIT_FIGUTR_URI, map, new HttpRequestListener() {
            @Override
            public void onSuccess(String json) {
                ToastUtil.showToast("记录添加成功");
//               int modifyStatus = JsonUtil.getIntJsonValueByKey(json, "modifyStatus");
//                if (modifyStatus == ResultCode.MODIFY_SUCCESS){
//
//                }
            }
        });
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
