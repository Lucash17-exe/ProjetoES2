package storage;

import categoriesManager.Category;
import categoriesManager.Password;
import categoriesManager.PasswordCategoryInterface;

public class PasswordStorage {
    protected StorageImplementation storage;

    public PasswordStorage(StorageImplementation storage) {
        this.storage = storage;
    }

    public StorageImplementation getStorage(){
        return this.storage;
    }

    public void save(Category category) {
        traverseAndSave(category, category.getName());
    }

    private void traverseAndSave(Category category, String currentPath) {
        for (PasswordCategoryInterface component : category.getComponents()) {
            if (component instanceof Category) {
                traverseAndSave((Category) component, currentPath + "/" + component.getName());
            } else if (component instanceof Password) {
                storage.savePassword(currentPath + " - " + component.getName(), ((Password) component).getPassword());
            }
        }
    }

    public String retrieve(String categoryPath, String passName) {
        return storage.getPassword(categoryPath, passName);
    }

}