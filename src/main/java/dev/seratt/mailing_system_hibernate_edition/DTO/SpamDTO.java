package dev.seratt.mailing_system_hibernate_edition.DTO;

import dev.seratt.mailing_system_hibernate_edition.entity.SpamEntity;
import dev.seratt.mailing_system_hibernate_edition.entity.SpamUserHistoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpamDTO {
    private Long id;

    @NotBlank(message = "Theme cannot be blank")
    @Size(min = 2, max = 100, message = "Theme must be min 2 and max 100 characters")
    private String letterTheme;

    @NotBlank
    @Size(min = 2, message = "Content must be min 2 characters")
    private String letterContent;
    private Timestamp sendDate;
    private GroupDTO group;
    private Set<SpamUserHistoryDTO> spamUserHistory = new HashSet<>();
    private char statusCode;

    public SpamDTO(SpamEntity spam){
        this.id = spam.getId();
        this.letterTheme = spam.getLetterTheme();
        this.letterContent = spam.getLetterContent();
        this.sendDate = spam.getSendDate();
        this.group = new GroupDTO(spam.getGroup());
        for (SpamUserHistoryEntity spamUserHistory : spam.getSpamUserHistory()) {
            this.spamUserHistory.add(new SpamUserHistoryDTO(spamUserHistory));
        }
        this.statusCode = spam.getStatusCode();
    }
}
