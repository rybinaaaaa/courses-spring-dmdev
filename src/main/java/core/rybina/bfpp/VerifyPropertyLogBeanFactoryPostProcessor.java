package core.rybina.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;


//interface PriorityOrdered >> any value of interface Ordered
public class VerifyPropertyLogBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
