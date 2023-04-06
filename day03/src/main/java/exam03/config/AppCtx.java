package exam03.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppCtx {

    @Bean
    public DataSource dataSource() {    /** 127.0.0.1 */
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        DataSource ds = new DataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl(url);
        ds.setUsername("scott");
        ds.setPassword("tiger");                    /** 여기까지 DB 연결 설정 */

        /** 연결 커넥션 풀 설정 */
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);                  /** 체크 */
        ds.setTimeBetweenEvictionRunsMillis(3000);  /** 3초 마다 유휴 객체 연결 확인 */
        ds.setMinEvictableIdleTimeMillis(60000);    /** 60초 이후 연결 객체 다시 생성 */

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }

    @Bean
    public TestDao testDao() {
        return new TestDao();
    }
}
