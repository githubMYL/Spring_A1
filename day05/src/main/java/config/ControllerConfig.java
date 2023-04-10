package config;

import controllers.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/** SPRING 설정*/
@Configuration
/** 검색범위 설정 */
@ComponentScan("controllers")
public class ControllerConfig {
    @Bean
    public HelloController helloController() {

        return new HelloController();
    }
}