package core.rybina;

import core.rybina.database.ConnectionPool;
import core.rybina.database.pool.CompanyRepository;
import core.rybina.database.pool.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
