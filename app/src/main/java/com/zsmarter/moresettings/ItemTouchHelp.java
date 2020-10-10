package com.zsmarter.moresettings;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchHelp extends ItemTouchHelper.Callback {
    private static final String TAG = "ImgItemTouchHelper";
    public List<User> userList;
    private ItemTouchAdapter mAdapter;
    private boolean isShow;

    public ItemTouchHelp(List<User> userList, ItemTouchAdapter mAdapter) {
        this.userList = userList;
        this.mAdapter = mAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int itemViewType = viewHolder.getAdapterPosition();
        int dragFag = 0;
        if (itemViewType > 2 && itemViewType != 13) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
        }
        return makeMovementFlags(dragFag, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.e(TAG, "onMove()");
        int fromPosition = viewHolder.getAdapterPosition();
        Log.e(TAG, "fromPosition" + fromPosition);
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();
        Log.e(TAG, "toPosition" + toPosition);
        if ((fromPosition > 2 && toPosition > 2)) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    if (i == 13) {
                        continue;
                    }
                    if (i == 12) {
                        Collections.swap(userList, i, i + 2);
                    } else {
                        Collections.swap(userList, i, i + 1);
                    }
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    if (i == 13) {
                        continue;
                    }
                    if (i == 14) {
                        Collections.swap(userList, i, i - 2);
                    } else {
                        Collections.swap(userList, i, i - 1);
                    }
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.e(TAG, "onSelectedChanged()");
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            //viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffffff"));
        }
    }

    /**
     * 是拖放完成以后view被释放的方法
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.e(TAG, "clearView()");
        viewHolder.itemView.setBackgroundColor(0);
        //完成拖动后刷新适配器，这样拖动后删除就不会错乱
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 重写拖拽不可用
     */
    @Override
    public boolean isLongPressDragEnabled() {
        Log.e(TAG, "isLongPressDragEnabled()");
        return true;
    }
}
