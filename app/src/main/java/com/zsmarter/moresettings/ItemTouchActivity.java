package com.zsmarter.moresettings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchActivity extends AppCompatActivity {
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
        ItemTouchAdapter itemTouchAdapter = new ItemTouchAdapter(userList);
        recyclerView.setAdapter(itemTouchAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(ItemTouchActivity.this, DividerItemDecoration.VERTICAL));

        ItemTouchHelp itemTouchCallBack = new ItemTouchHelp(userList, itemTouchAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        //拖拽功能
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initUser() {
        for (int i = 0; i < 2; i++) {
            User zhangsan = new User("饿了么", R.drawable.ic_launcher_background);
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