package cn.zhoujianwen.huolajianshen.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhoujianwen on 2018/05/01.
 */
public class BodyDataViewHolder extends RecyclerView.ViewHolder {

    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_bmi)
   public TextView tvHeadSuggestBmi;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_bmi_value)
    public  TextView tvHeadSuggestBmiValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_suggest_weight_scope)
    public  TextView tvSuggestWeightScope;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_weight_scope_value)
    public TextView tvHeadSuggestWeightScopeValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_head_suggest_weight_scope)
    public LinearLayout llHeadSuggestWeightScope;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_weight)
    public TextView tvHeadSuggestWeight;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_weight_value)
    public  TextView tvHeadSuggestWeightValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_head_suggest_weight)
    public LinearLayout llHeadSuggestWeight;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_ree)
    public  TextView tvHeadSuggestRee;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_ree_value)
    public  TextView tvHeadSuggestReeValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_head_suggest_ree)
    public  LinearLayout llHeadSuggestRee;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_heart_rate)
    public  TextView tvHeadSuggestHeartRate;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_head_suggest_heart_rate_value)
    public  TextView tvHeadSuggestHeartRateValue;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.ll_head_suggest_heart_rate)
    public  LinearLayout llHeadSuggestHeartRate;

    public BodyDataViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
