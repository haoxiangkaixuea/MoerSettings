package com.zsmarter.moresettings;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    private static final String TAG = "RecycleActivity";
    public List<String> mData;
    RecyclerView mRecycleView;
    private MyAdapter mAdapter;

    ItemTouchHelper mItemHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        //设置是否滑动时间，以及拖拽的方向，
        // 如果是列表布局的话则拖拽方向有DOWN和UP，
        // 如果是网格布局的话有DOWN和UP和LEFT和RIGHT4个方向
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            Log.e(TAG, "getMovementFlags()");
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                //拖动
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                //侧滑删除
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }

        //在拖动的过程中不断回调的方法,在这里可以写上一些交换数据的逻辑
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Log.e(TAG, "onMove()");
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            //对原数据进行移动
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mData, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mData, i, i - 1);
                }
            }
            //通知数据移动
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        //拖动完成以后会回调的方法
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.e(TAG, "拖拽完成 方向" + direction);
        }

        //是在选中以后回调的方法
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            Log.e(TAG, "onSelectedChanged()");
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            }
        }

        //是拖放完成以后view被释放的方法
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            Log.e(TAG, "clearView()");
            viewHolder.itemView.setBackgroundColor(0);
        }

        //重写拖拽不可用
        @Override
        public boolean isLongPressDragEnabled() {
            Log.e(TAG, "isLongPressDragEnabled()");
            return false;
        }
    });
    private Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        this.mRecycleView = (RecyclerView) findViewById(R.id.recyView);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        mData = new ArrayList<>();
        mAdapter = new MyAdapter(this, mData, mItemHelper, vb);
        mRecycleView.setAdapter(mAdapter);

        for (int i = 0; i < 100; i++) {
            mData.add("加油" + i);
        }

        mAdapter.notifyDataSetChanged();
        //拖拽功能
        mItemHelper.attachToRecyclerView(mRecycleView);
    }
}