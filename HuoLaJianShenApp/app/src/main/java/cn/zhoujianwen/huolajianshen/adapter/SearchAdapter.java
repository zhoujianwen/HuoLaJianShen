package cn.zhoujianwen.huolajianshen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseAdapter;
import cn.zhoujianwen.huolajianshen.bean.Food;
import cn.zhoujianwen.huolajianshen.view_holder.SearchViewHolder;

import java.util.List;


/**
 * * Created by zhoujianwen on 2018/04/01.
 *
 */
public class SearchAdapter extends BaseAdapter<Food, SearchViewHolder> {

    private List<Food> lists;
    private Context context;
    public SearchAdapter(Context context, int layoutResource, List<Food> lists) {
        super(context, layoutResource, lists);
        this.context = context;
        this.lists = lists;
    }

    @Override
    public SearchViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final SearchViewHolder holder, final int position) {
        final Food food = getItem(position);
        if (food == null){
            return;
        }

       holder.tv_calory_cc.setText(String.valueOf(food.getCalory()));
        holder.tv_search_title.setText(food.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnItemClicked(food, holder);
            }
        });

    }






    public interface  OnItemClickListener {
        void OnItemClicked(Food bean,SearchViewHolder holder);
    }



    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}
