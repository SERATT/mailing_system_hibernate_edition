package dev.seratt.mailing_system_hibernate_edition.repository;

import dev.seratt.mailing_system_hibernate_edition.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    public CountryEntity findByName(String name);
}
