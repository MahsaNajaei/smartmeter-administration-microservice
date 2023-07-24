package com.energymeter.administrationservice.service.impl;

import com.energymeter.administrationservice.exception.EntityIdNotFoundException;
import com.energymeter.administrationservice.model.dto.NeighborhoodTariffDto;
import com.energymeter.administrationservice.model.entity.NeighborhoodTariff;
import com.energymeter.administrationservice.repository.NeighborhoodTariffRepository;
import com.energymeter.administrationservice.service.NeighborhoodTariffHandlerService;
import com.energymeter.administrationservice.validation.GeneralValidationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NeighborhoodTariffManager implements NeighborhoodTariffHandlerService {

    private final ModelMapper mapper;
    private final GeneralValidationUtils validationUtils;
    private final NeighborhoodTariffRepository neighborhoodTariffRepository;

    @Override
    public NeighborhoodTariffDto registerTariff(NeighborhoodTariffDto tariffInfo) {
        validationUtils.validateForInsertion(tariffInfo);
        var neighborhoodTariff = mapper.map(tariffInfo, NeighborhoodTariff.class);
        neighborhoodTariffRepository.save(neighborhoodTariff);
        return mapper.map(neighborhoodTariff, NeighborhoodTariffDto.class);
    }

    @Override
    public NeighborhoodTariffDto retrieveNeighborhoodTariffById(long tariffId) {
        var neighborhoodTariff = neighborhoodTariffRepository.findById(tariffId)
                .orElseThrow(() -> new EntityIdNotFoundException("NeighborHoodTariff", tariffId));
        return mapper.map(neighborhoodTariff, NeighborhoodTariffDto.class);
    }

    @Override
    public NeighborhoodTariffDto updateTariff(long neighborhoodTariffId, double tariffInPercentage) {
        var neighborhoodTariff = neighborhoodTariffRepository.findById(neighborhoodTariffId)
                .orElseThrow(() -> new EntityIdNotFoundException("NeighborHoodTariff", neighborhoodTariffId));
        neighborhoodTariff.setTariffInPercentage(tariffInPercentage);
        neighborhoodTariffRepository.save(neighborhoodTariff);
        return mapper.map(neighborhoodTariff, NeighborhoodTariffDto.class);
    }

    @Override
    public void updateNeighborhood(long neighborhoodTariffId, String neighborhoodName) {
        var neighborhoodTariff = neighborhoodTariffRepository.findById(neighborhoodTariffId)
                .orElseThrow(() -> new EntityIdNotFoundException("NeighborHoodTariff", neighborhoodTariffId));
        neighborhoodTariff.setNeighbourhood(neighborhoodName);
        neighborhoodTariffRepository.save(neighborhoodTariff);
    }

    @Override
    public void deleteTariff(long tariffId) {
        neighborhoodTariffRepository.deleteById(tariffId);
    }

    @Override
    public void checkIdIsReal(Long id) {
        if (!neighborhoodTariffRepository.existsById(id))
            throw new EntityIdNotFoundException("NeighborhoodTariff", id);
    }

    @Override
    public List<NeighborhoodTariffDto> retrieveNeighborhoodTariffsByCityId(long cityId) {
        var neighborhoodTariffList = neighborhoodTariffRepository.findByCityId(cityId);
        return neighborhoodTariffList.stream().
                map(element -> mapper.map(element, NeighborhoodTariffDto.class))
                .toList();
    }

    @Override
    public NeighborhoodTariffDto retrieveNeighborhoodTariffsByUserId(long userId) {
        var neighborhoodTariff = neighborhoodTariffRepository.findByEnergyMeters_UserId(userId)
                .orElseThrow(() -> new EntityIdNotFoundException("User", userId));
        return mapper.map(neighborhoodTariff, NeighborhoodTariffDto.class);
    }
}
