package com.zsmarter.moresettings;

import android.os.Bundle;
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
public class ItemTouchActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ItemTouchActivity";
    private List<User> userList = new ArrayList<>();
    private Boolean isManager = false;
    private Button btnSet;
    private ItemTouchAdapter itemTouchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);

        btnSet = findViewById(R.id.set);
        btnSet.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == Constants.THIRTEEN) {
                    return Constants.FIVE;
                } else {
                    return Constants.ONE;
                }
            }
        });
        itemTouchAdapter = new ItemTouchAdapter(this);
        recyclerView.setAdapter(itemTouchAdapter);

        initUser();

        ItemTouchHelp itemTouchCallBack = new ItemTouchHelp(userList, itemTouchAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        //拖拽功能
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initUser() {
        userList = DataUtils.getData(DataUtils.DEFAULT_SP_NAME, "userList", this);
        if (userList.size() == 0) {
            userList.add(new User("饿了么", R.drawable.elimo));
            userList.add(new User("口碑", R.drawable.kobei));
            userList.add(new User("市民中心", R.drawable.chengshifuwu));
            userList.add(new User("电影演出", R.drawable.dinayingyanchu));
            userList.add(new User("转账", R.drawable.zhuanzhan));
            userList.add(new User("滴滴出行", R.drawable.didichuxing));
            userList.add(new User("充值中心", R.drawable.chongzhi));
            userList.add(new User("余额宝", R.drawable.yuebao));
            userList.add(new User("菜鸟驿站", R.drawable.cainiaoyizhan));
            userList.add(new User("记账本", R.drawable.jizhangben));
            userList.add(new User("校园一卡通", R.drawable.yikatong));
            userList.add(new User("火车机票", R.drawable.huochepiao));
            userList.add(new User("饿了么", R.drawable.elimo));
            userList.add(new User("健康码", R.drawable.jinagkangma));
            userList.add(new User("花呗", R.drawable.huabei));
            userList.add(new User("芝麻信用", R.drawable.zhimaxingyong));
            userList.add(new User("酒店出游", R.drawable.jiudian));
            userList.add(new User("蚂蚁保险", R.drawable.mayibaoxian));
            userList.add(new User("消费券", R.drawable.xioafeijuan));
            userList.add(new User("街电", R.drawable.jiedian));
            userList.add(new User("哈喽出行", R.drawable.hallo));
            userList.add(new User("怪兽充电", R.drawable.guaishouchongdian));
        }
        Log.d(TAG, "userList==" + userList);
        itemTouchAdapter.setData(userList);
        itemTouchAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        //销毁前存储数据
        DataUtils.saveData(userList, DataUtils.DEFAULT_SP_NAME, "userList", this);
        super.onDestroy();
        Log.d(TAG, "userList==" + userList);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.set) {
            isManager = !isManager;
            btnSet.setText(isManager ? "取消" : "管理");
            //为自定义方法--控制另外一个变量
            Log.d(TAG, "isManager==  " + isManager);
            itemTouchAdapter.changeShowDelImage(isManager);
        }
    }
}