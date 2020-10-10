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
public class ItemTouchAdapter extends RecyclerView.Adapter<ItemTouchAdapter.ViewHolder> {

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
    public ItemTouchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_touch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemTouchAdapter.ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.tvName.setText(user.getName());
        holder.sImg.setImageResource(user.getImg());

        holder.sDelete.setImageResource(R.drawable.ic_baseline_clear_24);
        holder.sDelete.setOnClickListener(view -> remove(position));

        if (isShow) {
            holder.sDelete.setVisibility(View.VISIBLE);
        } else {
            holder.sDelete.setVisibility(View.INVISIBLE);
        }

        if (position < Constants.THERE) {
            holder.sDelete.setVisibility(View.GONE);
        }
    }

    public void remove(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
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
}
