package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.User;
import dev.seratt.mailing_system_hibernate_edition.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Group> getAllGroups() {
        return groupDao.findAll();
    }

    @Override
    public void saveGroup(Group group) {
        groupDao.save(group);
    }

    @Override
    public Group getGroup(int id) {
        return groupDao.findById(id);
    }

    @Override
    public void deleteGroup(int id) {
        groupDao.deleteById(id);
    }

    @Override
    public List<Group> search(String searchText) {
        return groupDao.search(searchText);
    }

    @Override
    public void save(Group group) {
        groupDao.save(group);
    }

    @Override
    public List<Group> getGroupsByUsersContaining(User userEntity) {
        return groupDao.findGroupsByUsersContaining(userEntity);
    }

    @Override
    public void addUserToGroup(User userEntity, Group group) {
        group.addUser(userEntity);
        save(group);
    }

    @Override
    public void removeUserFromGroup(User userEntity, Group group) {
        group.removeUser(userEntity);
        save(group);
    }
}
