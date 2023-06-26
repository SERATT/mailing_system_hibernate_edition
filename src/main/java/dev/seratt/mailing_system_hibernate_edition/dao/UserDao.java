package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.User;

import java.util.List;

public interface UserDao {
    List<User> search(String searchText);

    List<User> findAll();

    void save(User user);

    User findById(int id);

    void deleteById(int id);

    User findUserByEmail(String email);
}
