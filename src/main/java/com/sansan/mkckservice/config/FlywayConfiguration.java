package com.sansan.mkckservice.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {
    @Value("${spring.flyway.url}")
    private String databaseUrl;
    @Value("${spring.r2dbc.username}")
    private String userName;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway(Flyway.configure().dataSource(databaseUrl, userName, password));
        flyway.migrate();
        return flyway;
    }
}
