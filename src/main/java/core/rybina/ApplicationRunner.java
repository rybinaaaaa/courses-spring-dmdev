package core.rybina;

import core.rybina.database.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        Map: key(адрес класса + # + айди): Object
        System.out.println(context.getBean(ConnectionPool.class)); // если бинов одного класса несклоько, то мы не можем доставать бин по классу (непонятно какой из нескольких брать)
//        System.out.println(context.getBean("id2",  ConnectionPool.class));
        System.out.println(context.getBean("core.rybina.database.ConnectionPool#0",  ConnectionPool.class));
    }
}
