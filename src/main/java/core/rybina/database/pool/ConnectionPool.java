package core.rybina.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPool {

    private final String username;
    private final Integer poolSize;

//    @Autowired будет тут установлена по умолчанию так, что можно не писать
    public ConnectionPool(@Value("${db.username}") String username, @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
//        System.out.println("Init connection pool");
    }

    @PreDestroy
    private void destroy() {
//        System.out.println("Destroy connection pool");
    }
}
