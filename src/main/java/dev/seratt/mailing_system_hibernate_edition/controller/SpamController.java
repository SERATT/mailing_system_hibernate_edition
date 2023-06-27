package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.entity.Spam;
import dev.seratt.mailing_system_hibernate_edition.service.EmailService;
import dev.seratt.mailing_system_hibernate_edition.service.GroupService;
import dev.seratt.mailing_system_hibernate_edition.service.SpamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/spam")
public class SpamController {
    @Autowired
    private SpamService spamService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private EmailService emailService;

    private int groupId;

    @GetMapping("/create")
    public String goToChooseGroup(Model model){
        model.addAttribute("groupsList", null);
        return "choose-group";
    }

    @GetMapping("/send_mail")
    public String sendEmail(@RequestParam("groupId") int groupId, Model model){
        Spam spam = new Spam();
        spam.setSendDate(new Timestamp(System.currentTimeMillis()));
        model.addAttribute("spam", spam);
        this.groupId = groupId;
        return "spam-form";
    }

    @PostMapping("/save")
    public String saveSpam(@ModelAttribute @Valid Spam spam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "spam-form";
        }
        spamService.saveSpam(spam, groupService.getGroup(groupId));
        return "redirect:/mailing";
    }
}
