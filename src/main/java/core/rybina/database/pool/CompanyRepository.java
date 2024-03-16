package core.rybina.database.pool;

import core.rybina.database.ConnectionPool;

public class CompanyRepository {

    private final ConnectionPool connectionPool;

    private CompanyRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static CompanyRepository of(ConnectionPool connectionPool) {
        return new CompanyRepository(connectionPool);
    }
}
