package com.energymeter.administrationservice.validation;

import com.energymeter.administrationservice.exception.DataValidationException;
import com.energymeter.administrationservice.model.dto.EnergyMeterDto;
import com.energymeter.administrationservice.model.dto.NeighborhoodTariffDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class GeneralValidationUtils<T> {

    public boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }

    public void validateForInsertion(EnergyMeterDto energyMeterDto) {
        if (isEmpty(energyMeterDto)
                || isEmpty(energyMeterDto.getUserId())
                || isEmpty(energyMeterDto.getNeighborhoodTariff())
                || isEmpty(energyMeterDto.getNeighborhoodTariff().getId()))
            throw new DataValidationException("Data is incomplete for energyMeter registration! UserId and neighbourhoodTariffId are mandatory!");
    }

    public void validateForInsertion(NeighborhoodTariffDto neighborhoodTariffDto) {
        if (isEmpty(neighborhoodTariffDto)
                || isEmpty(neighborhoodTariffDto.getCityId())
                || isEmpty(neighborhoodTariffDto.getNeighbourhood())
                || isEmpty(neighborhoodTariffDto.getTariffInPercentage()))
            throw new DataValidationException("Data is incomplete for neighborhoodTariff registration! CityId, neighborhood and tariff values are required!");
    }

    public void validateForUpdate() {

    }
}
