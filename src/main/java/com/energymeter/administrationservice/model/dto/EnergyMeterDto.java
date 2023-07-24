package com.energymeter.administrationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnergyMeterDto {
    @NotNull(message = "Energy Meter Id Should Not Be Null!")
    private Long Id;
    private Long userId;
    private NeighborhoodTariffDto neighborhoodTariff;
}
