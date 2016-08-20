package com.cnkaptan.trivagocodecase.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class Thumb implements Parcelable {

    /**
     * full : https://walter.trakt.us/images/movies/000/000/120/thumbs/original/6363cb8aeb.jpg
     */

    private String full;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.full);
    }

    public Thumb() {
    }

    protected Thumb(Parcel in) {
        this.full = in.readString();
    }

    public static final Parcelable.Creator<Thumb> CREATOR = new Parcelable.Creator<Thumb>() {
        @Override
        public Thumb createFromParcel(Parcel source) {
            return new Thumb(source);
        }

        @Override
        public Thumb[] newArray(int size) {
            return new Thumb[size];
        }
    };
}
