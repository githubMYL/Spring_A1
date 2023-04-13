package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DbConfig {

    @Value("${sb.username}")
    private String username;
    @Value("${sb.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();

        /** 계정설정부분 */
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:Oracle:thin:@localhost:1521:orcl");
        ds.setUsername(username);
        ds.setPassword(password);

        /** 커넥션 풀 설정 부분 */
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setTimeBetweenEvictionRunsMillis(3000);
        ds.setMinEvictableIdleTimeMillis(60000);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }
}
