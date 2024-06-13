package core.rybina.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
@Slf4j
public class SecondAspect {

    @Around(value = "CommonPointCuts.anyFindByIdServiceMethod()" +
            "&& args(id)" +
            "&& target(service)", argNames = "joinPoint,id,service")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object id, Object service) throws Throwable {

        log.info("AROUND -- invoke findById method in class {}, with id {}", service, id);

        try {
            Object result = joinPoint.proceed();
            log.info("AROUND -- after returning - invoke findById method in class {}", service);
            return result;
        } catch (Throwable e) {
            log.info("AROUND -- after throwing - invoke findById method in class {}", service);
            throw e;
        } finally {
            log.info("AROUND -- after (finally) - invoke findById method in class {}", service);
        }
    }
}
