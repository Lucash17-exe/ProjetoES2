package stateManager;

import categoriesManager.Category;
import categoriesManager.Password;
import categoriesManager.PasswordCategoryInterface;

import java.util.ArrayList;

public class PasswordStateManager {
    private final ArrayList<Category> savedCategories;
    private final ArrayList<Category> unsavedCategories;

    public PasswordStateManager(ArrayList<Category> savedPasswords,
                                  ArrayList<Category> unsavedPasswords) {
        this.savedCategories = savedPasswords;
        this.unsavedCategories = unsavedPasswords;
    }

    public ArrayList<Category> getSavedCategories() {
        return savedCategories;
    }

    public ArrayList<Category> getUnsavedPasswords() {
        return unsavedCategories;
    }
}
