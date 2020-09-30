package com.zsmarter.moresettings;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ImgRecycleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RecycleActivity";
    public List<ImgFile> list = new ArrayList<>();
    private ImgItemTouchAdapter mAdapter;
    private Vibrator vb;
    private Button btnSet;
    private boolean isManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        btnSet = findViewById(R.id.btnSet);
        btnSet.setOnClickListener(this);

        if (list != null && list.size() > 0) {
            RecyclerView mRecycleView = (RecyclerView) findViewById(R.id.recyView);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
            mRecycleView.setLayoutManager(gridLayoutManager);
            mAdapter.notifyDataSetChanged();
            /*
             * 使用itemTouchCallBack的时候
             * */
            ImgItemTouchHelper itemTouchCallBack = new ImgItemTouchHelper(list, mAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
            //拖拽功能
            itemTouchHelper.attachToRecyclerView(mRecycleView);
            mAdapter = new ImgItemTouchAdapter(this, list);
            mRecycleView.setAdapter(mAdapter);
        } else {
            assert list != null;
            Log.e(TAG, "加载出错了" + list.size());
        }
        loadUI();
        Log.e(TAG, "loadUI()");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSet:
                isManager = !isManager;
                btnSet.setText(isManager ? "取消" : "管理");
                //为自定义方法--控制另外一个变量
                mAdapter.changeShowDelImage(isManager);
                break;
            default:
        }
    }

    public void loadUI() {
        String[] d = {
                "饿了么", "口碑", "市民中心", "电影演出", "转账",
                "滴滴出行", "充值中心", "余额宝", "菜鸟驿站", "记账本",
                "校园一卡通", "火车机票", "健康码", "花呗", "芝麻信用",
                "酒店出游", "蚂蚁保险", "消费券", "街电",
                "哈喽出行", "怪兽充电"
        };
        int[] drawable = {R.drawable.elimo, R.drawable.kobei, R.drawable.chengshifuwu, R.drawable.dinayingyanchu, R.drawable.zhuanzhan,
                R.drawable.didichuxing, R.drawable.chongzhi, R.drawable.yuebao, R.drawable.cainiaoyizhan, R.drawable.jizhangben,
                R.drawable.yikatong, R.drawable.huochepiao, R.drawable.jinagkangma, R.drawable.huabei, R.drawable.zhimaxingyong,
                R.drawable.jiudian, R.drawable.mayibaoxian, R.drawable.xioafeijuan, R.drawable.jiudian,
                R.drawable.hallo, R.drawable.guaishouchongdian};

        //        for (int i = 0; i < d.length; i++) {
//            for (int j = 0; j < drawable.length; j++) {
//               userList.add(drawable[j],d[i]);
//            }
//        }
//        for (int i = 0; i < drawable.length; i++) {
//            img.setFileSrc(drawable[i]);
//        }
        list.add(new ImgFile("饿了么", R.drawable.elimo, R.drawable.ic_baseline_clear_24));
        list.add(new ImgFile("饿了么", R.drawable.elimo, R.drawable.ic_baseline_clear_24));
    }
}