package com.example.lab5_alarm;

import android.os.Parcel;
import android.os.Parcelable;

public class Alarm implements Parcelable {

    int hour, min;
    boolean am;
    boolean[] days = new boolean[7];
    String desc;
    boolean able = true;
    int uuid = (int) Math.floor(Math.random() * 1000000);

    public Alarm(int hour, int min, boolean am, boolean[] days, String desc) {
        this.hour = hour;
        this.min = min;
        this.am = am;
        this.days = days;
        this.desc = desc;
    }

    protected Alarm(Parcel in) {
        hour = in.readInt();
        min = in.readInt();
        am = in.readByte() != 0;
        days = in.createBooleanArray();
        desc = in.readString();
        able = in.readByte() != 0;
        uuid = in.readInt();
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public boolean isAm() {
        return am;
    }

    public void setAm(boolean am) {
        this.am = am;
    }

    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isAble() {
        return able;
    }

    public void setAble(boolean able) {
        this.able = able;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(hour);
        parcel.writeInt(min);
        parcel.writeByte((byte) (am ? 1 : 0));
        parcel.writeBooleanArray(days);
        parcel.writeString(desc);
        parcel.writeByte((byte) (able ? 1 : 0));
        parcel.writeInt(uuid);
    }
}
