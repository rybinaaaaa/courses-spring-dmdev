package core.rybina;

import core.rybina.database.ConnectionPool;
import core.rybina.database.pool.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        Map: key(адрес класса + # + айди): Object
        System.out.println(context.getBean(ConnectionPool.class)); // если бинов одного класса несклоько, то мы не можем доставать бин по классу (непонятно какой из нескольких брать)
//        System.out.println(context.getBean("id2",  ConnectionPool.class));
        System.out.println(context.getBean(CompanyRepository.class));
    }
}
