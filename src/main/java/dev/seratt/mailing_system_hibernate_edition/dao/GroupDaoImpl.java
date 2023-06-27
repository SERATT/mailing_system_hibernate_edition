package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {
    @Autowired
    private EntityManager entityManager;
    public List<Group> search(String searchText) {
        return entityManager.createQuery("SELECT g from Group g JOIN g.users u where " +
                        "g.title like :searchText or " +
                        "g.description like :searchText or " +
                        "u.name like :searchText or " +
                        "u.surname like :searchText or " +
                        "u.otchestvo like :searchText or " +
                        "u.email like :searchText", Group.class)
                .setParameter("searchText", "%"+searchText+"%")
                .getResultList();
    }

    public Group findById(int id) {
        return entityManager.find(Group.class, id);
    }

    public List<Group> findGroupsByUsersContaining(User user) {
        return entityManager.createQuery("SELECT g from Group g JOIN g.users u WHERE u.id = :userId", Group.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }

    public List<Group> findAll() {
        return entityManager.createQuery("from Group where isDeleted = false ", Group.class)
                .getResultList();
    }

    public void save(Group group) {
        entityManager.merge(group);
    }

    public void deleteById(int id) {
        entityManager.createQuery("UPDATE Group set isDeleted = true where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
