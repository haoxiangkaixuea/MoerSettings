package com.zsmarter.moresettings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchActivity extends AppCompatActivity {
    private static final String TAG = "ItemTouchActivity";
    private List<User> userList = new ArrayList<>();
    private ItemTouchAdapter itemTouchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);

        initUser();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 13) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        ItemTouchAdapter itemTouchAdapter = new ItemTouchAdapter(userList, ItemTouchActivity.this, recyclerView);
        recyclerView.setAdapter(itemTouchAdapter);
        //设置分割线
        GridDividerItemDecoration decoration = new GridDividerItemDecoration(ItemTouchActivity.this);
        recyclerView.addItemDecoration(decoration);

        //设置间距
        //recyclerView.addItemDecoration(new SpaceItemDecoration(0, 200));
        
        ItemTouchHelp itemTouchCallBack = new ItemTouchHelp(userList, itemTouchAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        //拖拽功能
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initUser() {
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
        User user1 = new User("饿了么", R.drawable.elimo);
        userList.add(user1);
        User user2 = new User("口碑", R.drawable.kobei);
        userList.add(user2);
        User user3 = new User("市民中心", R.drawable.chengshifuwu);
        userList.add(user3);
        User user4 = new User("电影演出", R.drawable.dinayingyanchu);
        userList.add(user4);
        User user5 = new User("转账", R.drawable.zhuanzhan);
        userList.add(user5);
        User user6 = new User("滴滴出行", R.drawable.didichuxing);
        userList.add(user6);
        User user7 = new User("充值中心", R.drawable.chongzhi);
        userList.add(user7);
        User user8 = new User("余额宝", R.drawable.yuebao);
        userList.add(user8);
        User user9 = new User("菜鸟驿站", R.drawable.cainiaoyizhan);
        userList.add(user9);
        User user10 = new User("记账本", R.drawable.jizhangben);
        userList.add(user10);
        User user11 = new User("校园一卡通", R.drawable.yikatong);
        userList.add(user11);
        User user12 = new User("火车机票", R.drawable.huochepiao);
        userList.add(user12);
        User user13 = new User("饿了么", R.drawable.elimo);
        userList.add(user13);
        User user14 = new User("健康码", R.drawable.jinagkangma);
        userList.add(user14);
        User user15 = new User("花呗", R.drawable.huabei);
        userList.add(user15);
        User user16 = new User("芝麻信用", R.drawable.zhimaxingyong);
        userList.add(user16);
        User user17 = new User("酒店出游", R.drawable.jiudian);
        userList.add(user17);
        User user18 = new User("蚂蚁保险", R.drawable.mayibaoxian);
        userList.add(user18);
        User user19 = new User("消费券", R.drawable.xioafeijuan);
        userList.add(user19);
        User user20 = new User("街电", R.drawable.jiedian);
        userList.add(user20);
        User user21 = new User("哈喽出行", R.drawable.hallo);
        userList.add(user21);
        User user22 = new User("怪兽充电", R.drawable.guaishouchongdian);
        userList.add(user22);
    }
}