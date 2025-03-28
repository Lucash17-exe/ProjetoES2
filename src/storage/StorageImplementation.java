package storage;

public interface StorageImplementation {
    void savePassword(String categoryPath, String password);
    String getPassword(String categoryPath);
}
