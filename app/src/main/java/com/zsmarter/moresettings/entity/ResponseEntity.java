package com.zsmarter.moresettings.entity;

import com.zsmarter.moresettings.data.User;

import java.util.List;

/**
 * GSON 获取数据
 *
 * @author Administrator
 */
public class ResponseEntity {
    private String code;
    private String message;
    private Context context;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class Context {
        private List<User> userList;

        public List<User> getUserList() {
            return userList;
        }

        public void setUserList(List<User> userList) {
            this.userList = userList;
        }
    }
}
