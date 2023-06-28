package dev.seratt.mailing_system_hibernate_edition.service;


import dev.seratt.mailing_system_hibernate_edition.DTO.UserDTO;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();

    public void saveUser(UserDTO userDTO);

    public UserDTO getUser(Long id);

    public void deleteUser(Long id);

    public List<UserDTO> search(String searchText);

    public boolean checkEmailUniqueness(String email);

}
