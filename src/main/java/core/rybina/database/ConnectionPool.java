package core.rybina.database;

import java.util.List;
import java.util.Map;

public class ConnectionPool {

    private final String username;
    private final String poolSize;
    private final List<Object> args;
    private final Map<String, Object> properties;

    public ConnectionPool(String username, String poolSize, List<Object> args, Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }
}
