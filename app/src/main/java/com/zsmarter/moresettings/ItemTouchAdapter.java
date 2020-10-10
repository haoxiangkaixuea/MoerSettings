package com.zsmarter.moresettings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_USER = 0;
    private static final int TYPE_SPLIT = 1;
    private List<User> mUsers = new ArrayList<>();
    private boolean isShow;
    private Context context;

    public ItemTouchAdapter(Context context) {
        this.context = context;
    }

    /**
     * 保存数据
     *
     * @param users 数据列表
     */
    public void setData(List<User> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_USER) {
            View view = LayoutInflater.from(context).inflate(R.layout.items_touch, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new SplitViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_USER) {
            User user = mUsers.get(position);
            ((ViewHolder) holder).tvName.setText(user.getName());
            ((ViewHolder) holder).sImg.setImageResource(user.getImg());

            ((ViewHolder) holder).sDelete.setImageResource(R.drawable.ic_baseline_clear_24);
            ((ViewHolder) holder).sDelete.setOnClickListener(view -> remove(position));

            if (isShow) {
                ((ViewHolder) holder).sDelete.setVisibility(View.VISIBLE);
            } else {
                ((ViewHolder) holder).sDelete.setVisibility(View.INVISIBLE);
            }

            if (position < Constants.THERE) {
                ((ViewHolder) holder).sDelete.setVisibility(View.GONE);
            }
        } else {
            ((SplitViewHolder) holder).tvSplit.setText(R.string.text_split);
        }
    }

    public void remove(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Constants.THIRTEEN) {
            return TYPE_SPLIT;
        } else {
            return TYPE_USER;
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    /**
     * 改变显示删除的imageView，通过定义变量isShow去接收变量isManager
     */
    public void changeShowDelImage(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView sImg;
        private ImageView sDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.rename);
            sImg = itemView.findViewById(R.id.reImg);
            sDelete = itemView.findViewById(R.id.imgDelete);
        }
    }

    static class SplitViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSplit;

        public SplitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSplit = itemView.findViewById(R.id.s_rename);
        }
    }
}
