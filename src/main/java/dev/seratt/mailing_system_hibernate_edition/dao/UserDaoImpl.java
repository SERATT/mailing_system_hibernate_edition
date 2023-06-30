package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private EntityManager entityManager;

    public List<UserEntity> search(String searchText) {
        return entityManager.createQuery("from UserEntity where isDeleted = false and " +
                "(name like :searchText or " +
                "surname like :searchText or " +
                "otchestvo like :searchText or " +
                "email like :searchText)", UserEntity.class)
                .setParameter("searchText", "%"+searchText+"%")
                .getResultList();
    }

    @Override
    public List<UserEntity> findAll() {
        return entityManager.createQuery("from UserEntity where isDeleted = false order by id asc ", UserEntity.class).getResultList();
    }

    @Override
    public void save(UserEntity user) {
        entityManager.merge(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("update UserEntity set isDeleted = true where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        UserEntity user;
        try {
            Query query = entityManager.createQuery("from UserEntity where email = :email", UserEntity.class)
                    .setParameter("email", email);
            user = (UserEntity) query.getSingleResult();
        } catch (Exception ex){
            return null;
        }
        return user;
    }
}
