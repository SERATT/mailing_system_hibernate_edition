package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.DTO.GroupDTO;
import dev.seratt.mailing_system_hibernate_edition.DTO.SpamDTO;
import dev.seratt.mailing_system_hibernate_edition.DTO.UserDTO;
import dev.seratt.mailing_system_hibernate_edition.entity.*;
import dev.seratt.mailing_system_hibernate_edition.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private SpamService spamService;

    @GetMapping("/")
    public String mainPage(){
        return "main-page";
    }

    @GetMapping("/users")
    public String usersPage(Model model){
        model.addAttribute("usersList", userService.getAllUsers());
        return "users-page";
    }

    @GetMapping("/searchUsers")
    public String findUsersBySearch(@RequestParam("searchText") String searchText,
                                    Model model){
        if(searchText.isEmpty()){
            return "redirect:/users";
        }
        List<UserDTO> usersList = userService.search(searchText);
        model.addAttribute("usersList", usersList);
        return "users-page";
    }

    @GetMapping("/searchUsersForAdding")
    public String findUsersForAddingBySearch(@RequestParam("searchText") String searchText,
                                             Model model, HttpServletRequest request, @RequestParam("groupId") Long groupId){
        if(searchText.isEmpty()){
            return "redirect:" + request.getHeader("Referer");
        }
        List<UserDTO> usersList = userService.search(searchText);
        model.addAttribute("usersList", usersList);
        model.addAttribute("groupId", groupId);
        return "choose-user";
    }

    @GetMapping("/searchGroupsForAdding")
    public String findGroupsForAddingBySearch(@RequestParam("searchText") String searchText,
                                             Model model, HttpServletRequest request){
        if(searchText.isEmpty()){
            return "redirect:" + request.getHeader("Referer");
        }
        List<GroupDTO> groupsList = groupService.search(searchText);
        model.addAttribute("groupsList", groupsList);
        return "choose-group";
    }

    @GetMapping("/groups")
    public String groupsPage(Model model){
        model.addAttribute("groupsList", groupService.getAllGroups());
        return "groups-page";
    }

    @GetMapping("/mailing")
    public String mailingPage(Model model){
        model.addAttribute("spamsList", spamService.getAllSpams());
        return "mailing-page";
    }

    @GetMapping("/searchGroups")
    public String findGroupsBySearch(@RequestParam("searchText") String searchText,
                                     Model model){
        if(searchText.isEmpty()){
            return "redirect:/groups";
        }
        List<GroupDTO> groupsList = groupService.search(searchText);
        model.addAttribute("groupsList", groupsList);
        return "groups-page";
    }

    @GetMapping("/searchSpams")
    public String findSpamsBySearch(@RequestParam("searchText") String searchText,
                                     Model model){
        if(searchText.isEmpty()){
            return "redirect:/mailing";
        }
        Set<SpamDTO> spamsList = spamService.search(searchText);
        model.addAttribute("spamsList", spamsList);
        return "mailing-page";
    }


}
