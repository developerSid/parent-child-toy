package com.example.parentchildtoy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean
    @Profile({"local"})
    public FlywayMigrationStrategy cleanMigrationStrategy() {
        return flyway -> {
            logger.debug("Cleaning up db");
            flyway.clean();
            logger.debug("Migrating db");
            flyway.migrate();
        };
    }
}
