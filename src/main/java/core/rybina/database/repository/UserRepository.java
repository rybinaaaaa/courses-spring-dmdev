package core.rybina.database.repository;

import core.rybina.database.entity.Role;
import core.rybina.database.entity.User;
import core.rybina.dto.PersonalInfo2;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
//спринг будет автоматически искать FilterUserRepositoryImpl
public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository,
//3 параметр это тип данных айдишника ревизии
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {

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

    //    @Lock(LockModeType.OPTIMISTIC) - блокировка на уровне джава приложения
//    @Lock(LockModeType.PESSIMISTIC) - блокировка на уровне БД
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthdateBefore(LocalDate birthdate, Sort sort);

    //    !!!У нас не будет работать нормально offset, limit потому, что если на одного юзера будет боьше одной локали, то на него будет выделено больше одной строки, то есть декартовое произведение
//    Поэтому у нас будет неправильно количество строк, мы не поймем скроко строк пропускать и на сколько ограничивать
//    Спринг выполнит этотт метод, но отфильтрует нужный результат уже сам после того, как вытащит ВСЕХ юзеров с бд. Что есть очень плохо!
    @EntityGraph(attributePaths = {"company", "company.locales"})
// тут мы добавили countQuery, чтобы пролемонстрировать, что тот ополнительный запрос, который делается для
// узнавания кол-ва страниц (напоминаю, что работает только с типом данных Page), мы можем переписать изменив его логику
    @Query(value = "select u from User u", countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

//    List<PersonalInfo> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> tClass);

    @Query(value = "SELECT firstname, lastname, birthdate FROM users WHERE company_id=:companyId", nativeQuery = true)
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

    User saveAndFlush(User user);
}
