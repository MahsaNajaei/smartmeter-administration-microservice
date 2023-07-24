package com.energymeter.administrationservice.controller;

import com.energymeter.administrationservice.model.dto.EnergyMeterDto;
import com.energymeter.administrationservice.service.EnergyMeterHandlerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/energy-meters")
@RequiredArgsConstructor
public class EnergyMeterController {

    private final EnergyMeterHandlerService energyMeterHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnergyMeterDto registerEnergyMeter(@RequestBody EnergyMeterDto energyMeterDto) {
        return energyMeterHandler.registerEnergyMeter(energyMeterDto);
    }

    @GetMapping("/{id}")
    public EnergyMeterDto getEnergyMeter(@PathVariable long id) {
        return energyMeterHandler.getEnergyMeter(id);
    }

    @PatchMapping
    public void updateEnergyMeter(@Valid @RequestBody EnergyMeterDto energyMeterDto) {
        energyMeterHandler.updateEnergyMeterInfo(energyMeterDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEnergyMeter(@PathVariable long id) {
        energyMeterHandler.deleteEnergyMeter(id);
    }

}
