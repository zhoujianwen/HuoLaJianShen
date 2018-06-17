package cn.zhoujianwen.huolajianshen.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import cn.zhoujianwen.huolajianshen.R;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class MeterialHeaderViewHolder extends RecyclerView.ViewHolder {

    public ImageView iv_meterial;

    public MeterialHeaderViewHolder(View itemView) {
        super(itemView);
        iv_meterial = (ImageView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.iv_meterial);
    }
}
