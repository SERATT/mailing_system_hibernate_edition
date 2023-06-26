package dev.seratt.mailing_system_hibernate_edition.service;


import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.User;

import java.util.List;

public interface GroupService {
    public List<Group> getAllGroups();

    public void saveGroup(Group group);

    public Group getGroup(int id);

    public void deleteGroup(int id);

    public List<Group> search(String searchText);
    public void save(Group group);

    public List<Group> getGroupsByUsersContaining(User userEntity);

    public void addUserToGroup(User userEntity, Group group);

    public void removeUserFromGroup(User userEntity, Group group);
}
