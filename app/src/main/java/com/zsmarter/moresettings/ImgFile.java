package com.zsmarter.moresettings;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Administrator
 */
public class ImgFile implements Parcelable {
    public static final Creator<ImgFile> CREATOR = new Creator<ImgFile>() {
        @Override
        public ImgFile createFromParcel(Parcel in) {
            ImgFile user = new ImgFile();
            //user.img = in.readString();
            user.fileName = in.readString();
            return user;
        }

        @Override
        public ImgFile[] newArray(int size) {
            return new ImgFile[size];
        }
    };
    private String fileName;
    private int fileSrc;
    private int fileDelete;

    public ImgFile() {

    }

    public ImgFile(String fileName, int fileSrc, int fileDelete) {
        this.fileName = fileName;
        this.fileSrc = fileSrc;
        this.fileDelete = fileDelete;
    }

    public int getFileDelete() {
        return fileDelete;
    }

    public void setFileDelete(int fileDelete) {
        this.fileDelete = fileDelete;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(int fileSrc) {
        this.fileSrc = fileSrc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileName);
    }
}
