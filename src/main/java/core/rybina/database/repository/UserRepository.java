package core.rybina.database.repository;

import core.rybina.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u " +
           "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    //    List<User> findAllByFirstNameAndLastnameContaining(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    List<User> findAllByUsername(String username);
}
