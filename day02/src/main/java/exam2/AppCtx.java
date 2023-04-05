package exam2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx {
    @Bean(initMethod = "init", destroyMethod = "close")
    @Scope("prototype") /** 싱글톤이 아닌방식으로 생성 */
    public Message message() {
        return new Message();
    }
}
