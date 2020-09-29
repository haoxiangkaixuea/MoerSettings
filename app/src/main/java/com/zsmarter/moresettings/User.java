package com.zsmarter.moresettings;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Administrator
 */
public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            User user = new User();
            //user.img = in.readString();
            user.name = in.readString();
            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
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

    //重写describeContents，返回0
    @Override
    public int describeContents() {
        return 0;
    }

    //重写writeToParcel，写出字段
    @Override
    public void writeToParcel(Parcel parcel, int flage) {
        //  parcel.writeString(img);
        parcel.writeString(name);
    }

}
