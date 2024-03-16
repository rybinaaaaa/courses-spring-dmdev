package core.rybina.database.pool;

import core.rybina.database.ConnectionPool;

public class CompanyRepository {

    private final ConnectionPool connectionPool;

    public CompanyRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
