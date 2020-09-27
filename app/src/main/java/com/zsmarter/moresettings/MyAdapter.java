package com.zsmarter.moresettings;

import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mData;
    private ItemTouchHelper mItemHelper;
    private Vibrator mVb;

    public MyAdapter(Context mContext, List<String> mData, ItemTouchHelper itemHelper, Vibrator vb) {
        this.mContext = mContext;
        this.mData = mData;
        this.mItemHelper = itemHelper;
        this.mVb = vb;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, null, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mData.get(position));
        holder.tv.setOnLongClickListener(v -> {
            if (position >= 0) {
                mItemHelper.startDrag(holder);
            }
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
