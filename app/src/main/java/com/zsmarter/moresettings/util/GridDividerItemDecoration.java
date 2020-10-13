package com.zsmarter.moresettings.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsmarter.moresettings.R;
import com.zsmarter.moresettings.constant.Constants;

import java.util.Objects;

/**
 * @author Administrator
 * 分割线
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private static final String TAG = "GridDividerItemDecoration";
    private Drawable mDivider;

    @SuppressLint("LongLogTag")
    public GridDividerItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        //自定义分割线颜色值
        mDivider = AppCompatResources.getDrawable(context, R.drawable.p2main);
        Log.d(TAG, "mDivider" + mDivider);
        a.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    /***
     * 绘制水平分割线
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        //这里固定了，最后一个不用线条
        if (childCount <= 0) {
            return;
        }
        for (int i = Constants.THIRTEEN; i < Constants.FOURTEEN; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 绘制垂直线
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        //这里固定了，最后一个不用线条
        if (childCount >= 0) {
            return;
        }
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            //final int right = child.getRight() + params.leftMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 是否是最后一行
     */
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = Objects.requireNonNull(parent.getAdapter()).getItemCount();
            //总行数
            double count = Math.ceil((double) childCount / (double) spanCount);
            //当前行数
            double currentCount = Math.ceil((double) (itemPosition + 1) / spanCount);

            //最后当前数量小于总的
            return !(currentCount < count);
        }
        return true;
    }

    /**
     * 判断是否是最后一列
     */
    private boolean isLast(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(parent);
            //因为是从0可以所以要将ItemPosition先加1
            return (itemPosition + 1) % spanCount == 0;
        }
        return false;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        int lastCount = Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1;
        if (childAdapterPosition == 0) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        outRect.set(0, 0, 0, 0);
    }
}
