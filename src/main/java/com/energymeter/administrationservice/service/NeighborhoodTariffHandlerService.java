package com.energymeter.administrationservice.service;

import com.energymeter.administrationservice.model.dto.NeighborhoodTariffDto;

import java.util.List;

public interface NeighborhoodTariffHandlerService {

    NeighborhoodTariffDto registerTariff(NeighborhoodTariffDto neighborhoodTariffDto);

    NeighborhoodTariffDto retrieveNeighborhoodTariffById(long neighborhoodTariffId);

    List retrieveNeighborhoodTariffsByCityId(long cityId);

    NeighborhoodTariffDto retrieveNeighborhoodTariffsByUserId(long userId);

    NeighborhoodTariffDto updateTariff(long neighborhoodTariffId, double tariffInPercentage);

    void updateNeighborhood(long neighborhoodTariffId, String neighborhoodName);

    void deleteTariff(long neighborhoodTariffId);

    void checkIdIsReal(Long neighborhoodTariffId);
}
