package com.wenmingkeji.peiliao.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by bevis on 2016/7/6.
 */
public class User extends MultiItemEntity implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private int IconHead;
    private String userName;
    private String sex;

    public User() {
    }

    protected User(Parcel in) {
        this.IconHead = in.readInt();
        this.userName = in.readString();
        this.sex = in.readString();
    }

    public int getIconHead() {
        return IconHead;
    }

    public void setIconHead(int iconHead) {
        IconHead = iconHead;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.IconHead);
        dest.writeString(this.userName);
        dest.writeString(this.sex);
    }
}
