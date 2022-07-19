package com.example.challengeandroiddevelopment.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.challengeandroiddevelopment.BuildConfig;
import com.example.challengeandroiddevelopment.models.CityWeather;
import com.example.challengeandroiddevelopment.network.ApiEndpoints;
import com.example.challengeandroiddevelopment.network.WeatherAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherRepository {
    private final ApiEndpoints apiService;
    private MutableLiveData<CityWeather> cityWeather;

    public WeatherRepository(){
        Retrofit retrofit = WeatherAPIService.getRetrofitInstance();
        this.apiService = retrofit.create(ApiEndpoints.class);
        cityWeather = new MutableLiveData<>();
    }

    public MutableLiveData<CityWeather> getWeatherById(int id){
        Call<CityWeather> call = apiService.getWeatherById(id, BuildConfig.API_KEY);
        call.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                cityWeather.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.d("WeatherRepository", "Failed!" + t);
            }
        });
        return cityWeather;
    }

    public MutableLiveData<CityWeather> getWeatherByLocation(double lat, double lon){
        Call<CityWeather> call = apiService.getWeatherByLocation(lat, lon, BuildConfig.API_KEY);
        call.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                cityWeather.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.d("WeatherRepository", "Failed!" + t);
            }
        });
        return cityWeather;
    }
}
