package com.zyflool.coronatracker.ui.info.rumors;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.Rumors;
import com.zyflool.coronatracker.util.ListNoItemViewHolder;
import com.zyflool.coronatracker.util.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RumorsFragment extends Fragment implements RumorsContract.RumorsView{

    private RumorsContract.RumorsPresenter mPresenter;

    private RecyclerView mRumorsRv;
    private MyRefreshLayout myRefreshLayout;
    private RumorsAdapter mAdapter;

    private MyRefreshLayout.OnLoadMoreListener onLoadMoreListener = new MyRefreshLayout.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            mPresenter.getRumors(false);
            myRefreshLayout.setLoading(false);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new RumorsPresenter(this, RumorsRepository.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rumors, container, false);

        myRefreshLayout = view.findViewById(R.id.mrl_rumors);
        myRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getRumors(true);
                myRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);
            }
        });
        myRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);

        mRumorsRv = view.findViewById(R.id.rv_rumors);
        mRumorsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RumorsAdapter(new ArrayList<>());
        mRumorsRv.setAdapter(mAdapter);

        mPresenter.getRumors(true);

        return view;
    }

    @Override
    public void showRumors(List<Rumors> rumorsList) {
        myRefreshLayout.setRefreshing(false);
        myRefreshLayout.setLoading(false);
        Log.e("RumorsFragment","getRumors finish");
        if ( rumorsList.size() < 20 )
            myRefreshLayout.setOnLoadMoreListener(null);
        mAdapter.refreshRumors(rumorsList);
    }

    @Override
    public void showMoreRumors(List<Rumors> rumorsList) {
        myRefreshLayout.setRefreshing(false);
        myRefreshLayout.setLoading(false);
        if ( rumorsList.size() < 20 )
            myRefreshLayout.setOnLoadMoreListener(null);
        if ( rumorsList.size() != 0 )
            mAdapter.addRumors(rumorsList);
    }

    @Override
    public void showError(String error) {
        myRefreshLayout.setRefreshing(false);
        myRefreshLayout.setLoading(false);
    }

    public class RumorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Rumors> mRumorsList;

        public RumorsAdapter (List<Rumors> newsList) {
            mRumorsList = newsList;
        }

        public void addRumors(List<Rumors> rumorsList) {
            int preSize = mRumorsList.size();
            mRumorsList.addAll(rumorsList);
            notifyItemRangeInserted(preSize, rumorsList.size());
        }

        public void refreshRumors(List<Rumors> rumorsList) {
            mRumorsList.clear();
            mRumorsList.addAll(rumorsList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if ( viewType == 1 )
                return new ListNoItemViewHolder(
                        LayoutInflater.from(getContext()).inflate(
                                R.layout.item_no_item, parent, false));
            else
                return new RumorsViewHolder(
                        LayoutInflater.from(getContext()).inflate(
                                R.layout.item_rumors, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
            if ( holder instanceof RumorsViewHolder ) {
                RumorsViewHolder mHolder = (RumorsViewHolder) holder;
                mHolder.mSummaryTv.setText(mRumorsList.get(position).getMainSummary());
                mHolder.mTitleTv.setText(mRumorsList.get(position).getTitle());

                mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage(mRumorsList.get(position).getBody());

                        builder.setCancelable(true);

                        AlertDialog dialog = builder.create();

                        final Window window = dialog.getWindow();
                        assert window != null;
                        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg, null));

                        dialog.show();

                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            if ( mRumorsList.size() == 0 )
                return 1;
            else return 0;
        }

        @Override
        public int getItemCount() {
            if ( mRumorsList.size() == 0 )
                return 1;
            else
                return mRumorsList.size();
        }

        public class RumorsViewHolder extends RecyclerView.ViewHolder {

            private TextView mTitleTv, mSummaryTv;

            public RumorsViewHolder(@NonNull View itemView) {
                super(itemView);
                mTitleTv = itemView.findViewById(R.id.tv_rumors_title);
                mSummaryTv = itemView.findViewById(R.id.tv_rumors_summary);
            }
        }
    }


}
