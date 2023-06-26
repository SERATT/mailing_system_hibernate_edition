package dev.seratt.mailing_system_hibernate_edition.service;


import dev.seratt.mailing_system_hibernate_edition.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User userEntity);

    public User getUser(int id);

    public void deleteUser(int id);

    public List<User> search(String searchText);

    public boolean checkEmailUniqueness(String email);

}
