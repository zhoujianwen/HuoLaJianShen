package cn.zhoujianwen.huolajianshen.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseAdapter;
import cn.zhoujianwen.huolajianshen.base.Helper;
import cn.zhoujianwen.huolajianshen.bean.ConsumeRecordInfo;
import cn.zhoujianwen.huolajianshen.bean.GoalInfo;
import cn.zhoujianwen.huolajianshen.config.API;
import cn.zhoujianwen.huolajianshen.config.ResultCode;
import cn.zhoujianwen.huolajianshen.http.HttpRequest;
import cn.zhoujianwen.huolajianshen.http.HttpRequestListener;
import cn.zhoujianwen.huolajianshen.utils.JsonUtil;
import cn.zhoujianwen.huolajianshen.utils.LogUtils;
import cn.zhoujianwen.huolajianshen.utils.TimeUtil;
import cn.zhoujianwen.huolajianshen.view_holder.PlanViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class PlanAdapter extends BaseAdapter<GoalInfo,PlanViewHolder> {


    private List<GoalInfo> lists;
    private Activity context;


    public PlanAdapter(Context context, int layoutResource, List<GoalInfo> consumeRecordInfo) {
        super(context, layoutResource, consumeRecordInfo);
        this.context = (Activity)context;
        this.lists = consumeRecordInfo;
    }

    @Override
    public PlanViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plan, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final PlanViewHolder holder, final int position) {
         GoalInfo item = getItem(position);
        holder.tv_plan_goal_time.setText(TimeUtil.timestampToYMD(item.getStopTime()));

        holder.tv_plan_goal_value.setText(item.getStopGoal()+"cm");
        holder.tv_plan_goal_describe.setText(item.getGoalDescribe());

        String goalStr = "体重";
        String unitStr = "kg";
        int goalType = item.getGoalType();
        switch (goalType){
            case ResultCode.WEIGHT_CODE:
                goalStr = "体重";

                break;
            case ResultCode.CHEST_CODE:
                goalStr = "胸围";
                unitStr = "cm";
                break;
            case ResultCode.LOIN_CODE:
                goalStr = "腰围";
                unitStr = "cm";
                break;
            case ResultCode.LEFT_ARM_CODE:
                goalStr = "左臂围";
                unitStr = "cm";
                break;
            case ResultCode.RIGHT_ARM_CODE:
                goalStr = "右臂围";
                unitStr = "cm";
                break;
            case ResultCode.SHOULDER_CODE:
                goalStr = "肩宽";
                unitStr = "cm";
                break;

        }
        holder.tv_plan_title.setText(String.format("目标%s:",goalStr));
        holder.tv_plan_current_value.setText(String.format("训练前%s:  %s%s", goalStr, item.getStartGoal(), unitStr));
        holder.tv_plan_current_time.setText(String.format("训练开始时间:%s", TimeUtil.timestampToYMD(item.getStartTime())));
        holder.tv_plan_goal_value.setText(String.format("%s%s",item.getStopGoal(),unitStr));

        if (item.getGoalStatus() == ResultCode.MODIFY_SUCCESS){
            holder.iv_plan_stutus.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_search_white));
            holder.iv_plan_stutus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,"此项计划已完成,向型男又迈进了一步",Snackbar.LENGTH_LONG).show();
                }
            });
        }else {
            holder.iv_plan_stutus.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.button_action_dark));
            holder.iv_plan_stutus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,"此项计划已完成?",Snackbar.LENGTH_LONG).setAction("完成", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submitGoalComplte(getItem(position),holder);
                        }
                    }).show();

                }
            });
        }

//        Timer timerValue = new Timer();
//        float totalValue = item.getStopGoal() - item.getStartGoal();
//        float consumeVale = item.get
//        timerValue.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        holder.npb_plan_time.incrementProgressBy(1);
//                        holder.npb_plan_value.incrementProgressBy(1);
//
//                    }
//                });
//            }
//        }, 1000, 100);

        long totalTime = item.getStopTime() - item.getStartTime();
        long consumeTime = System.currentTimeMillis() - item.getStartTime();
        final int scaleTime = (int)(((float)consumeTime /(float)totalTime)*100);
        Timer timerTime = new Timer();
        timerTime.schedule(new TimerTask() {
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (holder.npb_plan_time.getProgress()<=scaleTime){

                            holder.npb_plan_time.incrementProgressBy(1);
                            holder.npb_plan_value.incrementProgressBy(1);
                        }


                    }
                });
            }
        }, 1000, 100);


    }

    private void submitGoalComplte(final GoalInfo item, final PlanViewHolder holder) {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", String.valueOf(Helper.getUserId()));
        map.put("authToken", Helper.getToken());
        map.put("goalId",String.valueOf(item.getGoalId()));

        HttpRequest.post(API.MODIFY_GOAL_COMPLETE_URI, map, new HttpRequestListener() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                int modifyStatus = JsonUtil.getIntJsonValueByKey(json, "modifyStatus");
                if (modifyStatus == ResultCode.MODIFY_SUCCESS){
                    item.setGoalStatus(modifyStatus);
                    notifyDataSetChanged();
                    holder.iv_plan_stutus.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_search_white));
                }
            }
        });

    }


    public interface  OnItemClickListener {
        void OnItemClicked(ConsumeRecordInfo bean);
    }

    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
