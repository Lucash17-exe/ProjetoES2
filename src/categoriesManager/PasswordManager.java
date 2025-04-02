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
        // Divide o caminho em partes
        String[] pathParts = categoryPath.split("/");

        // Encontra ou cria a estrutura de categorias
        Category parentCategory = findOrCreateParentCategory(pathParts);

        // Cria e adiciona a senha à última categoria do caminho
        Password newPassword = new Password(pathParts[pathParts.length-1], password);
        parentCategory.addComponent(newPassword);

        // Marca a categoria pai como não salva
        markCategoryAsUnsaved(parentCategory);
    }

    private Category findOrCreateParentCategory(String[] pathParts) {
        Category currentCategory = null;

        // Procura na hierarquia de categorias salvas
        for (Category savedCategory : savedCategories) {
            if (savedCategory.getName().equals(pathParts[0])) {
                currentCategory = savedCategory;
                break;
            }
        }

        // Se não encontrou na raiz, cria nova categoria
        if (currentCategory == null) {
            currentCategory = new Category(pathParts[0]);
            unsavedCategories.add(currentCategory);
        }

        // Navega pelas subcategorias
        for (int i = 1; i < pathParts.length - 1; i++) {
            String subCategoryName = pathParts[i];
            Category subCategory = findSubCategory(currentCategory, subCategoryName);

            if (subCategory == null) {
                subCategory = new Category(subCategoryName);
                currentCategory.addComponent(subCategory);
                markCategoryAsUnsaved(currentCategory); // Pai precisa ser salvo
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
        // Remove de savedCategories se estiver lá
        if (savedCategories.remove(category)) {
            unsavedCategories.add(category);
        }

        // Se já está em unsavedCategories, não faz nada
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