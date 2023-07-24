package com.energymeter.administrationservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "city")
    @Column(nullable = false)
    private List<NeighborhoodTariff> neighborhoods;

    public City() {
    }

    public City(String cityName) {
        this.name = cityName;
    }
}
