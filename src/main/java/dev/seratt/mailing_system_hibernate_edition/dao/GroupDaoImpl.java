package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.GroupEntity;
import dev.seratt.mailing_system_hibernate_edition.entity.UserEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {
    @Autowired
    private EntityManager entityManager;
    public List<GroupEntity> search(String searchText) {
        return entityManager.createQuery("SELECT g from GroupEntity g JOIN g.users u where " +
                        "g.title like :searchText or " +
                        "g.description like :searchText or " +
                        "u.name like :searchText or " +
                        "u.surname like :searchText or " +
                        "u.otchestvo like :searchText or " +
                        "u.email like :searchText", GroupEntity.class)
                .setParameter("searchText", "%"+searchText+"%")
                .getResultList();
    }

    public GroupEntity findById(Long id) {
        return entityManager.find(GroupEntity.class, id);
    }

    public List<GroupEntity> findGroupsByUsersContainingUserId(Long userId) {
        return entityManager.createQuery("SELECT g from GroupEntity g JOIN g.users u WHERE u.id = :userId", GroupEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<GroupEntity> findAll() {
        return entityManager.createQuery("from GroupEntity where isDeleted = false ", GroupEntity.class)
                .getResultList();
    }

    public void save(GroupEntity group) {
        entityManager.merge(group);
    }

    public void deleteById(Long id) {
        entityManager.createQuery("UPDATE GroupEntity set isDeleted = true where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
