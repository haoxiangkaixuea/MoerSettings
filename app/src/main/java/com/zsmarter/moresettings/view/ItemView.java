package com.zsmarter.moresettings.view;

import com.zsmarter.moresettings.data.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface ItemView {

    /**
     * 返回数据成功
     *
     * @param result 返回成功数据
     */
    void returnSuccess(String result);

    /**
     * 返回数据失败
     *
     * @param msg 返回失败数据
     */
    void returnFailure(String msg);

    /**
     * 网络错误
     *
     * @param t 返回错误数据
     */
    void networkError(Throwable t);

    /**
     * 返回数据
     *
     * @param list 返回message数据
     */
    void getList(List<User> list);
}

