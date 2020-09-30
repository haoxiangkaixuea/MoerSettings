package com.zsmarter.moresettings;

import android.graphics.Color;
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
public class ImgItemTouchHelper extends ItemTouchHelper.Callback {
    private static final String TAG = "ImgItemTouchHelper";
    public List<ImgFile> list;
    private ImgAdapter mAdapter;

    public ImgItemTouchHelper(List<ImgFile> list, ImgAdapter mAdapter) {
        this.list = list;
        this.mAdapter = mAdapter;
    }


    /**
     * 设置是否滑动，以及拖拽的方向，如果是列表布局的话则拖拽方向有DOWN和UP，如果是网格布局的话有DOWN和UP和LEFT和RIGHT4个方向
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        Log.e(TAG, "getMovementFlags()");
        int itemViewType = viewHolder.getAdapterPosition();
        int dragFag = 0;

        //item12不拖动
        if (itemViewType == 14 && itemViewType == 0 && itemViewType == 1 && itemViewType == 2) {
            dragFag = 0;
        } else {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
        }
        return makeMovementFlags(dragFag, 0);
//            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
//                //拖动
//                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
//                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//                //侧滑删除
//                final int swipeFlags = 0;
//                return makeMovementFlags(dragFlags, swipeFlags);
//            } else {
//                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//                final int swipeFlags = 0;
//                return makeMovementFlags(dragFlags, swipeFlags);
//            }
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
        if ((fromPosition > 2 && toPosition > 2) && (fromPosition != 14 && toPosition != 14)) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(list, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(list, i, i - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    /**
     * 拖动完成以后会回调的方法,侧滑删除时可用
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e(TAG, "拖拽完成 方向" + direction);
    }

    /**
     * 是在选中以后回调的方法
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.e(TAG, "onSelectedChanged()");
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffffff"));
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
