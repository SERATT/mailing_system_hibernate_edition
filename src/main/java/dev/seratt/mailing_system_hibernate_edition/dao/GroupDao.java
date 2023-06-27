package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.User;

import java.util.List;

public interface GroupDao {

    public List<Group> search(String searchText);

    public Group findById(int id);

    public List<Group> findGroupsByUsersContaining(User user);

    public List<Group> findAll();

    public void save(Group group);

    public void deleteById(int id);
}
