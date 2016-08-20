package com.cnkaptan.trivagocodecase.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class Poster implements Parcelable {

    /**
     * full : https://walter.trakt.us/images/movies/000/000/120/posters/original/8369bf0d4a.jpg
     * medium : https://walter.trakt.us/images/movies/000/000/120/posters/medium/8369bf0d4a.jpg
     * thumb : https://walter.trakt.us/images/movies/000/000/120/posters/thumb/8369bf0d4a.jpg
     */

    private String full;
    private String medium;
    private String thumb;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.full);
        dest.writeString(this.medium);
        dest.writeString(this.thumb);
    }

    public Poster() {
    }

    protected Poster(Parcel in) {
        this.full = in.readString();
        this.medium = in.readString();
        this.thumb = in.readString();
    }

    public static final Parcelable.Creator<Poster> CREATOR = new Parcelable.Creator<Poster>() {
        @Override
        public Poster createFromParcel(Parcel source) {
            return new Poster(source);
        }

        @Override
        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };
}
