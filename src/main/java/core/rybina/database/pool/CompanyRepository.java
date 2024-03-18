package core.rybina.database.pool;

import core.rybina.beanPostProcessor.InjectBean;
import core.rybina.bfpp.Transaction;
import core.rybina.database.ConnectionPool;
import core.rybina.database.entity.Company;
import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Transaction
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool connectionPool;

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
