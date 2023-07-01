package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.DTO.GroupDTO;
import dev.seratt.mailing_system_hibernate_edition.dao.UserDao;
import dev.seratt.mailing_system_hibernate_edition.entity.GroupEntity;
import dev.seratt.mailing_system_hibernate_edition.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<GroupDTO> getAllGroups() {
        List<GroupDTO> groupDTOList = new ArrayList<>();
        List<GroupEntity> groupsList = groupDao.findAll();
        for(GroupEntity group : groupsList){
            GroupDTO groupDTO = new GroupDTO(group);
            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }

    @Override
    public void saveGroup(GroupDTO groupDTO) {
        GroupEntity group;
        if(groupDTO.getId() == 0) {
            group = new GroupEntity();
            group.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            group = groupDao.findById(groupDTO.getId());
        }
        group.setTitle(groupDTO.getTitle());
        group.setDescription(groupDTO.getDescription());
        groupDao.save(group);
    }

    @Override
    public GroupDTO getGroup(Long id) {
        return new GroupDTO(groupDao.findById(id));
    }

    @Override
    public void deleteGroup(Long id) {
        groupDao.deleteById(id);
    }

    @Override
    public List<GroupDTO> search(String searchText) {
        List<GroupDTO> groupsList = new ArrayList<>();
        for(GroupEntity group : groupDao.search(searchText)){
            groupsList.add(new GroupDTO(group));
        }
        return groupsList;
    }

    @Override
    public void save(GroupEntity group) {
        groupDao.save(group);
    }

    @Override
    public void addUserToGroup(Long userId, Long groupId) {
        GroupEntity group = groupDao.findById(groupId);
        group.addUser(userDao.findById(userId));
        save(group);
    }

    @Override
    public void removeUserFromGroup(Long userId, Long groupId) {
        GroupEntity group = groupDao.findById(groupId);
        group.removeUser(userDao.findById(userId));
        save(group);
    }
}
