package com.cnkaptan.trivagocodecase.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class Movie implements Parcelable {

    /**
     * title : The Dark Knight
     * year : 2008
     * ids : {"trakt":120,"slug":"the-dark-knight-2008","imdb":"tt0468569","tmdb":155}
     * tagline : Why So Serious?
     * overview : Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.
     * released : 2008-07-18
     * runtime : 152
     * trailer : http://youtube.com/watch?v=GVx5K8WfFJY
     * homepage : http://thedarkknight.warnerbros.com/dvdsite/
     * rating : 9.03287
     * votes : 25404
     * updated_at : 2016-08-17T01:32:09.000Z
     * language : en
     */

    private String title;
    private int year;
    private Ids ids;
    private Images images;
    private List<String> genres;
    private String tagline;
    private String overview;
    private String released;
    private int runtime;
    private String trailer;
    private String homepage;
    private double rating;
    private int votes;
    private String updated_at;
    private String language;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.year);
        dest.writeParcelable(this.ids, flags);
        dest.writeParcelable(this.images, flags);
        dest.writeStringList(this.genres);
        dest.writeString(this.tagline);
        dest.writeString(this.overview);
        dest.writeString(this.released);
        dest.writeInt(this.runtime);
        dest.writeString(this.trailer);
        dest.writeString(this.homepage);
        dest.writeDouble(this.rating);
        dest.writeInt(this.votes);
        dest.writeString(this.updated_at);
        dest.writeString(this.language);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.year = in.readInt();
        this.ids = in.readParcelable(Ids.class.getClassLoader());
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.genres = in.createStringArrayList();
        this.tagline = in.readString();
        this.overview = in.readString();
        this.released = in.readString();
        this.runtime = in.readInt();
        this.trailer = in.readString();
        this.homepage = in.readString();
        this.rating = in.readDouble();
        this.votes = in.readInt();
        this.updated_at = in.readString();
        this.language = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
