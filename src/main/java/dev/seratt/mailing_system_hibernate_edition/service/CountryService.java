package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.DTO.CountryDTO;
import dev.seratt.mailing_system_hibernate_edition.entity.CountryEntity;

import java.util.List;

public interface CountryService {
    public List<CountryDTO> getAllCountries();
}
