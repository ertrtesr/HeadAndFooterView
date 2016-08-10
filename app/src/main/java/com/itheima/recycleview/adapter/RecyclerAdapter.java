package com.itheima.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.recycleview.R;
import com.itheima.recycleview.holder.RecyclerHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private Context mContext;
    private List<String> mDataSet;

    public RecyclerAdapter(Context context, List<String> list) {
        mContext = context;
        mDataSet = list;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new RecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.bindView(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
