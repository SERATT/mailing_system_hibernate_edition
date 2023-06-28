package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.entity.CityEntity;
import dev.seratt.mailing_system_hibernate_edition.service.CityService;
import dev.seratt.mailing_system_hibernate_edition.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MyRestController {
    @Autowired
    private CityService cityService;

    @GetMapping("/api/country/{country}/getCitiesByCountry")
    public Set<CityEntity> getCitiesListByCountry(@PathVariable("country") String countryName) {
        return cityService.getAllCityEntitiesByCountryName(countryName);
    }
}
