package com.example.challengeandroiddevelopment.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.challengeandroiddevelopment.models.CityWeather;
import com.example.challengeandroiddevelopment.repositories.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository repo;

    public WeatherViewModel(){
        repo = new WeatherRepository();
    }

    public MutableLiveData<CityWeather> getWeatherById(int id){
        return repo.getWeatherById(id);
    }

    public MutableLiveData<CityWeather> getWeatherByLocation(double lat, double lon){
        return repo.getWeatherByLocation(lat, lon);
    }
}
