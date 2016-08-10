package com.itheima.recycleview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.recycleview.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.iv_file)
    ImageView iv_file;

    @InjectView(R.id.tv_content)
    TextView tv_content;

    public RecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    /**
     * 该方法给控件设置数据
     * @param data
     */
    public void bindView(String data) {
        tv_content.setText(data);
    }
}
