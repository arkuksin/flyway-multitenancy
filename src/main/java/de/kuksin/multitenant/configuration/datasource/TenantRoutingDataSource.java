package de.kuksin.multitenant.configuration.datasource;

import de.kuksin.multitenant.configuration.web.ThreadTenantStorage;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadTenantStorage.getTenantId();
    }
}