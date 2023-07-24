package com.energymeter.administrationservice.service;

import com.energymeter.administrationservice.model.dto.CityDto;

import java.util.List;

public interface LocationService {

    void deleteCity(long cityId);

    CityDto addCity(String cityName);

    CityDto changeCityName(long cityId, String newCityName);

    List getCities();
}
