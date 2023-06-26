package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getAllCountries();

    public Country findById(int id);
}
