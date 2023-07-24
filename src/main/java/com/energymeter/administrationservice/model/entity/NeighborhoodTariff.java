package com.energymeter.administrationservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class NeighborhoodTariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private City city;
    @Column(nullable = false, unique = true)
    private String neighbourhood;
    @Column(nullable = false)
    private Double tariffInPercentage;
    @OneToMany(mappedBy = "neighborhoodTariff")
    private List<EnergyMeter> energyMeters;
}
