package com.energymeter.administrationservice.repository;

import com.energymeter.administrationservice.model.entity.EnergyMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyMeterRepository extends JpaRepository<EnergyMeter, Long> {
}
