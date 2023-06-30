package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.CityEntity;
import dev.seratt.mailing_system_hibernate_edition.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepository cityRepository;

    public Set<CityEntity> getAllCityEntitiesByCountryName(String countryName){
        return cityRepository.findCityEntitiesByCountryName(countryName);
    }
}
