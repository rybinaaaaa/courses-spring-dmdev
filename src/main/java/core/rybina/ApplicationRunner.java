package core.rybina;

import core.rybina.config.ConfigurationApplication;
import core.rybina.database.ConnectionPool;
import core.rybina.database.pool.CompanyRepository;
import core.rybina.database.pool.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApplication.class)) {
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
