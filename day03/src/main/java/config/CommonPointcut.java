package config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointcut {

    @Pointcut("execution(* exam02..*(..))")
    /** 공통기능 범위 설정 */
    public void publicTarget(){}
}
