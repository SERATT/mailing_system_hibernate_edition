package dev.seratt.mailing_system_hibernate_edition.repository;

import dev.seratt.mailing_system_hibernate_edition.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    public Set<CityEntity> findCityEntitiesByCountryName(String countryName);

    public CityEntity findByName(String name);
}
