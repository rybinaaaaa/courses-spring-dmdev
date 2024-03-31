package core.rybina.database.repository;

import core.rybina.database.entity.Role;
import core.rybina.database.entity.User;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u " +
           "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    //    List<User> findAllByFirstNameAndLastnameContaining(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying
    @Query("update User u " +
           "set u.role = :role " +
           "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthdateBeforeOrderByBirthdateDesc(LocalDate birthdate);

    List<User> findTop3ByBirthdateBefore(LocalDate birthdate, Sort sort);

    @EntityGraph(value = "User.company")
// тут мы добавили countQuery, чтобы пролемонстрировать, что тот ополнительный запрос, который делается для
// узнавания кол-ва страниц (напоминаю, что работает только с типом данных Page), мы можем переписать изменив его логику
    @Query(value = "select u from User u", countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);
}
