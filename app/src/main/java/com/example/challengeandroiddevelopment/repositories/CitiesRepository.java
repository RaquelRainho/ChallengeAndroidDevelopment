package com.example.challengeandroiddevelopment.repositories;

import java.util.HashMap;
import java.util.Map;

public class CitiesRepository {
    private Map<String, Integer> cities;

    public CitiesRepository(){
        populateMap();
    }

    public Map<String, Integer> getCities(){
        return cities;
    }

    private void populateMap(){
        cities = new HashMap<String, Integer>(){{
            put("Lisbon", 4516749);
            put("Madrid", 3117735);
            put("Paris", 2968815);
            put("Berlin", 2950158);
            put("Copenhagen", 2618425);
            put("Rome", 3169070);
            put("London", 2643743);
            put("Dublin", 2964574);
            put("Prague", 3067696);
            put("Vienna", 2761369);
        }};
    }
}
