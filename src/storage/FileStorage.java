package storage;
import categoriesManager.Category;
import categoriesManager.Password;
import categoriesManager.PasswordCategoryInterface;
import config.ConfigManager;
import java.io.*;
import java.util.ArrayList;

public class FileStorage implements StorageImplementation {
    private static final String FILE_PATH = ConfigManager.getInstance().getFileName();

    public FileStorage() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.createNewFile();
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
    public String getPassword(String category, String passName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ", 2);

                String[] passwordParts = parts[1].split(" | ");

                if (parts[0].equals(category) && passwordParts[0].equals(passName)) {
                    return passwordParts[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> mainCategories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length != 2) continue;

                String pathPart = parts[0].trim();
                String passwordValue = parts[1].trim();

                String[] pathAndName = pathPart.split(" - ", 2);
                if (pathAndName.length != 2) continue;

                String categoryPath = pathAndName[0].trim();
                String passwordName = pathAndName[1].trim();

                String[] categoryNames = categoryPath.split("/");

                String mainCategoryName = categoryNames[0];
                Category mainCategory = findOrCreateMainCategory(mainCategories, mainCategoryName);

                Category currentCategory = mainCategory;
                for (int i = 1; i < categoryNames.length; i++) {
                    currentCategory = findOrCreateSubCategory(currentCategory, categoryNames[i]);
                }

                Password password = new Password(passwordName, passwordValue);
                currentCategory.addComponent(password);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return mainCategories;
    }

    private Category findOrCreateMainCategory(ArrayList<Category> mainCategories, String name) {
        for (Category category : mainCategories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        Category newCategory = new Category(name);
        mainCategories.add(newCategory);
        return newCategory;
    }

    private Category findOrCreateSubCategory(Category parent, String name) {
        for (PasswordCategoryInterface component : parent.getComponents()) {
            if (component instanceof Category && component.getName().equals(name)) {
                return (Category) component;
            }
        }
        Category newSubCategory = new Category(name);
        parent.addComponent(newSubCategory);
        return newSubCategory;
    }
}
