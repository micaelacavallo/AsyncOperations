package com.example.micaela.worldcupapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by micaela.cavallo on 10/02/2015.
 */
public class Match {
    @SerializedName("home_team")
    private HomeTeam mHomeTeam;
    @SerializedName("away_team")
    private AwayTeam mAwayTeam;
    @SerializedName("location")
    private String mLocation;

    public Match() {
    }

    public HomeTeam getmHomeTeam() {
        return mHomeTeam;
    }

    public void setmHomeTeam(HomeTeam mHomeTeam) {
        this.mHomeTeam = mHomeTeam;
    }

    public AwayTeam getmAwayTeam() {
        return mAwayTeam;
    }

    public void setmAwayTeam(AwayTeam mAwayTeam) {
        this.mAwayTeam = mAwayTeam;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }


}
