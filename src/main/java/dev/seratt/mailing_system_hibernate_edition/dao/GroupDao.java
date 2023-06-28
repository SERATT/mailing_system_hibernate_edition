package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.GroupEntity;
import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;

import java.util.List;

public interface GroupDao {

    public List<GroupEntity> search(String searchText);

    public GroupEntity findById(Long id);

    public List<GroupEntity> findGroupsByUsersContainingUserId(Long userId);

    public List<GroupEntity> findAll();

    public void save(GroupEntity group);

    public void deleteById(Long id);
}
