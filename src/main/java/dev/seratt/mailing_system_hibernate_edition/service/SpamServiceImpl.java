package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.DTO.SpamDTO;
import dev.seratt.mailing_system_hibernate_edition.dao.GroupDao;
import dev.seratt.mailing_system_hibernate_edition.entity.*;
import dev.seratt.mailing_system_hibernate_edition.repository.SpamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class SpamServiceImpl implements SpamService{
    @Autowired
    private SpamRepository spamRepository;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private EmailService emailService;
    @Override
    public List<SpamDTO> getAllSpams() {
        List<SpamDTO> spamList = new ArrayList<>();
        for(SpamEntity spam : spamRepository.findAll()) {
            spamList.add(new SpamDTO(spam));
        }
        return spamList;
    }

    @Override
    public void saveSpam(SpamDTO spamDTO, Long groupId) {
        SpamEntity spam = new SpamEntity();
        spam.setStatusCode('R');
        spam.setGroup(groupDao.findById(groupId));
        spam.setLetterTheme(spamDTO.getLetterTheme());
        spam.setLetterContent(spamDTO.getLetterContent());
        spam.setSendDate(spamDTO.getSendDate());

        String theme = spamDTO.getLetterTheme();
        String content = spamDTO.getLetterContent();

        int sent = 0, not_sent = 0;
        for(UserEntity user : spam.getGroup().getUsers()){
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setSubject(theme);
            emailDetails.setMsgBody(content);

            SpamUserHistoryEntity spamUserHistory = new SpamUserHistoryEntity();
            spamUserHistory.setUser(user);
            spamUserHistory.setSpam(spam);
            spamUserHistory.setStatusCode('R');
            if(emailService.sendSimpleMail(emailDetails)){
                sent++;
                spamUserHistory.setStatusCode('G');
            } else {
                not_sent++;
            }

            spam.getSpamUserHistory().add(spamUserHistory);
        }

        spam.setStatusCode('G');

        if(not_sent > 0){
            spam.setStatusCode('Y');
        }

        if(sent == 0){
            spam.setStatusCode('R');
        }
        spamRepository.save(spam);
    }

    @Override
    public SpamEntity getSpam(Long id) {
        return spamRepository.findById(id).get();
    }

    @Override
    public Set<SpamDTO> search(String searchText) {
        Set<SpamDTO> spamList = new HashSet<>();
        for(SpamEntity spam :
                spamRepository.findSpamsByLetterThemeContainingIgnoreCaseOrLetterContentContainingIgnoreCase(searchText, searchText)
        ) {
            spamList.add(new SpamDTO(spam));
        }
        return spamList;
    }

    @Override
    public void deleteSpamsByGroupId(Long groupId) {
        spamRepository.deleteSpamsByGroupId(groupId);
    }


}
