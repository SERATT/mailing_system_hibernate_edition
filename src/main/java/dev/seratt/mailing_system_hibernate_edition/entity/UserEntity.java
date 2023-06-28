package dev.seratt.mailing_system_hibernate_edition.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String surname;

    @Column(name = "otchestvo")
    @NotBlank(message = "Otchestvo can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String otchestvo;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;


    @Size(max = 100, message = "Email max 100 characters")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid email: does not match the pattern")
    @Column(name = "email")
    private String email;

    @Column(name = "date_of_creation")
    private Timestamp dateOfCreation;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<GroupEntity> groups = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<SpamUserHistoryEntity> sentUsers;

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String surname, String otchestvo, CountryEntity country, CityEntity city, String email, Timestamp dateOfCreation) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.otchestvo = otchestvo;
        this.country = country;
        this.city = city;
        this.email = email;
        this.dateOfCreation = dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp date_of_creation) {
        this.dateOfCreation = date_of_creation;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groupEntities) {
        this.groups = groupEntities;
    }

    @Override
    public String toString() {
        return "User: " + name + " " + surname + " " + otchestvo +
                " Email:" + email;
    }
}
