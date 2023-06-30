package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.DTO.GroupDTO;
import dev.seratt.mailing_system_hibernate_edition.DTO.UserDTO;
import dev.seratt.mailing_system_hibernate_edition.dao.GroupDao;
import dev.seratt.mailing_system_hibernate_edition.entity.GroupEntity;
import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;
import dev.seratt.mailing_system_hibernate_edition.dao.UserDao;
import dev.seratt.mailing_system_hibernate_edition.repository.CityRepository;
import dev.seratt.mailing_system_hibernate_edition.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> usersList = new ArrayList<>();
        for(UserEntity user : userDao.findAll()){
            usersList.add(new UserDTO(user));
        }
        return usersList;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity user;

        if(userDTO.getId() != 0) {
            user = userDao.findById(userDTO.getId());
        } else {
            user = new UserEntity();
        }

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setOtchestvo(userDTO.getOtchestvo());
        user.setEmail(userDTO.getEmail());
        user.setCountry(countryRepository.findByName(userDTO.getCountry()));
        user.setCity(cityRepository.findByName(userDTO.getCity()));
        user.setDateOfCreation(userDTO.getDateOfCreation());

        userDao.save(user);
    }

    @Override
    public UserDTO getUser(Long id) {
        return new UserDTO(userDao.findById(id));
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userDao.findById(id);
        for (GroupEntity group : groupDao.findGroupsByUsersContainingUserId(id)) {
            group.removeUser(user);
            groupDao.save(group);
        }
        userDao.deleteById(id);
    }

    @Override
    public List<UserDTO> search(String searchText){
        List<UserDTO> usersList = new ArrayList<>();
        for(UserEntity user : userDao.search(searchText)){
            usersList.add(new UserDTO(user));
        }
        return usersList;
    }

    @Override
    public boolean checkEmailUniqueness(String email) {
        return userDao.findUserByEmail(email) == null;
    }
}
