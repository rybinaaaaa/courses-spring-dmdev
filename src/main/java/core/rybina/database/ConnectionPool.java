package core.rybina.database;

import core.rybina.bfpp.Audited;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public class ConnectionPool implements InitializingBean {

    private  String username;

    @Autowired
    @Value("${db.pool.size}")
    private  String poolSize;
    private  List<Object> args;
    private Map<String, Object> properties;

    public ConnectionPool() {
    }

    public ConnectionPool(String username, String poolSize, List<Object> args, Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @PostConstruct
    private void init() {
//        System.out.println("Init connection pool");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("Properties set");
    }

    @PreDestroy
    private void destroy() {
//        System.out.println("Destroy connection pool");
    }
}
