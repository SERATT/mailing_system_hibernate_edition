package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.User;
import dev.seratt.mailing_system_hibernate_edition.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User userEntity) {
        userDao.save(userEntity);
    }

    @Override
    public User getUser(int id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> search(String searchText){
        return userDao.search(searchText);
    }

    @Override
    public boolean checkEmailUniqueness(String email) {
        return userDao.findUserByEmail(email) == null;
    }
}
