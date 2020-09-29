package com.zsmarter.moresettings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14
 *
 * @author Administrator
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {
    private Context mContext;
    private List<ImgFile> mList;
    private boolean isShow;

    public ImgAdapter(Context mContext, List<ImgFile> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ImgFile img = mList.get(position);
        holder.tv.setText(img.getFileName());
        holder.imageView.setImageResource(img.getFileSrc());

        if (isShow) {
            holder.imgDelete.setVisibility(View.INVISIBLE);
        } else {
            holder.imgDelete.setVisibility(View.VISIBLE);
        }

        holder.imgDelete.setImageResource(img.getFileDelete());
        if (position < 3) {
            holder.imgDelete.setVisibility(View.INVISIBLE);
        }
        holder.imgDelete.setOnClickListener(view -> remove(position));
//        holder.tv.setOnLongClickListener(v -> {
//            if (position > 2) {
//                mItemHelper.startDrag(holder);
//            }
//            return false;
//        });
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }

    /**
     * 改变显示删除的imageView，通过定义变量isShow去接收变量isManager
     */
    public void changeShowDelImage(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView imageView;
        private ImageView imgDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imgDelete = itemView.findViewById(R.id.im_delete);
        }
    }
}
