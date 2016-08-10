package com.itheima.recycleview.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.recycleview.R;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class HeaderAndFootView {
    private static HeaderAndFootView mHeaderAndFootView;
    // Footer View
    private ProgressBar mFooterProgressBar;
    private TextView mFooterTextView;
    private ImageView mFooterImageView;
    private View mFooterView;

    // Header View
    private ProgressBar mHeadProgressBar;
    private TextView mHeadTextView;
    private ImageView mHeadImageView;
    private View mHeaderView;

    private OnFooterClickListener mListener;

    private HeaderAndFootView() {
    }

    public static HeaderAndFootView getInstance() {
        if (mHeaderAndFootView == null) {
            synchronized (HeaderAndFootView.class) {
                if (mHeaderAndFootView == null) {
                    mHeaderAndFootView = new HeaderAndFootView();
                }
            }
        }
        return mHeaderAndFootView;
    }

    public View createFooterView(Context context) {
        mFooterView = LayoutInflater.from(context).inflate(R.layout.layout_footer, null);
        mFooterView.setClickable(false);
        mFooterProgressBar = (ProgressBar) mFooterView.findViewById(R.id.footer_pb_view);
        mFooterImageView = (ImageView) mFooterView.findViewById(R.id.footer_image_view);
        mFooterTextView = (TextView) mFooterView.findViewById(R.id.footer_text_view);
        mFooterProgressBar.setVisibility(View.GONE);
        mFooterImageView.setVisibility(View.VISIBLE);
        mFooterImageView.setImageResource(R.mipmap.down_arrow);
        mFooterTextView.setText("上拉加载更多...");
        return mFooterView;
    }

    public View createHeaderView(Context context) {
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.layout_head, null);
        mHeadProgressBar = (ProgressBar) mHeaderView.findViewById(R.id.pb_view);
        mHeadTextView = (TextView) mHeaderView.findViewById(R.id.text_view);
        mHeadTextView.setText("下拉刷新");
        mHeadImageView = (ImageView) mHeaderView.findViewById(R.id.image_view);
        mHeadImageView.setVisibility(View.VISIBLE);
        mHeadImageView.setImageResource(R.mipmap.down_arrow);
        mHeadProgressBar.setVisibility(View.GONE);
        return mHeaderView;
    }

    /**
     * 设置刷新中的头布局
     */
    public void setRefreshingHeader() {
        mHeadTextView.setText("正在刷新");
        mHeadImageView.setVisibility(View.GONE);
        mHeadProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 设置拖动过程中的头布局
     *
     * @param enable 是否可以拖动
     */
    public void setScrollingHeader(boolean enable) {
        mHeadTextView.setText(enable ? "松开刷新" : "下拉刷新");
        mHeadImageView.setVisibility(View.VISIBLE);
        mHeadImageView.setRotation(enable ? 180 : 0);
    }

    /**
     * 设置刷新结束后的头布局
     */
    public void setFinishedHeader() {
        mHeadProgressBar.setVisibility(View.GONE);
    }

    /**
     * 设置正在刷新的脚布局
     */
    public void setLoadingFooter() {
        mFooterView.setClickable(false);
        mFooterTextView.setText("正在加载...");
        mFooterImageView.setVisibility(View.GONE);
        mFooterProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 设置拖动中的脚布局
     *
     * @param enable
     */
    public void setScrollingFooter(boolean enable) {
        mFooterView.setClickable(false);
        mFooterTextView.setText(enable ? "松开刷新" : "上拉加载更多");
        mFooterImageView.setVisibility(View.VISIBLE);
        mFooterImageView.setRotation(enable ? 0 : 180);
    }

    /**
     * 设置刷新结束后的脚布局
     */
    public void setFinishedFooter() {
        mFooterView.setClickable(false);
        mFooterProgressBar.setVisibility(View.GONE);
    }

    /**
     * 设置加载更多失败后的脚布局
     */
    public void setErrorFooter() {
        mFooterView.setClickable(true);                     //设置脚布局可以点击
        mFooterTextView.setText("加载失败,点击重试");
        mFooterImageView.setVisibility(View.GONE);
        mFooterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFooterClick();
            }
        });
    }

    /**
     * 对外暴露接口
     */
    public interface OnFooterClickListener {
        void onFooterClick();
    }

    public void setOnFooterClickListener(OnFooterClickListener listener) {
        mListener = listener;
    }
}
