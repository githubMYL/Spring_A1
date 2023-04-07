package config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Order(2)
@Aspect
public class CachedProxy {

    private Map<Long, Object> cacheData = new HashMap<>();

    @Pointcut("execution(* exam02..**(long))")
    public void cachedTarget() {}

    @Around("config.CommonPointcut.publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Long num = (Long)args[0];
        if ( cacheData.containsKey(num)) {      /** 기존 데이터가 있으면 캐시 테이터 사용 */
            System.out.printf("[%d] 캐시 사용 %n", num);
            return cacheData.get(num);
        }

        Object result = joinPoint.proceed();    /** 다음 프록시가 있으면 다음 프록시가 수행 -> factorial 프록시 */


        cacheData.put(num,result);              /** 없으면 캐시에 기록 */
        System.out.printf("[%d] 캐시 추가 %n", num);

        return result;
    }
}
