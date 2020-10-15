package com.zsmarter.moresettings;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsmarter.moresettings.adapetr.ItemTouchAdapter;
import com.zsmarter.moresettings.constant.Constants;
import com.zsmarter.moresettings.data.User;

import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchHelp extends ItemTouchHelper.Callback {
    private static final String TAG = "ImgItemTouchHelper";
    public List<User> userList;
    private ItemTouchAdapter mAdapter;

    public ItemTouchHelp(List<User> userList, ItemTouchAdapter mAdapter) {
        this.userList = userList;
        this.mAdapter = mAdapter;
    }

    /**
     * 设置是否滑动时间，以及拖拽的方向.
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int itemViewType = viewHolder.getAdapterPosition();
        int dragFag = 0;
        if (itemViewType > Constants.TWO && itemViewType != Constants.THIRTEEN) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
        }
        return makeMovementFlags(dragFag, 0);
    }

    /**
     * 在拖动的过程中不断回调的方法,在这里可以写上一些交换数据的逻辑
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.e(TAG, "onMove()");
        int fromPosition = viewHolder.getAdapterPosition();
        Log.e(TAG, "fromPosition" + fromPosition);
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();
        Log.e(TAG, "toPosition" + toPosition);
        if (fromPosition != Constants.THIRTEEN && toPosition != Constants.THIRTEEN) {
            if ((fromPosition > Constants.TWO && toPosition > Constants.TWO)) {
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
        }
        return true;
    }

    /**
     * 拖动完成以后会回调的方法
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e(TAG, "onSwiped()");
    }

    /**
     * 在选中以后回调的方法
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.e(TAG, "onSelectedChanged()");
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
        //延时刷新
        recyclerView.post(() -> mAdapter.notifyDataSetChanged());
    }

    /**
     * 重写是否允许item被拖拽的方法
     */
    @Override
    public boolean isLongPressDragEnabled() {
        Log.e(TAG, "isLongPressDragEnabled()");
        return true;
    }
}
