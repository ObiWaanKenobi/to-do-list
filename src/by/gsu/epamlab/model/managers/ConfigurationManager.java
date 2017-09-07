package by.gsu.epamlab.model.managers;

import by.gsu.epamlab.model.constants.Constants;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle bundle = ResourceBundle.getBundle(Constants.RESOURCE_CONFIG);

    public static String getProperty(String property){
        return bundle.getString(property);
    }
}
