package com.zyflool.coronatracker.ui.info.news;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.News;
import com.zyflool.coronatracker.util.ListNoItemViewHolder;
import com.zyflool.coronatracker.util.MyRefreshLayout;
import com.zyflool.coronatracker.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements NewsContract.NewsView{

    private NewsContract.NewsPresenter mPresenter;

    private RecyclerView mNewsRv;
    private MyRefreshLayout myRefreshLayout;
    private NewsAdapter mAdapter;

    private MyRefreshLayout.OnLoadMoreListener onLoadMoreListener = new MyRefreshLayout.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            mPresenter.getNews(false);
            myRefreshLayout.setLoading(false);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewsPresenter(this, NewsRepository.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        myRefreshLayout = view.findViewById(R.id.mrl_news);
        myRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNews(true);
                myRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);
            }
        });
        myRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);


        mNewsRv = view.findViewById(R.id.rv_news);
        mNewsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsAdapter(new ArrayList<>());
        mNewsRv.setAdapter(mAdapter);

        mPresenter.getNews(true);

        return view;
    }

    @Override
    public void showNews(List<News> newsList) {
        Log.e("NewsFragment","getRumors finish");
        myRefreshLayout.setRefreshing(false);
        myRefreshLayout.setLoading(false);
        if ( newsList.size() < 20 )
            myRefreshLayout.setOnLoadMoreListener(null);
        mAdapter.refreshNews(newsList);
    }

    @Override
    public void showMoreNews(List<News> newsList) {
        myRefreshLayout.setLoading(false);
        if (newsList.size() < 20)
            myRefreshLayout.setOnLoadMoreListener(null);
        if (newsList.size() != 0)
            mAdapter.addNews(newsList);
    }

    @Override
    public void showError(String error) {
        myRefreshLayout.setRefreshing(false);
        myRefreshLayout.setLoading(false);
    }

    public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<News> mNewsList;

        public NewsAdapter (List<News> newsList) {
            mNewsList = newsList;
        }

        public void addNews(List<News> newsList) {
            int preSize = mNewsList.size();
            mNewsList.addAll(newsList);
            notifyItemRangeInserted(preSize, newsList.size());
        }

        public void refreshNews(List<News> newsList) {
            mNewsList.clear();
            mNewsList.addAll(newsList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if ( viewType == 1 )
                return new ListNoItemViewHolder(
                        LayoutInflater.from(getContext()).inflate(
                                R.layout.item_news, parent, false));
            else
                return new NewsViewHolder(
                        LayoutInflater.from(getContext()).inflate(
                                R.layout.item_news, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
            if ( holder instanceof NewsViewHolder ) {
                NewsViewHolder mHolder = (NewsViewHolder) holder;
                mHolder.mSourceTv.setText(mNewsList.get(position).getInfoSource());
                mHolder.mSummaryTv.setText(mNewsList.get(position).getSummary());
                mHolder.mTitleTv.setText(mNewsList.get(position).getTitle());
                String time = mNewsList.get(position).getPubDate();
                time = time.substring(0, time.length() - 3);
                mHolder.mTimeTv.setText(Utils.paserTime(Long.parseLong(time)).substring(0, 10));

                mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("即将打开手机上的浏览器查看新闻详细内容，请问您确认要打开浏览器吗？");

                        builder.setCancelable(true);

                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = mNewsList.get(position).getSourceUrl();
                                Uri uri = Uri.parse(url);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                try {
                                    getContext().startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    e.printStackTrace();
                                }
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        //设置对话框背景
                        Window window = dialog.getWindow();
                        assert window != null;
                        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg,null));

                        dialog.show();

                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            if ( mNewsList.size() == 0 )
                return 1;
            else return 0;
        }

        @Override
        public int getItemCount() {
            if ( mNewsList.size() == 0 )
                return 1;
            else
                return mNewsList.size();
        }

        public class NewsViewHolder extends RecyclerView.ViewHolder {

            private TextView mTitleTv, mSummaryTv, mSourceTv, mTimeTv;

            public NewsViewHolder(@NonNull View itemView) {
                super(itemView);
                mTimeTv = itemView.findViewById(R.id.tv_info_time);
                mTitleTv = itemView.findViewById(R.id.tv_info_title);
                mSummaryTv = itemView.findViewById(R.id.tv_info_summary);
                mSourceTv = itemView.findViewById(R.id.tv_info_source);
            }
        }
    }

}
