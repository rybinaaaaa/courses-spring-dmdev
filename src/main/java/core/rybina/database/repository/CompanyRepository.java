package core.rybina.database.repository;

import core.rybina.bfpp.Audited;
import core.rybina.bfpp.Transaction;
import core.rybina.database.pool.ConnectionPool;
import core.rybina.database.entity.Company;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Integer> {

    Optional<Company> findById(Integer id);
    void delete(Company entity);
}
