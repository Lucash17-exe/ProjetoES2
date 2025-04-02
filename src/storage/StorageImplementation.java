package storage;

import categoriesManager.Category;
import categoriesManager.Password;
import categoriesManager.PasswordCategoryInterface;

import java.util.ArrayList;
import java.util.HashMap;

public interface StorageImplementation {
    void savePassword(String categoryPath, String password);
    String getPassword(String category, String passName);
    ArrayList<Category> getAllCategories();
}
