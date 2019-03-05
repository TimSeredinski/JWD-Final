package by.training.epam.seredinski.dao.impl;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private final static String BASE_NAME = "resources.db";

    private ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
