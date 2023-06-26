package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.Spam;

import java.util.List;
import java.util.Set;

public interface SpamService {
    public List<Spam> getAllSpams();

    public void saveSpam(Spam spam, Group group);

    public Spam getSpam(int id);

    public Set<Spam> search(String searchText);

    public void deleteSpamsByGroup(Group group);

}
