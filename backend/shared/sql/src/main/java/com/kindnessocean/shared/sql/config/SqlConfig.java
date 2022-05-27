package com.kindnessocean.shared.sql.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan("com.kindnessocean.shared.sql.entity")
@EnableJpaRepositories(basePackages = "com.kindnessocean.shared.sql.repository")
@Configuration
@EnableTransactionManagement
public class SqlConfig {

    @Value("${com.kindnessocean.shared.config.repository.sql.url}")
    private String url;

    @Value("${com.kindnessocean.shared.config.repository.sql.username}")
    private String username;

    @Value("${com.kindnessocean.shared.config.repository.sql.password}")
    private String password;

    @Value("${com.kindnessocean.shared.config.repository.sql.databasePlatform}")
    private String databasePlatform;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databasePlatform);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
