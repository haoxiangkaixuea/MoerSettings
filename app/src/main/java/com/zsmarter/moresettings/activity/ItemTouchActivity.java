package com.zsmarter.moresettings.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.zsmarter.moresettings.ItemTouchHelp;
import com.zsmarter.moresettings.R;
import com.zsmarter.moresettings.adapetr.ItemTouchAdapter;
import com.zsmarter.moresettings.constant.Constants;
import com.zsmarter.moresettings.data.User;
import com.zsmarter.moresettings.presenter.ItemPresenter;
import com.zsmarter.moresettings.util.DataUtils;
import com.zsmarter.moresettings.util.DisRepeatUtils;
import com.zsmarter.moresettings.util.GridDividerItemDecoration;
import com.zsmarter.moresettings.view.ItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ItemTouchActivity extends AppCompatActivity implements View.OnClickListener, ItemView {
    private static final String TAG = "ItemTouchActivity";
    public List<User> stringList = new ArrayList<>();
    public List<User> stringList1 = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private Boolean isManager = false;
    private Button btnSet;
    private ItemTouchAdapter itemTouchAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);

        btnSet = findViewById(R.id.set);
        btnSet.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerview);
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

        itemTouchAdapter = new ItemTouchAdapter(this, userList);

        recyclerView.setAdapter(itemTouchAdapter);

        ItemPresenter itemPresenter = new ItemPresenter(this);
        itemPresenter.showList();

        // 设置分割线
        GridDividerItemDecoration decoration = new GridDividerItemDecoration(ItemTouchActivity.this);
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    protected void onStop() {
        //销毁前存储数据
        super.onStop();
        DataUtils.saveData(userList, DataUtils.DEFAULT_SP_NAME, "userList", this);
        DataUtils.saveData(stringList, DataUtils.DEFAULT_SP_NAME, "stringList", this);
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

    private void initUser() {
        userList = DataUtils.getData(DataUtils.DEFAULT_SP_NAME, "userList", this);
        Log.d(TAG, " initUser userList Size: " + userList.size());
        if (userList.size() == 0) {
            userList.addAll(stringList);
        }
        Log.d(TAG, " initUser userList.addAll(stringList) Size: " + userList.size());
        itemTouchAdapter.setData(userList);
        itemTouchAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnSuccess(String result) {
        Log.d(TAG, "result" + result);
    }

    @Override
    public void returnFailure(String msg) {
        Log.d(TAG, "msg" + msg);
    }

    @Override
    public void networkError(Throwable t) {
        Log.d(TAG, "网络错误" + t);
    }

    @Override
    public void getList(List<User> list) {
        stringList = list;
        Log.d(TAG, "stringList  Size: " + stringList.size());
        Log.d(TAG, "stringList数据: " + stringList);
        stringList1 = DataUtils.getData(DataUtils.DEFAULT_SP_NAME, "stringList", this);
        Log.d(TAG, "disRepeat(stringList, stringList1)" + DisRepeatUtils.disRepeat(stringList, stringList1));
        initUser();
        ItemTouchHelp itemTouchCallBack = new ItemTouchHelp(userList, itemTouchAdapter);
        Log.d(TAG, "userList数据: " + userList);
        Log.d(TAG, " userList Size: " + userList.size());
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        // 拖拽功能
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}