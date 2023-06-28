package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.DTO.SpamDTO;
import dev.seratt.mailing_system_hibernate_edition.entity.SpamEntity;

import java.util.List;
import java.util.Set;

public interface SpamService {
    public List<SpamDTO> getAllSpams();

    public void saveSpam(SpamDTO spamDTO, Long groupId);

    public SpamEntity getSpam(Long id);

    public Set<SpamDTO> search(String searchText);

    public void deleteSpamsByGroupId(Long groupId);

}
