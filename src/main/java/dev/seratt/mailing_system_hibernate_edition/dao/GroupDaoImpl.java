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
    EntityManager entityManager;
    public List<GroupEntity> search(String searchText) {
        return entityManager.createQuery("from GroupEntity where title like :searchText or description like :searchText ", GroupEntity.class)
                .setParameter("searchText", "%"+searchText+"%")
                .getResultList();
    }

    public GroupEntity findById(int id) {
        return entityManager.find(GroupEntity.class, id);
    }

    public List<GroupEntity> findGroupsByUsersContaining(UserEntity userEntity) {
        return entityManager.createQuery("SELECT g from GroupEntity g JOIN g.users u WHERE u.id = :userId", GroupEntity.class)
                .setParameter("userId", userEntity.getId())
                .getResultList();
    }

    public List<GroupEntity> findAll() {
        return entityManager.createQuery("from GroupEntity ", GroupEntity.class)
                .getResultList();
    }

    public void save(GroupEntity groupEntity) {
        entityManager.merge(groupEntity);
    }

    public void deleteById(int id) {
        entityManager.createQuery("UPDATE GroupEntity set isDeleted = true where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
