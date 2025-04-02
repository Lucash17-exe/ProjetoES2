package categoriesManager;
import stateManager.PasswordStateManager;
import storage.PasswordStorage;
import java.util.ArrayList;
import java.util.List;

public class PasswordManager {
    private ArrayList<Category> savedCategories;
    private ArrayList<Category> unsavedCategories;

    public PasswordManager(ArrayList<PasswordStorage> storages) {
        this.savedCategories = new ArrayList<>();
        this.unsavedCategories = new ArrayList<>();
        for (PasswordStorage passwordStorage : storages) {
            importDataFromStorages(passwordStorage);
        }
    }

    public ArrayList<Category> getSavedCategories() {
        return savedCategories;
    }

    public ArrayList<Category> getUnsavedCategories() {
        return unsavedCategories;
    }

    private void importDataFromStorages(PasswordStorage storage) {
        savedCategories.addAll(storage.getStorage().getAllCategories());
    }

    public void addPassword(String password, String categoryPath) {
        String[] pathParts = categoryPath.split("/");

        Category parentCategory = findOrCreateParentCategory(pathParts);

        Password newPassword = new Password(pathParts[pathParts.length-1], password);
        parentCategory.addComponent(newPassword);

        markCategoryAsUnsaved(parentCategory);
    }

    private Category findOrCreateParentCategory(String[] pathParts) {
        Category currentCategory = null;

        for (Category savedCategory : savedCategories) {
            if (savedCategory.getName().equals(pathParts[0])) {
                currentCategory = savedCategory;
                break;
            }
        }

        if (currentCategory == null) {
            currentCategory = new Category(pathParts[0]);
            unsavedCategories.add(currentCategory);
        }

        for (int i = 1; i < pathParts.length - 1; i++) {
            String subCategoryName = pathParts[i];
            Category subCategory = findSubCategory(currentCategory, subCategoryName);

            if (subCategory == null) {
                subCategory = new Category(subCategoryName);
                currentCategory.addComponent(subCategory);
                markCategoryAsUnsaved(currentCategory);
            }

            currentCategory = subCategory;
        }

        return currentCategory;
    }

    private Category findSubCategory(Category parent, String name) {
        for (PasswordCategoryInterface component : parent.getComponents()) {
            if (component instanceof Category && component.getName().equals(name)) {
                return (Category) component;
            }
        }
        return null;
    }

    private void markCategoryAsUnsaved(Category category) {
        if (savedCategories.remove(category)) {
            unsavedCategories.add(category);
        }

    }

    public void saveChanges(PasswordStorage passwordStorage) {
        List<Category> categoriesToSave = new ArrayList<>(unsavedCategories);

        for (Category category : categoriesToSave) {
            passwordStorage.save(category);
            unsavedCategories.remove(category);
            savedCategories.add(category);
        }
    }

    public PasswordStateManager saveState() {
        return new PasswordStateManager(
                new ArrayList<>(savedCategories),
                new ArrayList<>(unsavedCategories)
        );
    }

    public void restoreState(PasswordStateManager memento) {
        this.savedCategories = memento.getSavedCategories();
        this.unsavedCategories = memento.getUnsavedPasswords();
    }
}