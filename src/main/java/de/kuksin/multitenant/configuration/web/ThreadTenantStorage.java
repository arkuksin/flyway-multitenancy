package de.kuksin.multitenant.configuration.web;

public class ThreadTenantStorage {

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setTenantName(String tenantName) {
        currentTenant.set(tenantName);
    }

    public static String getTenantName() {
        return currentTenant.get();
    }

    public static void clear(){
        currentTenant.remove();
    }

}
