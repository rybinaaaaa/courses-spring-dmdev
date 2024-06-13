package core.rybina.aop;

import core.rybina.validation.UserInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
@Order(1)
public class FirstAspect {

    private static final Logger log = LoggerFactory.getLogger(FirstAspect.class);

    //    this смотрит AOP прокси типы
//    target смотрит тип объекта внутри прокси
    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {
    }

    //    option + shift + cmd + C - скопировать полный путь
//    @annotation проверяет аннотации на уровне методов
    @Pointcut("CommonPointCuts.isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    //    org.springframework.ui.Model, ..) - один параметр нужный нам и 0+ других
//    org.springframework.ui.Model, *) - один параметр нужный нам и строго один второй параметр (но любой)
    @Pointcut("CommonPointCuts.isControllerLayer() && args(org.springframework.ui.Model, ..)")
    public void hasModelParam() {
    }

    @Pointcut("CommonPointCuts.isControllerLayer() && @args(core.rybina.validation.UserInfo, ..)")
    public void hasUserInfoParamAnnotation() {
    }

    //    Смотрит имя бина
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    //    Advice:
//    args(id) указали чтобы иметь доступ к параметру id в addLogging
//    @Before вызовется до выполнения метода
    @Before(value = "CommonPointCuts.anyFindByIdServiceMethod() " +
            "&& args(id)" +
            "&& target(service)" +
            "&& this(userServiceProxy)" +
            "&& @within(transactional)",
            argNames = "joinPoint,id,service,userServiceProxy,transactional")
    public void addLogging(JoinPoint joinPoint, Object id, Object service, Object userServiceProxy, Transactional transactional) {
        log.info("invoke findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "CommonPointCuts.anyFindByIdServiceMethod()" +
            "&& target(service)",
            returning = "result", argNames = "result,service")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("after returning - invoke findById method in class {}", service);
    }

    @AfterThrowing(value = "CommonPointCuts.anyFindByIdServiceMethod()" +
            "&& target(service)",
            throwing = "ex", argNames = "ex,service")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("after throwing - invoke findById method in class {}", service);
    }

    @After(value = "CommonPointCuts.anyFindByIdServiceMethod()" +
            "&& target(service)")
    public void addLoggingAfter(Object service) {
        log.info("after (finally) - invoke findById method in class {}", service);
    }
}
