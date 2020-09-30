package com.zsmarter.moresettings;

import android.content.Context;
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

    private static final String TAG = "ItemTouchAdapter";
    public IItem iItem;
    private List<User> mUsers;
    private Context context;
    private RecyclerView recyclerView;

    public ItemTouchAdapter(List<User> user, Context context, RecyclerView recyclerView) {
        mUsers = user;
        this.context = context;
        this.recyclerView = recyclerView;

    }

    //此方法就是连接接口与activity的桥梁
    public void setItem(IItem iItem) {
        this.iItem = iItem;
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
        holder.layout.setOnClickListener(v -> {
            iItem.setOnItem(position);
        });
        if (position < 3) {
//            DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
//            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape));
//            recyclerView.addItemDecoration(divider);
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

    /**
     * 在活动中实现的接口
     */
    public interface IItem {
        /**
         * 接口中的方法
         */
        void setOnItem(int position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView sImg;
        private ImageView sDelete;
        private ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.rename);
            sImg = itemView.findViewById(R.id.reImg);
            sDelete = itemView.findViewById(R.id.imgDelete);
            layout = itemView.findViewById(R.id.re_layout);
        }
    }
}
