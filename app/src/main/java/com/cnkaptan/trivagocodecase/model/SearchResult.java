package com.cnkaptan.trivagocodecase.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class SearchResult implements Parcelable {


    /**
     * type : movie
     * score : 49.35817
     * movie : {"title":"The Big Bang Theory: It All Started with a Big Bang","overview":"The Big Bang Theory - It All Started With a Big Bang behind the scenes, interviews with all casts and how they think of each characters, storyline and much more","year":2012,"images":{"poster":{"full":null,"medium":null,"thumb":null},"fanart":{"full":null,"medium":null,"thumb":null}},"ids":{"trakt":226033,"slug":"the-big-bang-theory-it-all-started-with-a-big-bang-2012","imdb":"tt2342339","tmdb":383889}}
     */

    private String type;
    private double score;
    private Movie movie;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeDouble(this.score);
        dest.writeParcelable(this.movie, flags);
    }

    public SearchResult() {
    }

    protected SearchResult(Parcel in) {
        this.type = in.readString();
        this.score = in.readDouble();
        this.movie = in.readParcelable(Movie.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchResult> CREATOR = new Parcelable.Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };
}
