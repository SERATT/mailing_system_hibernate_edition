package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.CountryEntity;
import dev.seratt.mailing_system_hibernate_edition.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    public CountryEntity findById(Long id){
        return countryRepository.findById(id).get();
    }
}
