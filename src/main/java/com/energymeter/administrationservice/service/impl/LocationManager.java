package com.energymeter.administrationservice.service.impl;

import com.energymeter.administrationservice.exception.EntityIdNotFoundException;
import com.energymeter.administrationservice.model.dto.CityDto;
import com.energymeter.administrationservice.model.entity.City;
import com.energymeter.administrationservice.repository.CityRepository;
import com.energymeter.administrationservice.service.LocationService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationManager implements LocationService {

    private final ModelMapper mapper;
    private final CityRepository cityRepository;

    @Override
    public CityDto addCity(@NotBlank String cityName) {
        var city = new City(cityName);
        cityRepository.save(city);
        return mapper.map(city, CityDto.class);
    }

    @Override
    public CityDto changeCityName(long cityId, @NotBlank String newCityName) {
        var city = cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityIdNotFoundException("City", cityId));
        city.setName(newCityName);
        cityRepository.save(city);
        return mapper.map(city, CityDto.class);
    }

    @Override
    public List getCities() {
        var cities = cityRepository.findAll();
        return cities
                .stream()
                .map(city -> mapper.map(city, CityDto.class))
                .toList();
    }

    @Override
    public void deleteCity(long cityId) {
        cityRepository.deleteById(cityId);
    }

}
