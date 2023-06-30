package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.DTO.GroupDTO;
import dev.seratt.mailing_system_hibernate_edition.service.GroupService;
import dev.seratt.mailing_system_hibernate_edition.service.SpamService;
import dev.seratt.mailing_system_hibernate_edition.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/form")
    public String updateGroup(@RequestParam("id") Long id, Model model){
        GroupDTO groupDTO;
        if(id == 0){
            groupDTO = new GroupDTO();
            groupDTO.setId(0L);
            groupDTO.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            groupDTO = groupService.getGroup(id);
        }
        model.addAttribute("group", groupDTO);
        return "group-form";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("group") @Valid GroupDTO groupDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "group-form";
        }
        groupService.saveGroup(groupDTO);
        return "redirect:/groups";
    }

    @GetMapping("/delete")
    public String deleteGroup(@RequestParam("id") Long id){
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("/createGroup")
    public String createGroup(@RequestBody GroupDTO groupDTO){
        groupService.saveGroup(groupDTO);
        return "redirect:/groups";
    }

    @GetMapping("/addUser")
    public String addUserToGroup(@RequestParam("userId") String userIdStr, @RequestParam("groupId") Long groupId, Model model){
        Long userId;
        try{
            userId = Long.parseLong(userIdStr);
            groupService.addUserToGroup(userId, groupId);
        } catch (NumberFormatException ex){
            model.addAttribute("groupId", groupId);
            return "choose-user";
        }

        return "redirect:/groups";
    }

    @GetMapping("/removeUser")
    public String removeUserFromGroup(@RequestParam("userId") Long userId, @RequestParam("groupId") Long groupId, HttpServletRequest request){
        groupService.removeUserFromGroup(userId, groupId);
        return "redirect:" + request.getHeader("Referer");
    }
}
