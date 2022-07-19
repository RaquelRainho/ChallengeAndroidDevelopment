package com.example.challengeandroiddevelopment.models;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private float temp;

    @SerializedName("feels_like")
    private float feelsLike;

    @SerializedName("temp_min")
    private float tempMin;

    @SerializedName("temp_max")
    private float tempMax;

    @SerializedName("humidity")
    private int humidity;

    public float getTemp() {
        return temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public int getHumidity() {
        return humidity;
    }
}
