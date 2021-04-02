package com.task.backpac.config.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Setter
@Getter
@Configuration
@EnableTransactionManagement
@Slf4j
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class DatabaseConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String connectionTestQuery;

    @Bean
    public DataSource dataSource() {
        log.info("DataSource Initializing......");

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setUsername(this.username);
        hikariConfig.setPassword(this.password);
        hikariConfig.setDriverClassName(this.driverClassName);
        hikariConfig.setConnectionTestQuery(this.connectionTestQuery);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        log.info(dataSource.toString());

        return dataSource;
    }
}
