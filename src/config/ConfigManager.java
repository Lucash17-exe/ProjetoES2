package config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static ConfigManager instance = null;
    private final String ALGORITHM = "AES";
    private final String SECRET_KEY = "1234567890123456";
    private final int MAX_CONNECTION_POOL_SIZE = 10;
    private final String API_URL = "https://passwordsApi";

    private String fileName = "passwords.txt";

    FileWriter fw = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getALGORITHM() {
        return ALGORITHM;
    }

    public String getApiURL() {
        return API_URL;
    }

    public int getMaxConnectionPoolSize() {
        return MAX_CONNECTION_POOL_SIZE;
    }

    public String getSecretKey() {
        return SECRET_KEY;
    }

    private ConfigManager(){}

    public static ConfigManager getInstance(){
        if(instance == null)
        {
            synchronized (ConfigManager.class)
            {
                if (instance == null)
                {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }
}
