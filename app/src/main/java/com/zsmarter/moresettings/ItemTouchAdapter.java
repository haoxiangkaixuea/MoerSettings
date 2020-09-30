package com.zsmarter.moresettings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchAdapter extends RecyclerView.Adapter<ItemTouchAdapter.ViewHolder> {

    private List<User> mUsers;

    public ItemTouchAdapter(List<User> user) {
        mUsers = user;

    }

    @NonNull
    @Override
    public ItemTouchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_touch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemTouchAdapter.ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.tvName.setText(user.getName());
        holder.sImg.setImageResource(user.getImg());
        holder.sDelete.setImageResource(R.drawable.ic_baseline_clear_24);
        holder.sDelete.setOnClickListener(view -> remove(position));

        if (position < 3) {
            holder.sDelete.setVisibility(View.INVISIBLE);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView sImg;
        private ImageView sDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.rename);
            sImg = itemView.findViewById(R.id.reImg);
            sDelete = itemView.findViewById(R.id.imgDelete);
            ConstraintLayout layout = itemView.findViewById(R.id.re_layout);
        }
    }
}
