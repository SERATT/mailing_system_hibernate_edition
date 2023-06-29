package dev.seratt.mailing_system_hibernate_edition.DTO;

import dev.seratt.mailing_system_hibernate_edition.entity.CityEntity;
import dev.seratt.mailing_system_hibernate_edition.entity.CountryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryDTO {
    private Long id;

    private String name;

    public String toString() {
        return name;
    }

    public CountryDTO(CountryEntity country){
        this.id = country.getId();
        this.name = country.getName();
    }

}
