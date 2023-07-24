package com.energymeter.administrationservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class EnergyMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @ManyToOne
    private NeighborhoodTariff neighborhoodTariff;
}
