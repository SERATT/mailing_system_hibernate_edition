package dev.seratt.mailing_system_hibernate_edition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "group", schema = "public")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Title can not be left blank")
    @Size(min = 2, max = 25, message = "Title must be min 2 and max 25 characters")
    private String title;

    @Column(name = "description")
    @NotBlank(message = "Description can not be left blank")
    @Size(min = 2, message = "Description must be min 2 characters")
    private String description;

    @Column(name = "date_of_creation")
    private Timestamp dateOfCreation;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<UserEntity> users;

    public GroupEntity() {
    }


    public GroupEntity(Long id, String title, String description, Timestamp dateOfCreation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public void addUser(UserEntity user) {
        users.add(user);
        user.getGroups().add(this);
    }
    public void removeUser(UserEntity user) {
        users.remove(user);
        user.getGroups().remove(this);
    }
}
