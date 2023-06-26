package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.City;
import dev.seratt.mailing_system_hibernate_edition.entity.Country;
import dev.seratt.mailing_system_hibernate_edition.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    CityRepository cityRepository;

    public Set<City> getAllCitiesByCountryId(int countryId){
        return cityRepository.findCitiesByCountryId(countryId);
    }
    public City findById(int id){
        City city = null;
        try{
            city = cityRepository.findById(id).get();
        } catch (NoSuchElementException ex){
        }
        return city;
    }
}
