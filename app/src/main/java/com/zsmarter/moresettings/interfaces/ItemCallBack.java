package com.zsmarter.moresettings.interfaces;

import com.zsmarter.moresettings.data.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface ItemCallBack {

    /**
     * 返回成功数据
     *
     * @param result 返回成功数据
     */
    void onReturnSuccess(String result);

    /**
     * 登录失败
     *
     * @param msg 返回注册失败的结果
     */
    void onReturnFailure(String msg);

    /**
     * 网络错误
     *
     * @param t 返回失败错误
     */
    void networkError(Throwable t);

    /**
     * 返回数据
     *
     * @param list list
     */
    void getList(List<User> list);
}
