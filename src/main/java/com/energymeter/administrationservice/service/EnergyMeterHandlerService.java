package com.energymeter.administrationservice.service;

import com.energymeter.administrationservice.model.dto.EnergyMeterDto;

public interface EnergyMeterHandlerService {

    EnergyMeterDto registerEnergyMeter(EnergyMeterDto energyMeterDto);

    EnergyMeterDto getEnergyMeter(long energyMeterId);

    void updateEnergyMeterInfo(EnergyMeterDto energyMeterDto);

    void deleteEnergyMeter(long energyMeterId);
}
