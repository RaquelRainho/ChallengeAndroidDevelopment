package com.example.challengeandroiddevelopment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.challengeandroiddevelopment.adapters.CitiesAdapter;
import com.example.challengeandroiddevelopment.databinding.FragmentCitiesBinding;
import com.example.challengeandroiddevelopment.models.CityWeather;
import com.example.challengeandroiddevelopment.repositories.CitiesRepository;
import com.example.challengeandroiddevelopment.viewmodels.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

public class CitiesFragment extends Fragment {
    private FragmentCitiesBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    private CitiesRepository citiesRepo;
    private WeatherViewModel viewModel;
    private CityWeather currentLocationWeather;

    public CitiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
        citiesRepo = new CitiesRepository();
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkForLocation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCitiesBinding.inflate(inflater, container, false);

        CitiesAdapter adapter = new CitiesAdapter(citiesRepo.getCities());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void createCurrentLocationOnClickListener(){
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CitiesFragmentDirections.ActionCitiesFragmentToCityWeatherFragment action = CitiesFragmentDirections
                        .actionCitiesFragmentToCityWeatherFragment(currentLocationWeather.getId());

                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void checkForLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            permissionResult.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
        }else {
            getCurrentLocation();
            createCurrentLocationOnClickListener();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){
        fusedLocationClient.getLastLocation().addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    viewModel.getWeatherByLocation(location.getLatitude(), location.getLongitude())
                            .observe(getViewLifecycleOwner(), new Observer<CityWeather>() {
                                @Override
                                public void onChanged(CityWeather cityWeather) {
                                    currentLocationWeather = cityWeather;
                                    binding.currentLocation.setText(cityWeather.getCityName());
                                    binding.weatherValue.setText(getString(R.string.weather_value, (int)cityWeather.getMain().getTemp()));
                                    binding.moreDetails.setVisibility(View.VISIBLE);
                                    Picasso.get().load(cityWeather.getIconUrl()).into(binding.weatherIcon);
                                    }
                            });
                }
            }
        });
    }

    private final ActivityResultLauncher<String> permissionResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted){
                    getCurrentLocation();
                    checkForLocation();
                }else{
                    binding.currentLocation.setText(R.string.no_permission);
                    binding.moreDetails.setVisibility(View.INVISIBLE);
                }
            });
}