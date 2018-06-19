package cn.zhoujianwen.huolajianshen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseAdapter;
import cn.zhoujianwen.huolajianshen.bean.ConsumeRecordInfo;
import cn.zhoujianwen.huolajianshen.bean.SlidingDeckModel;
import cn.zhoujianwen.huolajianshen.utils.TimeUtil;
import cn.zhoujianwen.huolajianshen.view_holder.ConsumeRecordViewHolder;

import java.lang.reflect.Type;
import java.util.List;


/**
 * * Created by zhoujianwen on 2018/04/01.
 *
 */
public class ConsumeRecordAdapter extends BaseAdapter<ConsumeRecordInfo, ConsumeRecordViewHolder> {

    private List<ConsumeRecordInfo> lists;
    private Context context;
    public ConsumeRecordAdapter(Context context, int layoutResource, List<ConsumeRecordInfo> consumeRecordInfo) {
        super(context, layoutResource, consumeRecordInfo);
        this.context = context;
        this.lists = consumeRecordInfo;
    }

    @Override
    public ConsumeRecordViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_consume_record, parent, false);
        return new ConsumeRecordViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ConsumeRecordViewHolder holder, final int position) {
        ConsumeRecordInfo consumeRecordInfo = getItem(position);

        holder.tv_cr_time.setText(TimeUtil.timestampToData(consumeRecordInfo.getConsumeRecordTime()));
        holder.tv_consume_cc.setText(String.format("摄入:%s大卡", consumeRecordInfo.getConsumeCC()));

        Gson gson = new Gson();
        Type listType = new TypeToken<List<SlidingDeckModel>>() {}.getType();
        List<SlidingDeckModel> modelList = gson.fromJson(consumeRecordInfo.getConsumeRecordContent(), listType);

        holder.ll_consume_content.removeAllViews();
        for (int i = 0;i< modelList.size();i++){
            TextView textView = new TextView(context);
            textView.setText(String.format("%s : %.2f 大卡",modelList.get(i).getSlidingName(),modelList.get(i).getTotalCC()));
            holder.ll_consume_content.addView(textView);
        }

    }

    public interface  OnItemClickListener {
        void OnItemClicked(ConsumeRecordInfo bean);
    }

    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    };


}
