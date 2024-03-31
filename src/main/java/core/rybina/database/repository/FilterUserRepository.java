package core.rybina.database.repository;

import core.rybina.database.entity.User;
import core.rybina.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFiler(UserFilter filter);
}
