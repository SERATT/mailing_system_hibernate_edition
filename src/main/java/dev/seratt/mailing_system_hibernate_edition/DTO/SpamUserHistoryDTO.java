package dev.seratt.mailing_system_hibernate_edition.DTO;

import dev.seratt.mailing_system_hibernate_edition.entity.SpamUserHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SpamUserHistoryDTO {
    private Long id;
    private UserDTO user;
    private char statusCode;

    public SpamUserHistoryDTO(SpamUserHistoryEntity spamUserHistory) {
        this.id = spamUserHistory.getId();
        this.user = new UserDTO(spamUserHistory.getUser());
        this.statusCode = spamUserHistory.getStatusCode();
    }
}
