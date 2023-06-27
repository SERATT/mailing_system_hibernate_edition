package dev.seratt.mailing_system_hibernate_edition.controller;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.User;
import dev.seratt.mailing_system_hibernate_edition.form.UserForm;
import dev.seratt.mailing_system_hibernate_edition.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SpamService spamService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @GetMapping("/form")
    public String updateUser(@RequestParam("id") int id, Model model){
        User userEntity;
        if(id == 0){
            userEntity = new User();
            userEntity.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            userEntity = userService.getUser(id);
        }
        model.addAttribute("user", userEntity);
        model.addAttribute("countryList", countryService.getAllCountries());
        return "user-form";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") @Valid UserForm userForm, BindingResult bindingResult, Model model, HttpServletRequest request){
        // фильтр валидации
        if(bindingResult.hasErrors()){
            model.addAttribute("countryList", countryService.getAllCountries());
            userForm.setCountry(countryService.findById(userForm.getCountryId()));
            userForm.setCity(cityService.findById(userForm.getCityId()));
            model.addAttribute("user", userForm);
            return "user-form";
        }

        String email = userForm.getEmail();
        User userEntity;

        // if new user is being created
        if(userForm.getId() == 0){
            // if email exists
            if(!userService.checkEmailUniqueness(email)){
                model.addAttribute("error_message", "Email is not unique");
                model.addAttribute("countryList", countryService.getAllCountries());
                userForm.setCountry(countryService.findById(userForm.getCountryId()));
                userForm.setCity(cityService.findById(userForm.getCityId()));
                model.addAttribute("user", userForm);
                return "user-form";
            }
            // if unique, create new instance and set the creation time
            userEntity = new User();
            userEntity.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            // user is being updated
            userEntity = userService.getUser(userForm.getId());

            // if the email in form is not original and is not unique
            if(!userEntity.getEmail().equals(userForm.getEmail()) && !userService.checkEmailUniqueness(email)){
                model.addAttribute("error_message", "Email is not unique");
                model.addAttribute("countryList", countryService.getAllCountries());
                model.addAttribute("user", userEntity);
                return "user-form";
            }
        }
        // set the fields as given in the form
        userEntity.setName(userForm.getName());
        userEntity.setSurname(userForm.getSurname());
        userEntity.setOtchestvo(userForm.getOtchestvo());
        userEntity.setEmail(userForm.getEmail());

        userEntity.setCountry(countryService.findById(userForm.getCountryId()));
        userEntity.setCity(cityService.findById(userForm.getCityId()));

        userService.saveUser(userEntity);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id){
        User userEntity = userService.getUser(id);
        List<Group> groupsContainingUser = groupService.getGroupsByUsersContaining(userEntity);
        groupsContainingUser.forEach(group -> group.removeUser(userEntity));
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User userEntity){
        userService.saveUser(userEntity);
        return "redirect:/users";
    }

}
