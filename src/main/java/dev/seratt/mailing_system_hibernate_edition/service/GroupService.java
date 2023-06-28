package dev.seratt.mailing_system_hibernate_edition.service;


import dev.seratt.mailing_system_hibernate_edition.DTO.GroupDTO;
import dev.seratt.mailing_system_hibernate_edition.entity.GroupEntity;

import java.util.List;

public interface GroupService {
    public List<GroupDTO> getAllGroups();

    public void saveGroup(GroupDTO groupDTO);

    public GroupDTO getGroup(Long id);

    public void deleteGroup(Long id);

    public List<GroupDTO> search(String searchText);
    public void save(GroupEntity group);

    public void addUserToGroup(Long userId, Long groupId);

    public void removeUserFromGroup(Long userId, Long groupId);
}
