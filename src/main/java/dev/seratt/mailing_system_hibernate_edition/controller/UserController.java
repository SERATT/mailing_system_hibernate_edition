package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.DTO.UserDTO;
import dev.seratt.mailing_system_hibernate_edition.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/form")
    public String updateUser(@RequestParam("id") Long id, Model model){
        UserDTO userDTO;
        if(id.equals(0L)){
            userDTO = new UserDTO();
            userDTO.setId(0L);
            userDTO.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            userDTO = userService.getUser(id);
        }
        model.addAttribute("user", userDTO);
        model.addAttribute("countryList", countryService.getAllCountries());
        return "user-form";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, Model model){
        // фильтр валидации
        if(bindingResult.hasErrors()){
            model.addAttribute("countryList", countryService.getAllCountries());
            model.addAttribute("user", userDTO);
            return "user-form";
        }

        String email = userDTO.getEmail();

        // if new user is being created
        if(userDTO.getId().equals(0L)){
            // if email exists
            if(!userService.checkEmailUniqueness(email)){
                model.addAttribute("error_message", "Email is not unique");
                model.addAttribute("countryList", countryService.getAllCountries());
                model.addAttribute("user", userDTO);
                return "user-form";
            }
            // if unique, create new instance and set the creation time
            userDTO.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            // user is being updated
            // if the email in form is not original and is not unique
            if(!userDTO.getEmail()
                    .equals(userService.getUser(userDTO.getId()).getEmail()) && !userService.checkEmailUniqueness(email)){
                model.addAttribute("error_message", "Email is not unique");
                model.addAttribute("countryList", countryService.getAllCountries());
                model.addAttribute("user", userDTO);
                return "user-form";
            }
        }

        userService.saveUser(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return "redirect:/users";
    }

}
