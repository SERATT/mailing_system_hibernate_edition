package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.City;
import dev.seratt.mailing_system_hibernate_edition.entity.Country;

import java.util.Set;

public interface CityService {
    public Set<City> getAllCitiesByCountryId(int countryId);
    public City findById(int id);

}
