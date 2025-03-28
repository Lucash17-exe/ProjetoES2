package storage;
import config.ConfigManager;
import java.io.*;

public class FileStorage implements StorageImplementation {
    private static final String FILE_PATH = ConfigManager.getInstance().getFileName();

    public FileStorage() {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void savePassword(String categoryPath, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(categoryPath + " | " + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPassword(String categoryPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts[0].equals(categoryPath)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
