package dev.seratt.mailing_system_hibernate_edition.repository;

import dev.seratt.mailing_system_hibernate_edition.entity.SpamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface SpamRepository extends JpaRepository<SpamEntity, Long> {

    public Set<SpamEntity> findSpamsByLetterThemeContainingIgnoreCaseOrLetterContentContainingIgnoreCase(String theme, String content);

    public void deleteSpamsByGroupId(Long groupId);
}
