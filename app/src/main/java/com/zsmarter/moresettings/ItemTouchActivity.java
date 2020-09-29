package com.zsmarter.moresettings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemTouchActivity extends AppCompatActivity {
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);

        initUser();
        //RecyclerView列表
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        //设置列表的布局方式，使用线性布局
//        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
//        //加载到RecyclerView列表中
//        recyclerView.setLayoutManager(linearLayout);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置ViewAdapter实例，把数据传入到构造函数中
        ItemTouchAdapter itemTouchAdapter = new ItemTouchAdapter(userList);
        //完成适配器设置
        recyclerView.setAdapter(itemTouchAdapter);
    }

    private void initUser() {
        for (int i = 0; i < 2; i++) {
            User zhangsan = new User("张三", R.drawable.ic_launcher_background);
            userList.add(zhangsan);
            User lisi = new User("李四", R.drawable.ic_launcher_background);
            userList.add(lisi);
            User wangwu = new User("王五", R.drawable.ic_launcher_background);
            userList.add(wangwu);
            User liuliu = new User("六六lllll", R.drawable.ic_launcher_background);
            userList.add(liuliu);
            User qiqi = new User("七七", R.drawable.ic_launcher_background);
            userList.add(qiqi);
            User baba = new User("八八", R.drawable.ic_launcher_background);
            userList.add(baba);
            User jiujiu = new User("久久", R.drawable.ic_launcher_background);
            userList.add(jiujiu);
            User shishi = new User("试试", R.drawable.ic_launcher_background);
            userList.add(shishi);
        }
    }
}