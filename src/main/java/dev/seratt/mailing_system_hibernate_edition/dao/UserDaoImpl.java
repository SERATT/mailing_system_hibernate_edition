package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    EntityManager entityManager;

    public List<User> search(String searchText) {
        return entityManager.createQuery("from User where " +
                "name like :searchText or " +
                "surname like :searchText or " +
                "otchestvo like :searchText or " +
                "email like :searchText", User.class)
                .setParameter("searchText", "%"+searchText+"%")
                .getResultList();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User where isDeleted = false order by id asc ", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("update User set isDeleted = true where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User findUserByEmail(String email) {
        User user;
        try {
            Query query = entityManager.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email);
            user = (User) query.getSingleResult();
        } catch (Exception ex){
            return null;
        }
        return user;
    }
}
