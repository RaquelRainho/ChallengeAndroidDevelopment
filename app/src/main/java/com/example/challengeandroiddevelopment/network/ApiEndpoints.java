package com.example.challengeandroiddevelopment.network;

import com.example.challengeandroiddevelopment.models.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("/data/2.5/weather?units=metric")
    Call<CityWeather> getWeatherById(@Query("id") int cityId, @Query("appid") String appId);

    @GET("/data/2.5/weather?units=metric")
    Call<CityWeather> getWeatherByLocation(@Query("lat") double lat,@Query("lon") double lon,@Query("appid") String appId);

}
