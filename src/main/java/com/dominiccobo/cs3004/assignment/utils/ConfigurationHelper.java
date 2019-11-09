package com.dominiccobo.cs3004.assignment.utils;

public class ConfigurationHelper {
    public static String getConfiguration(String envVar, String propertyVar, String defaultValue) {
        String envConfigValue = System.getenv(envVar);
        String propertyVarConfigValue = System.getProperty(propertyVar);
        if(envConfigValue != null && envConfigValue.isEmpty()) {
            return envConfigValue;
        }
        if(propertyVarConfigValue != null && propertyVarConfigValue.isEmpty()) {
            return propertyVarConfigValue;
        }
        return defaultValue;
    }
}
