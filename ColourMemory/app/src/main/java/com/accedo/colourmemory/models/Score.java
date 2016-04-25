package com.accedo.colourmemory.models;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Model class to store Score related attributes for DB
 */
public class Score implements Serializable, Parcelable {
    private long id;
    private String name;
    private int score;

    public Score(long id, String name, int score) {
        this.name = name;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.score);
    }

    protected Score(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.score = in.readInt();
    }

    public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel source) {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };
}
