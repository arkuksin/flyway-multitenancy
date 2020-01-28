package de.kuksin.multitenant.configuration.datasource;

import de.kuksin.multitenant.configuration.web.ThreadTenantStorage;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class CarRoutingDataSource extends AbstractRoutingDataSource {

    private static final String DEFAULT_TENANT = "vw";

    @Override
    protected Object determineCurrentLookupKey() {
        return ObjectUtils.defaultIfNull(ThreadTenantStorage.getTenantName(), DEFAULT_TENANT);
    }
}