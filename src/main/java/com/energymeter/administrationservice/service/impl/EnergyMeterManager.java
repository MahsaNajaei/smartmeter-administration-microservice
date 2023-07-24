package com.energymeter.administrationservice.service.impl;

import com.energymeter.administrationservice.exception.EntityIdNotFoundException;
import com.energymeter.administrationservice.model.dto.EnergyMeterDto;
import com.energymeter.administrationservice.model.entity.EnergyMeter;
import com.energymeter.administrationservice.repository.EnergyMeterRepository;
import com.energymeter.administrationservice.service.EnergyMeterHandlerService;
import com.energymeter.administrationservice.service.NeighborhoodTariffHandlerService;
import com.energymeter.administrationservice.validation.GeneralValidationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class EnergyMeterManager implements EnergyMeterHandlerService {

    private final ModelMapper mapper;
    private final GeneralValidationUtils validationUtils;
    private final EnergyMeterRepository energyMeterRepository;
    private final NeighborhoodTariffHandlerService neighborhoodTariffHandler;

    @Override
    public EnergyMeterDto registerEnergyMeter(EnergyMeterDto energyMeterDto) {
        validationUtils.validateForInsertion(energyMeterDto);
        neighborhoodTariffHandler.checkIdIsReal(energyMeterDto.getNeighborhoodTariff().getId());
        var energyMeter = mapper.map(energyMeterDto, EnergyMeter.class);
        energyMeterRepository.save(energyMeter);
        return mapper.map(energyMeter, EnergyMeterDto.class);
    }

    @Override
    public EnergyMeterDto getEnergyMeter(long energyMeterId) {
        var energyMeter = energyMeterRepository.findById(energyMeterId).orElseThrow(() -> new EntityIdNotFoundException("EnergyMeter", energyMeterId));
        return mapper.map(energyMeter, EnergyMeterDto.class);
    }

    @Override
    public void updateEnergyMeterInfo(EnergyMeterDto energyMeterDto) {
        var energyMeter = energyMeterRepository.findById(energyMeterDto.getId())
                .orElseThrow(() -> new EntityIdNotFoundException("EnergyMeter", energyMeterDto.getId()));
        if (hasNeighborhoodTariffUpdate(energyMeter, energyMeterDto)) {
            neighborhoodTariffHandler.checkIdIsReal(energyMeterDto.getNeighborhoodTariff().getId());
            energyMeter.setNeighborhoodTariff(null);
        }
        mapper.map(energyMeterDto, energyMeter);
        energyMeterRepository.save(energyMeter);
    }

    private boolean hasNeighborhoodTariffUpdate(EnergyMeter energyMeter, EnergyMeterDto energyMeterDto) {
        return (!ObjectUtils.isEmpty(energyMeterDto.getNeighborhoodTariff())
                && energyMeter.getNeighborhoodTariff().getId() != energyMeterDto.getNeighborhoodTariff().getId());
    }

    @Override
    public void deleteEnergyMeter(long energyMeterId) {
        energyMeterRepository.deleteById(energyMeterId);
    }
}
