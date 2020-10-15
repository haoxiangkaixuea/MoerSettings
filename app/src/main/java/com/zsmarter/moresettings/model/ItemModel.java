package com.zsmarter.moresettings.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.zsmarter.moresettings.api.ItemServiceApi;
import com.zsmarter.moresettings.constant.Constants;
import com.zsmarter.moresettings.data.User;
import com.zsmarter.moresettings.entity.ResponseEntity;
import com.zsmarter.moresettings.httpservice.RetrofitClient;
import com.zsmarter.moresettings.interfaces.ItemCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Administrator
 */
public class ItemModel {

    private static final String TAG = "RegisterModel";


    private static MediaType JSON = MediaType.get("application/json;charset=utf-8");
    /**
     * 单例模式
     */
    private static volatile ItemModel INSTANCE;

    public ItemModel() {

    }

    public static ItemModel getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemModel();
                }
            }
        }
        return INSTANCE;
    }

    public void getData(ItemCallBack itemCallBack) {
        Retrofit retrofit = RetrofitClient.getInstance();
        ItemServiceApi api = retrofit.create(ItemServiceApi.class);
        String content = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            jsonObject.put("code", "3E8744hk6618");
            //json串转string类型
            content = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(JSON, content);
        Call<ResponseBody> call = api.setData(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                String result;
                String code = "";
                String message = "";
                List<User> jsonMessage = new ArrayList<>();
                if (response.body() != null) {
                    try {
                        result = response.body().string();
                        Log.d(TAG, "result" + result);
                        Gson gson = new Gson();
                        ResponseEntity responseEntity = gson.fromJson(result, ResponseEntity.class);
                        code = responseEntity.getCode();
                        message = responseEntity.getMessage();
                        jsonMessage = responseEntity.getContext().getUserList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (Constants.GET_CODE.equals(code)) {
                    itemCallBack.onReturnSuccess(message);
                    itemCallBack.getList(jsonMessage);
                } else {
                    itemCallBack.onReturnFailure(message);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                itemCallBack.networkError(t);
            }
        });
    }
}
