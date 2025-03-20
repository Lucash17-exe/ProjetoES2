package config;

public class ConfigManager {
    private static ConfigManager instance = null;
    private final String ALGORITHM = "AES";
    private final String SECRET_KEY = "1234567890123456";

    private String fileName = "passwords.txt";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getALGORITHM() {
        return ALGORITHM;
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
