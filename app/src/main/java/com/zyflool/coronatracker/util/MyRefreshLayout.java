package com.zyflool.coronatracker.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zyflool.coronatracker.R;

public class MyRefreshLayout extends SwipeRefreshLayout {

    private OnLoadMoreListener mLoadMoreListener;
    private RecyclerView mListView;
    private float mDownY, mUpY;
    private boolean isLoading;
    private View mFooter;
    private int mScaledTouchSlop;


    public MyRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mFooter = View.inflate(getContext(), R.layout.item_list_footer, null);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (canLoadMore()) {
                    loadData();
                }
                break;
            case MotionEvent.ACTION_UP:
                mUpY = getY();

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void loadData() {
        if (mLoadMoreListener != null && !isLoading) {
            setLoading(true);
            mLoadMoreListener.onLoadMore();
        }
    }

    private boolean canLoadMore() {
        try {
            mListView = (RecyclerView) getChildAt(0);
            //RefreshLayout 由两部分组成一个是circle 一个是list， 有时候list切换过快list会还没加载出来
        } catch (Exception e) {
            return false;
        }

        //1. 上拉
        boolean condition1 = (mDownY - mUpY) >= mScaledTouchSlop;
        if (condition1) {
            Log.e("SwipeRefreshLayout--->", "上拉.....");
        }

        //2. 最后一条
        boolean condition2 = false;
        if (mListView != null && mListView.getAdapter() != null) {
            LinearLayoutManager manager = (LinearLayoutManager) mListView.getLayoutManager();

            condition2 = manager.findLastVisibleItemPosition() >= (mListView.getAdapter().getItemCount() - 3);

        }
        if (condition2) {
            Log.e("SwipeRefreshLayout--->", "最后一条可见");
        }


        //3. 不是正在刷新
        boolean condition3 = !isLoading;
        if (condition3) {
            Log.e("SwipeRefreshLayout--->", "不是正在刷新");
        }

        return condition1 & condition2 & condition3;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        mDownY = 0;
        mUpY = 0;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
