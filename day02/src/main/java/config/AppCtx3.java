package config;

import models.member.MemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.format.DateTimeFormatter;

@Configuration
/**
@ComponentScan(basePackages = "models.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes={ManualBean.class}))
*/
/**
@ComponentScan(basePackages = "models.member",
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes={MemberDao.class}),
        @ComponentScan.Filter(type= FilterType.ASPECTJ, pattern = "models.member.**Service")
})
*/

public class AppCtx3 {

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return formatter;
    }
}
