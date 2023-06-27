package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> search(String searchText);

    public List<User> findAll();

    public void save(User user);

    public User findById(int id);

    public void deleteById(int id);

    public User findUserByEmail(String email);
}
