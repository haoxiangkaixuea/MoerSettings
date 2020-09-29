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
 */
public class ItemTouchAdapter extends RecyclerView.Adapter<ItemTouchAdapter.ViewHolder> {

    private List<User> mUsers;

    public ItemTouchAdapter(List<User> user) {
        mUsers = user;
    }

    @NonNull
    @Override
    public ItemTouchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_touch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTouchAdapter.ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.name.setText(user.getName());
        holder.sImg.setImageResource(user.getImg());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView sImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentname);
            sImg = itemView.findViewById(R.id.studentimage);
        }
    }
}
