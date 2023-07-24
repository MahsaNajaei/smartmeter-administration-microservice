package com.energymeter.administrationservice.repository;

import com.energymeter.administrationservice.model.entity.NeighborhoodTariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborhoodTariffRepository extends JpaRepository<NeighborhoodTariff, Long> {
    List<NeighborhoodTariff> findByCityId(long cityId);

//    @Query("select tariff, tariff.energyMeters as energyMeter from NeighborhoodTariff tariff where energyMeter.userId = ?1")
//    Optional<NeighborhoodTariff> findNeighborhoodTariffByUserId(long userId);

    Optional<NeighborhoodTariff> findByEnergyMeters_UserId(long userId);
}
