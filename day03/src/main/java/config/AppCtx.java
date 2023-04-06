package config;

import exam02.ImplCalculator;
import exam02.RecCalculator;
import exam02.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration          /** 스프링 설정 */
@EnableAspectJAutoProxy /** 자동 프록시 설정*/
//@EnableAspectJAutoProxy(proxyTargetClass = true)
/** @EnableAspectJAutoProxy 은 인터페이스 기반이나 (proxyTargetClass = true) 을 추가하면 클래스기반도 가능하다*/
public class AppCtx {
    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
    /**
    @Bean
    public RecCalculator calculator() {
        return new RecCalculator();
    }

    @Bean
    public ImplCalculator calculator1() {
        return new ImplCalculator();
    }
     */
    @Bean
    public CommonPointcut commonPointcut() {
        return new CommonPointcut();
    }

    @Bean
    public CachedProxy cachedProxy() {
        return new CachedProxy();
    }

    @Bean
    public  ProxyCalculator2 proxyCalculator2() {
        return new ProxyCalculator2();
    }
}
