package core.rybina.database.repository;

import core.rybina.database.entity.User;
import core.rybina.dto.UserFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFiler(UserFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> userRoot = criteria.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.firstname() != null) {
            predicates.add(cb.like(userRoot.get("firstname"), filter.firstname()));
        }
        if (filter.lastname() != null) {
            predicates.add(cb.like(userRoot.get("lastname"), filter.lastname()));
        }
        if (filter.birthdate() != null) {
            predicates.add(cb.lessThan(userRoot.get("birthdate"), filter.birthdate()));
        }

        criteria.select(userRoot).where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
