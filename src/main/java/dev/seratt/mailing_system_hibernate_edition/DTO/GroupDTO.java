package dev.seratt.mailing_system_hibernate_edition.DTO;

import dev.seratt.mailing_system_hibernate_edition.entity.GroupEntity;
import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupDTO {
    private Long id;

    @NotBlank(message = "Title can not be left blank")
    @Size(min = 2, max = 25, message = "Title must be min 2 and max 25 characters")
    private String title;

    @NotBlank(message = "Description can not be left blank")
    @Size(min = 2, message = "Description must be min 2 characters")
    private String description;

    private Timestamp dateOfCreation;

    private Set<UserDTO> users;

    public GroupDTO(GroupEntity group) {
        this.id = group.getId();
        this.title = group.getTitle();
        this.description = group.getDescription();
        this.dateOfCreation = group.getDateOfCreation();
        this.users = new HashSet<>();

        for(UserEntity user : group.getUsers()){
            UserDTO userDTO = new UserDTO(user);
            this.users.add(userDTO);
        }
    }
}
