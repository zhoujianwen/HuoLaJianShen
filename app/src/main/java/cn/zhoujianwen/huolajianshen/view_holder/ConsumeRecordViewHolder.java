package cn.zhoujianwen.huolajianshen.view_holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;


/**
 * @author 作者 zhoujianwen E-mail: zhoujianwenai@foxmail.com
 * @version 创建时间：2018年05月13日 类说明
 */

public class ConsumeRecordViewHolder extends RecyclerView.ViewHolder{

	public TextView tv_cr_time;
	public TextView tv_consume_cc;
	public LinearLayout ll_consume_content;


	public ConsumeRecordViewHolder(View itemView) {
		super(itemView);
		tv_cr_time = (TextView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.tv_cr_time);
		tv_consume_cc = (TextView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.tv_consume_cc);
		ll_consume_content = (LinearLayout) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.ll_consume_content);

	}

	
}
