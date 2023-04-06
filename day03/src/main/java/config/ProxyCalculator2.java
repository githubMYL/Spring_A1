package config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Order(2)
@Aspect /** 공통기능을 구현한 프록시 클래스 */
public class ProxyCalculator2 {


    @Around("config.CommonPointcut.publicTarget()")
    /** Proceeding 은 핵심기능을 수행 모든걸 반환할 수 있기에 반환값은 Object */
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.nanoTime();     /** 공통기능 */

        Object result = joinPoint.proceed();    /** 핵심기능 수행 factorial */

        long endTime = System.nanoTime();       /** 공통기능 */
        System.out.printf("걸린 시간 :  %d%n", endTime-startTime);

        return result;

    }
}
