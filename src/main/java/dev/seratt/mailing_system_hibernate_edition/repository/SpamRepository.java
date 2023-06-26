package dev.seratt.mailing_system_hibernate_edition.repository;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.Spam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SpamRepository extends JpaRepository<Spam, Integer> {
    Spam findById(int id);

    public Set<Spam> findSpamsByLetterThemeContainingIgnoreCaseOrLetterContentContainingIgnoreCase(String theme, String content);

    public void deleteSpamsByGroup(Group group);
}
