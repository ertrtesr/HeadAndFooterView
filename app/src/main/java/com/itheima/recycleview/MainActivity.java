package com.itheima.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.recycleview.adapter.RecyclerAdapter;
import com.itheima.recycleview.widgets.HeaderAndFootView;
import com.itheima.recycleview.widgets.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {
    @InjectView(R.id.swipeRefreshLayout)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;

    @InjectView(R.id.recycleView)
    RecyclerView mRecyclerView;
    private View mHeaderView;
    private View mFooterView;
    private List<String> mList;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        System.out.println("123");
        initDatas();
        initListener();
        mRecyclerAdapter = new RecyclerAdapter(this, mList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mHeaderView = HeaderAndFootView.getInstance().createHeaderView(mSuperSwipeRefreshLayout.getContext());
        mFooterView = HeaderAndFootView.getInstance().createFooterView(mSuperSwipeRefreshLayout.getContext());
        //        mFooterView = HeaderAndFootView.createFooterView(mSuperSwipeRefreshLayout.getContext());
        mSuperSwipeRefreshLayout.setHeaderView(mHeaderView);
        mSuperSwipeRefreshLayout.setFooterView(mFooterView);

        mSuperSwipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                HeaderAndFootView.getInstance().setRefreshingHeader();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mSuperSwipeRefreshLayout.setRefreshing(false);
                        HeaderAndFootView.getInstance().setFinishedHeader();
                    }
                }, 2000);
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                HeaderAndFootView.getInstance().setScrollingHeader(enable);
            }
        });

        mSuperSwipeRefreshLayout.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                HeaderAndFootView.getInstance().setLoadingFooter();
                mList.add("这是新增的数据");
                mRecyclerAdapter.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        HeaderAndFootView.getInstance().setFinishedFooter();
                        mSuperSwipeRefreshLayout.setLoadMore(false);                //移除脚布局
                    }
                }, 2000);
            }

            @Override
            public void onPushDistance(int distance) {

            }

            @Override
            public void onPushEnable(boolean enable) {
                HeaderAndFootView.getInstance().setScrollingFooter(enable);
            }
        });
    }

    private void initListener() {
        HeaderAndFootView.getInstance().setOnFooterClickListener(new HeaderAndFootView.OnFooterClickListener() {
            @Override
            public void onFooterClick() {
                System.out.println("我被点击了");
            }
        });
    }

    private void initDatas() {
        mList = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            mList.add("item " + i);
        }
    }
}
