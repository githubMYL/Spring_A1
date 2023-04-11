package config;

import commons.CommonLibrary;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Locale;

/** 웹 MVC 설정 항목 */

/** 스프링 설정 */
@Configuration
/** 웹 MVC 연결 */
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /** Thymeleaf 시 주석처리 했음 */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        /** jsp 파일 경로 설정 */
//        registry.jsp("/WEB-INF/view/", ".jsp");
//    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        /** 번역을 매번하지 않음 동일한 캐시파일 부담이 적음 (개발을 할때 부하가 걸릴수도 있음) */
        /** 서비스 배포시작시엔 true 로 변경 */
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        /** true = EL 식을 컴파일 해준다 false = EL 식을 컴파일하지 않는다 */
        templateEngine.setEnableSpringELCompiler(true);
        /** Java8 확장모듈 추가 temporals 식 객체*/
        templateEngine.addDialect(new Java8TimeDialect());
        /** doBody 와 비슷한 기능 */
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        /** */
        resolver.setContentType("text/html");
        /** JSP 인코딩 기본세팅을 해줌 */
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("messages.commons");
        ms.setDefaultEncoding("UTF-8");
//        ms.setDefaultLocale(Locale.KOREAN);
        return ms;
    }

    @Bean
    public CommonLibrary cLib() {
        return new CommonLibrary();
    }

    /** 컨트롤러 없이 바로 홈페이지를 띄울 수 있다 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/mypage")
                .setViewName("mypage/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
