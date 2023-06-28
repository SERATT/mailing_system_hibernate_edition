package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;

import java.util.List;

public interface UserDao {
    public List<UserEntity> search(String searchText);

    public List<UserEntity> findAll();

    public void save(UserEntity user);

    public UserEntity findById(Long id);

    public void deleteById(Long id);

    public UserEntity findUserByEmail(String email);
}
