package core.rybina.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import core.rybina.database.entity.Role;
import core.rybina.database.entity.User;
import core.rybina.database.querydsl.QPredicates;
import core.rybina.dto.PersonalInfo;
import core.rybina.dto.UserFilter;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static core.rybina.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_BY_COMPANY_AND_ROLE = """
            select firstname, lastname, birthdate 
            from users where company_id = ? and role = ?""";

    private static final String UPDATE_COMPANY_AND_ROLE = """
            update users
            set company_id = ?,
            role = ?
            where id = ?""";

    @Override
    public List<User> findAllByFiler(UserFilter filter) {
        Predicate predicate = QPredicates.builer()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthdate(), user.birthdate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_ALL_BY_COMPANY_AND_ROLE, new RowMapper<PersonalInfo>() {
            @Override
            public PersonalInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PersonalInfo(rs.getString("firstname"), rs.getString("lastname"), rs.getDate("birthdate").toLocalDate());
            }
        }, companyId, role.name());
    }

    @Override
    public void updateCompanyAnyRole(List<User> users) {
        List<Object[]> args = users.stream().map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()}).toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }
}
