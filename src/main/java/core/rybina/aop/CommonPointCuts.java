package core.rybina.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointCuts {

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

    //    execution - modifiers pattern + ret.type pattern +[declaring.type pattern:name pattern] (of params) + throws pattern
//    core.rybina.database.service.* - declaring type
//    findById - name pattern
    @Pointcut("execution(public * core.rybina.database.service.*.findById(*))")
//    @Pointcut("execution(public * findById(*))")
    public void anyFindByIdServiceMethod() {
    }
}
