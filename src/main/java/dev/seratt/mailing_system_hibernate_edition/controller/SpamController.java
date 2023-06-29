package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.DTO.SpamDTO;
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

    private Long groupId;

    @GetMapping("/create")
    public String goToChooseGroup(Model model){
        model.addAttribute("groupsList", null);
        return "choose-group";
    }

    @GetMapping("/send_mail")
    public String sendEmail(@RequestParam("groupId") Long groupId, Model model){
        SpamDTO spamDTO = new SpamDTO();
        spamDTO.setSendDate(new Timestamp(System.currentTimeMillis()));
        model.addAttribute("spam", spamDTO);
        this.groupId = groupId;
        return "spam-form";
    }

    @PostMapping("/save")
    public String saveSpam(@ModelAttribute @Valid SpamDTO spamDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "spam-form";
        }
        spamService.saveSpam(spamDTO, groupId);
        return "redirect:/mailing";
    }
}
