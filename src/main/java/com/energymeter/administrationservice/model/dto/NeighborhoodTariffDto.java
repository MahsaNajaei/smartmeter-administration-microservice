package com.energymeter.administrationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NeighborhoodTariffDto {
    private Long Id;
    private Long cityId;
    private String cityName;
    private String neighbourhood;
    private Double tariffInPercentage;
}