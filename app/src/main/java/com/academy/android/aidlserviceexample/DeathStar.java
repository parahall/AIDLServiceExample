package com.academy.android.aidlserviceexample;

import android.os.Parcel;
import android.os.Parcelable;

public class DeathStar implements Parcelable {

    private int width;

    private int height;

    private String BFG;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBFG() {
        return BFG;
    }

    public void setBFG(String BFG) {
        this.BFG = BFG;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.BFG);
    }

    public DeathStar(int height, int width, String BFG) {
        this.height = height;
        this.width = width;
        this.BFG = BFG;
    }

    protected DeathStar(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.BFG = in.readString();
    }

    public static final Parcelable.Creator<DeathStar> CREATOR
            = new Parcelable.Creator<DeathStar>() {
        public DeathStar createFromParcel(Parcel source) {
            return new DeathStar(source);
        }

        public DeathStar[] newArray(int size) {
            return new DeathStar[size];
        }
    };
}
