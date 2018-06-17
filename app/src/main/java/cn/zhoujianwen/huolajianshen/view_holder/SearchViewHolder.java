package cn.zhoujianwen.huolajianshen.view_holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;


/**
 * @author 作者 zhoujianwen E-mail: zhoujianwenai@foxmail.com
 * @version 创建时间：2018年05月13日 类说明
 */

public class SearchViewHolder extends RecyclerView.ViewHolder{


	public TextView tv_search_title;
	public TextView tv_calory_cc;
	public ImageView iv_add_search;


	public SearchViewHolder(View itemView) {
		super(itemView);
		tv_search_title = (TextView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.tv_search_title);
		tv_calory_cc = (TextView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.tv_calory_cc);
		iv_add_search = (ImageView) itemView.findViewById(cn.zhoujianwen.huolajianshen.R.id.iv_add_search);

	}


		
		
	
}
