package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.CityEntity;

import java.util.Set;

public interface CityService {
    public Set<CityEntity> getAllCityEntitiesByCountryName(String countryName);

}
