package dev.seratt.mailing_system_hibernate_edition.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spam")
public class SpamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToMany(mappedBy = "spam", cascade = CascadeType.ALL)
    Set<SpamUserHistoryEntity> spamUserHistory = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity group;

    @Column(name = "status_code")
    private char statusCode;

    @Column(name = "send_date")
    private Timestamp sendDate;


    @Column(name = "letter_theme")
    private String letterTheme;

    @Column(name = "letter_content")
    private String letterContent;
    public SpamEntity() {
    }

    public SpamEntity(Long id, GroupEntity group, char statusCode, Timestamp sendDate, String letterTheme, String letterContent) {
        this.id = id;
        this.group = group;
        this.statusCode = statusCode;
        this.sendDate = sendDate;
        this.letterTheme = letterTheme;
        this.letterContent = letterContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public char getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(char statusCode) {
        this.statusCode = statusCode;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public String getLetterTheme() {
        return letterTheme;
    }

    public void setLetterTheme(String letterTheme) {
        this.letterTheme = letterTheme;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    @Override
    public String toString() {
        return "Spam{" +
                "id=" + id +
                ", group=" + group +
                ", statusCode=" + statusCode +
                ", sendDate=" + sendDate +
                ", letterTheme='" + letterTheme + '\'' +
                ", letterContent='" + letterContent + '\'' +
                '}';
    }

    public Set<SpamUserHistoryEntity> getSpamUserHistory() {
        return spamUserHistory;
    }

    public void setSpamUserHistory(Set<SpamUserHistoryEntity> spamUserHistory) {
        this.spamUserHistory = spamUserHistory;
    }
}
