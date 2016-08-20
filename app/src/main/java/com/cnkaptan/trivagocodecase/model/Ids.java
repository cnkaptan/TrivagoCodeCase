package com.cnkaptan.trivagocodecase.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class Ids implements Parcelable {

    /**
     * trakt : 120
     * slug : the-dark-knight-2008
     * imdb : tt0468569
     * tmdb : 155
     */

    private int trakt;
    private String slug;
    private String imdb;
    private int tmdb;

    public int getTrakt() {
        return trakt;
    }

    public void setTrakt(int trakt) {
        this.trakt = trakt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getTmdb() {
        return tmdb;
    }

    public void setTmdb(int tmdb) {
        this.tmdb = tmdb;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.trakt);
        dest.writeString(this.slug);
        dest.writeString(this.imdb);
        dest.writeInt(this.tmdb);
    }

    public Ids() {
    }

    protected Ids(Parcel in) {
        this.trakt = in.readInt();
        this.slug = in.readString();
        this.imdb = in.readString();
        this.tmdb = in.readInt();
    }

    public static final Parcelable.Creator<Ids> CREATOR = new Parcelable.Creator<Ids>() {
        @Override
        public Ids createFromParcel(Parcel source) {
            return new Ids(source);
        }

        @Override
        public Ids[] newArray(int size) {
            return new Ids[size];
        }
    };
}
