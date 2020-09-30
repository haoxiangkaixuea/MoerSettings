package com.zsmarter.moresettings;

/**
 * @author Administrator
 */
public class User {

    private int img;
    private String name;

    public User() {
    }

    public User(String name, int img) {
        this.name = name;
        this.img = img;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

}
