package by.gsu.epamlab.model.managers;

import by.gsu.epamlab.model.constants.CommonConstants;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle bundle = ResourceBundle.getBundle(CommonConstants.RESOURCE_CONFIG);

    public static String getProperty(String property){
        return bundle.getString(property);
    }
}
