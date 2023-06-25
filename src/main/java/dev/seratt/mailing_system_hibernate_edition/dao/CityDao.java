package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.City;
import dev.seratt.mailing_system_hibernate_edition.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CityRepository extends JpaRepository<City, Integer> {
    public Set<City> findCitiesByCountry(Country country);
}
