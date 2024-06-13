package core.rybina.aop;

import core.rybina.validation.UserInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
public class FirstAspect {

    private static final Logger log = LoggerFactory.getLogger(FirstAspect.class);

    //    @within - проверяет аннотацию на уровне класса, работает именно с аннотациями
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }


    //    within - проверяет классы просто
    @Pointcut("within(core.rybina.database.service.*Service)")
//    @Pointcut("within(core.rybina.database.service.*)")
//    @Pointcut("within(core.rybina.database.service.*ce)") и тд
    public void isServiceLayer() {
    }

    //    this смотрит AOP прокси типы
//    target смотрит тип объекта внутри прокси
    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {
    }

    //    option + shift + cmd + C - скопировать полный путь
//    @annotation проверяет аннотации на уровне методов
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    //    org.springframework.ui.Model, ..) - один параметр нужный нам и 0+ других
//    org.springframework.ui.Model, *) - один параметр нужный нам и строго один второй параметр (но любой)
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model, ..)")
    public void hasModelParam() {
    }

    @Pointcut("isControllerLayer() && @args(core.rybina.validation.UserInfo, ..)")
    public void hasUserInfoParamAnnotation() {
    }

    //    Смотрит имя бина
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    //    execution - modifiers pattern + ret.type pattern +[declaring.type pattern:name pattern] (of params) + throws pattern
//    core.rybina.database.service.* - declaring type
//    findById - name pattern
    @Pointcut("execution(public * core.rybina.database.service.*.findById(*))")
//    @Pointcut("execution(public * findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    //    Advice:
//    args(id) указали чтобы иметь доступ к параметру id в addLogging
//    @Before вызовется до выполнения метода
    @Before(value = "anyFindByIdServiceMethod() " +
            "&& args(id)" +
            "&& target(service)" +
            "&& this(userServiceProxy)" +
            "&& @within(transactional)",
            argNames = "joinPoint,id,service,userServiceProxy,transactional")
    public void addLogging(JoinPoint joinPoint, Object id, Object service, Object userServiceProxy, Transactional transactional) {
        log.info("invoke findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod()" +
            "&& target(service)",
            returning = "result", argNames = "result,service")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("after returning - invoke findById method in class {}", service);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod()" +
            "&& target(service)",
            throwing = "ex", argNames = "ex,service")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("after throwing - invoke findById method in class {}", service);
    }

    @After(value = "anyFindByIdServiceMethod()" +
            "&& target(service)")
    public void addLoggingAfter(Object service) {
        log.info("after (finally) - invoke findById method in class {}", service);
    }

    @Around(value = "anyFindByIdServiceMethod()" +
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
