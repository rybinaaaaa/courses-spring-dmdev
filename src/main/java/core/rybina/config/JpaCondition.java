package core.rybina.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JpaCondition implements Condition {

//    Этот метод возвращает булевое значение может ли мы срздать бин проверяя какое-то условие. в Нашем сулчае бин, над котоорым строит аннтоация @Condition(JpaCondition.class) создаться только при условии что драйвер постгреса загрузится
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
        context.getClassLoader().loadClass("org.postgresql.Driver");
        return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
