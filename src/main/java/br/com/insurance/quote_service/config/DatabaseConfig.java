package br.com.insurance.quote_service.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private String poolName;
    private String maxLifetime;

    @Bean
    public DataSource dataSource () {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db_insurance");
        config.setUsername("root");
        config.setPassword("verysecret");
        config.setPoolName("insurancePool");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(2);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }
}

