package dev.seratt.mailing_system_hibernate_edition.DTO;

import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String name;

    @NotBlank(message = "Surname can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String surname;

    @NotBlank(message = "Otchestvo can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String otchestvo;

    @Size(max = 100, message = "Email max 100 characters")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid email: does not match the pattern")
    private String email;

    private String country;
    private String city;
    private Timestamp dateOfCreation;

    public UserDTO(UserEntity user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.otchestvo = user.getOtchestvo();
        this.country = user.getCountry().getName();
        this.city = user.getCity().getName();
        this.email = user.getEmail();
        this.dateOfCreation = user.getDateOfCreation();
    }

    @Override
    public String toString() {
        return "User: " + this.name + " " + this.surname + " " + this.otchestvo + " " + this.email;
    }
}
