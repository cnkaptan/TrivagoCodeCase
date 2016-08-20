package com.cnkaptan.trivagocodecase.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class Images implements Parcelable {
    Poster poster;
    Banner banner;
    Thumb thumb;

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Thumb getThumb() {
        return thumb;
    }

    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.poster, flags);
        dest.writeParcelable(this.banner, flags);
        dest.writeParcelable(this.thumb, flags);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.poster = in.readParcelable(Poster.class.getClassLoader());
        this.banner = in.readParcelable(Banner.class.getClassLoader());
        this.thumb = in.readParcelable(Thumb.class.getClassLoader());
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
