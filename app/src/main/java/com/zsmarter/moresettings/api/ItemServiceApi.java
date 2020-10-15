package com.zsmarter.moresettings.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Administrator
 */
public interface ItemServiceApi {

    /**
     * 登录的网络请求
     *
     * @param requestBody 设置数据网络请求体
     *                    动态态添加请求头
     * @return ResponseBody   ResponseBody
     */
    @POST("mock/5d6dd0e53d655f501a6c5067/mobilebank/getMoreSettingItems")
    Call<ResponseBody> setData(@Body RequestBody requestBody);
}

