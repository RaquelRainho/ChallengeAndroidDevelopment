package com.example.challengeandroiddevelopment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.challengeandroiddevelopment.databinding.FragmentCityWeatherBinding;
import com.example.challengeandroiddevelopment.models.CityWeather;
import com.example.challengeandroiddevelopment.viewmodels.WeatherViewModel;
import com.squareup.picasso.Picasso;

public class CityWeatherFragment extends Fragment {
    private FragmentCityWeatherBinding binding;
    private WeatherViewModel viewModel;

    public CityWeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCityWeatherBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int id = CityWeatherFragmentArgs.fromBundle(getArguments()).getId();

        viewModel.getWeatherById(id).observe(getViewLifecycleOwner(), new Observer<CityWeather>() {
            @Override
            public void onChanged(CityWeather cityWeather) {
                binding.cityName.setText(cityWeather.getCityName());
                Picasso.get().load(cityWeather.getIconUrl()).into(binding.weatherIcon);
                binding.gridLayout.setVisibility(View.VISIBLE);
                binding.weatherValue.setText(getString(R.string.weather_value,(int)cityWeather.getMain().getTemp()));
                binding.tempMax.setText(getString(R.string.temp_max, (int)cityWeather.getMain().getTempMax()));
                binding.tempMin.setText(getString(R.string.temp_min, (int) cityWeather.getMain().getTempMin()));
                binding.humidity.setText(getString(R.string.humidity, cityWeather.getMain().getHumidity()));
                binding.windSpeed.setText(getString(R.string.wind_speed, cityWeather.getWind().getSpeed()));
            }
        });
    }
}