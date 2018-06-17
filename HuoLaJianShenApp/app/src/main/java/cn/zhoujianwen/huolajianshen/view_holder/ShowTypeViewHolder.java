package cn.zhoujianwen.huolajianshen.view_holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;


/**
 * @author 作者 zhoujianwen E-mail: zhoujianwenai@foxmail.com
 * @version 创建时间：2018年05月13日 类说明
 */

public class ShowTypeViewHolder extends RecyclerView.ViewHolder{

	public TextView btn_select_type;

	
	public ShowTypeViewHolder(View itemView) {
		super(itemView);
		btn_select_type = (TextView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.btn_select_type);

	}


		
		
	
}
