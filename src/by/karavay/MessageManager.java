package by.karavay;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourseBundle = ResourceBundle.getBundle("by.karavay.properties.messages");

    private MessageManager(){}

    public static String getProperty(String key){
        return resourseBundle.getString(key);
    }
}
