package com.energymeter.administrationservice.controller;

import com.energymeter.administrationservice.model.dto.NeighborhoodTariffDto;
import com.energymeter.administrationservice.service.NeighborhoodTariffHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tariffs")
@RequiredArgsConstructor
public class TariffController {

    private final NeighborhoodTariffHandlerService neighborhoodTariffHandlerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NeighborhoodTariffDto defineTariff(@RequestBody NeighborhoodTariffDto neighborhoodTariffDto) {
        return neighborhoodTariffHandlerService.registerTariff(neighborhoodTariffDto);
    }

    @GetMapping("/{neighbourhoodTariffId}")
    public NeighborhoodTariffDto getTariff(@PathVariable long neighbourhoodTariffId) {
        return neighborhoodTariffHandlerService.retrieveNeighborhoodTariffById(neighbourhoodTariffId);
    }

    @PatchMapping("/{neighbourhoodTariffId}")
    public NeighborhoodTariffDto updateTariff(@PathVariable long neighbourhoodTariffId, @RequestParam Double tariffInPercentage) {
        return neighborhoodTariffHandlerService.updateTariff(neighbourhoodTariffId, tariffInPercentage);
    }

    @DeleteMapping("/{neighbourhoodTariffId}")
    public void deleteTariff(@PathVariable long neighbourhoodTariffId) {
        neighborhoodTariffHandlerService.deleteTariff(neighbourhoodTariffId);
    }

    @GetMapping("/search/{userId}")
    public NeighborhoodTariffDto getTariffByUserId(@PathVariable long userId) {
        return neighborhoodTariffHandlerService.retrieveNeighborhoodTariffsByUserId(userId);
    }
}
