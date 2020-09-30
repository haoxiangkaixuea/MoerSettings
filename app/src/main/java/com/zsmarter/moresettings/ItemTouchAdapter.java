package com.zsmarter.moresettings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/09/29
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
public class ItemTouchAdapter extends RecyclerView.Adapter<ItemTouchAdapter.ViewHolder> {

    private List<User> mUsers;
    private String TAG = "ItemTouchAdapter";

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
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView sImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.rename);
            sImg = itemView.findViewById(R.id.reImg);
        }
    }
}
