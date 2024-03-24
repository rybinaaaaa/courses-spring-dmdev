package core.rybina;

import core.rybina.config.ConfigurationApplication;
import core.rybina.database.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApplication.class)) {
            CompanyService companyService = context.getBean(CompanyService.class);
//            context.getEnvironment().setActiveProfiles("web", "prod");
////            ПЕРЕСОЗДАЕМ БИНЫ, ЧТОБЫ НЕПОДХОДЯЩИЕ НЕ СОЗДАВАЛИСЬ!
//            context.refresh();
            System.out.println(companyService.findById(1));
        }
    }
}
