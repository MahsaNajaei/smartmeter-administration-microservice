package com.energymeter.administrationservice.controller;

import com.energymeter.administrationservice.model.dto.CityDto;
import com.energymeter.administrationservice.model.dto.NeighborhoodTariffDto;
import com.energymeter.administrationservice.service.LocationService;
import com.energymeter.administrationservice.service.impl.NeighborhoodTariffManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final NeighborhoodTariffManager neighborhoodTariffManager;

    @PostMapping("/cities/{cityName}")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto addCity(@PathVariable String cityName) {
        return locationService.addCity(cityName);
    }

    @PatchMapping("/cities/{cityId}")
    public CityDto changeCityName(@PathVariable long cityId, @RequestParam String cityName) {
        return locationService.changeCityName(cityId, cityName);
    }

    @GetMapping("/cities")
    public List<CityDto> getAllCities() {
        return locationService.getCities();
    }

    @DeleteMapping("/cities/{cityId}")
    public void deleteCity(@PathVariable long cityId) {
        locationService.deleteCity(cityId);
    }

    @GetMapping("/{cityId}/neighbourhoods")
    public List<NeighborhoodTariffDto> retrieveCityNeighborhoods(@PathVariable long cityId) {
        return neighborhoodTariffManager.retrieveNeighborhoodTariffsByCityId(cityId);
    }

    @PatchMapping("/neighborhoods/{neighbourhoodTariffId}")
    public void changeNeighborhoodName(@PathVariable long neighbourhoodTariffId, @RequestParam String neighborhoodName) {
        neighborhoodTariffManager.updateNeighborhood(neighbourhoodTariffId, neighborhoodName);
    }
}
