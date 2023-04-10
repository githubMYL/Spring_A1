package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;



/** 스프링 컨테이너 정보 애너테이션 */
@Configuration
/** 컴포넌트 스캔으로 model 패키지 자정 */
@ComponentScan("models")
public class AppCtx {

    /** 불려왔다가 필요없으면 자동으로 소멸해줌 오토클로져블 기능이 있음 */
    @Bean(destroyMethod = "close")

    /** 데이터베이스 연결정보 메서드 */
    public DataSource dataSource() {
        /** 데이터베이스 연결정보 생성자 */
        DataSource ds = new DataSource();

        /** DB 정보 및 로그인정보 */
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        ds.setUsername("springdb2");
        ds.setPassword("aA123456");

        /** 커넥션 풀의 기본 사이즈 */
        ds.setInitialSize(2);
        /** 커넥션 풀의 최대 사이즈 */
        ds.setMaxActive(10);
        /** 유휴 시간 true 하면 있음으로 설정 */
        ds.setTestWhileIdle(true);
        /** 설정된 시간 간격마다 놀고 있는 커넥션을 풀에서 제거밀리초단위로 설정 */
        ds.setTimeBetweenEvictionRunsMillis(3000);
        /** 사용되지 않은 커넥션을 추출할 때 이 속성에서 지정한 시간 이상 비활성화 상태인 커넥션만 추출한다 */
        ds.setMinEvictableIdleTimeMillis(60000);

        return ds;
    }

    @Bean
    /** DB와 연결 해주는 메서드 */
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    /** 트랜잭션 관리 메서드 */
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }
}


