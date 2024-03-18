package core.rybina.database.pool;

import core.rybina.beanPostProcessor.InjectBean;
import core.rybina.database.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;
}
