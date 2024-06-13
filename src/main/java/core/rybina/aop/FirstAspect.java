package core.rybina.aop;

import core.rybina.validation.UserInfo;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
public class FirstAspect {

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
    public void isRepositoryLayer(){
    }

//    option + shift + cmd + C - скопировать полный путь
//    @annotation проверяет аннотации на уровне методов
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

//    org.springframework.ui.Model, ..) - один параметр нужный нам и 0+ других
//    org.springframework.ui.Model, *) - один параметр нужный нам и строго один второй параметр (но любой)
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model, ..)")
    public void hasModelParam(){
    }

    @Pointcut("isControllerLayer() && @args(core.rybina.validation.UserInfo, ..)")
    public void hasUserInfoParamAnnotation(){
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
    public void findByIdServiceMethod() {
    }
}
