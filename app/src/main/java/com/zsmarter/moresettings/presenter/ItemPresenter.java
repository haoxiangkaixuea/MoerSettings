package com.zsmarter.moresettings.presenter;


import android.util.Log;

import com.zsmarter.moresettings.data.User;
import com.zsmarter.moresettings.interfaces.ItemCallBack;
import com.zsmarter.moresettings.model.ItemModel;
import com.zsmarter.moresettings.view.ItemView;

import java.util.List;

/**
 * @author Administrator
 */
public class ItemPresenter {

    private static final String TAG = "RegisterPresenter";
    ItemModel registerModel = ItemModel.getInstance();
    /**
     * View接口
     */
    private ItemView itemView;

    public ItemPresenter(ItemView view) {
        this.itemView = view;
    }

    public void showList() {
        registerModel.getData(new ItemCallBack() {

            @Override
            public void onReturnSuccess(String result) {
                itemView.returnSuccess(result);
            }

            @Override
            public void onReturnFailure(String msg) {
                itemView.returnFailure(msg);
            }

            @Override
            public void networkError(Throwable t) {
                itemView.networkError(t);
                Log.d(TAG, "t " + t);
            }

            @Override
            public void getList(List<User> list) {
                itemView.getList(list);
            }
        });
    }
}
