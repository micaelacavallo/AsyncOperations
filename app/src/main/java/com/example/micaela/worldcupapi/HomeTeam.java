package com.example.micaela.worldcupapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 16/02/2015.
 */
public class HomeTeam {
    @SerializedName("code")
    private String mCode;
    @SerializedName("goals")
    private String mGoals;

    public HomeTeam() {
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmGoals() {
        return mGoals;
    }

    public void setmGoals(String mGoals) {
        this.mGoals = mGoals;
    }

    @Override
    public String toString() {
        return mCode + "( " + mGoals + " - ";
    }
}