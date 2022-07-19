package com.example.challengeandroiddevelopment.models;

import com.google.gson.annotations.SerializedName;

public class CityWeather {
    @SerializedName("name")
    private String cityName;

    @SerializedName("id")
    private int id;

    @SerializedName("weather")
    private Weather[] weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("wind")
    private Wind wind;

    public String getCityName() {
        return cityName;
    }

    public Main getMain() {
        return main;
    }

    public int getId() {
        return id;
    }

    public Wind getWind() {
        return wind;
    }

    public String getIconUrl(){
        return "https://openweathermap.org/img/wn/" + weather[0].getIcon() + "@4x.png";
    }
}

