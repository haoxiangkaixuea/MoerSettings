package com.zsmarter.moresettings.data;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * @author Administrator
 */
public class User {

    private int img;
    private int delete;
    private String name;

    public User() {
    }

    public User(String name, int img) {
        this.name = name;
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return img == user.img &&
                delete == user.delete &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(img, delete, name);
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
