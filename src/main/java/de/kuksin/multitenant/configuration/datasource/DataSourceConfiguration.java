package de.kuksin.multitenant.configuration.datasource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;


@Configuration
public class DataSourceConfiguration {

    public static final String DEFAULT_LOCATION = "db/migration/";

    private final DataSourceProperties dataSourceProperties;

    public DataSourceConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public DataSource dataSource() {
        TenantRoutingDataSource customDataSource = new TenantRoutingDataSource();
        customDataSource.setTargetDataSources(dataSourceProperties.getDatasources());
        return customDataSource;
    }

    @PostConstruct
    public void migrate() {
        dataSourceProperties
                .getDatasources()
                .entrySet()
                .forEach(this::migrate);
    }

    private void migrate(Map.Entry<Object, Object> set) {
        Flyway flyway = Flyway.configure()
                .locations("/db/migration/common", "db/migration/" + set.getKey())
                .dataSource((DataSource) set.getValue())
                .load();
        flyway.migrate();
    }
}
