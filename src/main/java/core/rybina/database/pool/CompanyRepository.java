package core.rybina.database.pool;

import core.rybina.beanPostProcessor.InjectBean;
import core.rybina.bfpp.Audited;
import core.rybina.bfpp.Transaction;
import core.rybina.database.ConnectionPool;
import core.rybina.database.entity.Company;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@Transaction
@Audited
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @Qualifier("rybina1")
    @Autowired
    private ConnectionPool connectionPool;

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
