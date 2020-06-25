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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.News;
import com.zyflool.coronatracker.util.AppExecutors;
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
        Log.e("zhongyifan","getRumors finish");
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
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

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
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NewsViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
            holder.mSourceTv.setText(mNewsList.get(position).getInfoSource());
            holder.mSummaryTv.setText(mNewsList.get(position).getSummary());
            holder.mTitleTv.setText(mNewsList.get(position).getTitle());
            String time = mNewsList.get(position).getPubDate();
            time = time.substring(0, time.length()-3);
            holder.mTimeTv.setText(Utils.paserTime(Long.parseLong(time)).substring(0,10));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("即将打开手机上的浏览器查看新闻详细内容，请问您确认要打开浏览器吗？");
                    //点击对话框以外的区域是否让对话框消失
                    builder.setCancelable(true);
                    //设置正面按钮
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
                    //设置反面按钮
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();

                    //显示对话框
                    dialog.show();

                }
            });
        }

        @Override
        public int getItemCount() {
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
