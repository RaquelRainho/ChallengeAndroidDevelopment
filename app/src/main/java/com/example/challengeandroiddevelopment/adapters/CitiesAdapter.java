package com.example.challengeandroiddevelopment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challengeandroiddevelopment.CitiesFragmentDirections;
import com.example.challengeandroiddevelopment.CitiesFragmentDirections.ActionCitiesFragmentToCityWeatherFragment;
import com.example.challengeandroiddevelopment.R;

import java.util.Map;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {
    private Map<String, Integer> cities;

    public CitiesAdapter(Map<String, Integer> cities){
        this.cities = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cityView.setText(cities.keySet().toArray()[position].toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionCitiesFragmentToCityWeatherFragment action = CitiesFragmentDirections
                        .actionCitiesFragmentToCityWeatherFragment(cities.get(cities.keySet().toArray()[holder.getBindingAdapterPosition()]));

                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView cityView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            cityView = itemView.findViewById(R.id.city_name);
        }
    }
}
