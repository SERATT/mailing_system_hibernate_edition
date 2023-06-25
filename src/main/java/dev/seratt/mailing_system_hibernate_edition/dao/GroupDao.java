package dev.seratt.mailing_system_hibernate_edition.dao;

import dev.seratt.mailing_system_hibernate_edition.entity.Group;
import dev.seratt.mailing_system_hibernate_edition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Integer> {
//    public List<Employee> getAllEmployees();
//
//    public void saveEmployee(Employee employee);
//
//    public Employee getEmployee(int id);
//
//    public void deleteEmployee(int id);
    public Set<Group> findGroupsByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    public Group findById(int id);

    public List<Group> findGroupsByUsersContaining(User user);


}
