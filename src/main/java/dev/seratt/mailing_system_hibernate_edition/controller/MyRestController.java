package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.entity.City;
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
    CityService cityService;
    @Autowired
    CountryService countryService;
    @GetMapping("/api/country/{countryId}/getCitiesByCountry")
    public Set<City> getCitiesListByCountry(@PathVariable("countryId") int countryId) {
        return cityService.getAllCitiesByCountryId(countryId);
    }
}
