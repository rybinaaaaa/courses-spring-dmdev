package core.rybina.database.repository;

import core.rybina.bfpp.Audited;
import core.rybina.bfpp.Transaction;
import core.rybina.database.pool.ConnectionPool;
import core.rybina.database.entity.Company;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Scope("prototype")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transaction
@Audited
@RequiredArgsConstructor
@Repository("companyRepository")
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @Qualifier("pool2")
    private final ConnectionPool connectionPool;
    private final List<ConnectionPool> connectionPools;

    //    Equal to
//    @Resource(name = "rybina1")
//    private ConnectionPool connectionPool;

    @PostConstruct
    private void init() {
        System.out.println("Init company Repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("Find By id method....");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("Delete By entity method....");
    }
}
