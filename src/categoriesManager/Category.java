package categoriesManager;

import java.util.ArrayList;

public class Category implements PasswordCategoryInterface{
    private String name;
    private ArrayList<PasswordCategoryInterface> components = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addComponent(PasswordCategoryInterface component){
        components.add(component);
    }

    public void removeComponent(PasswordCategoryInterface component){
        components.remove(component);
    }

    public void showAll() {
        int level = 0;
        String indent = "\t".repeat(level);

        System.out.println(indent + "📁 " + name);

        for (PasswordCategoryInterface component : components) {
            component.showAll(level + 1);
        }
    }

    public void showAll(int level) {
        String indent = "\t".repeat(level);

        System.out.println(indent + "📁 " + name);

        for (PasswordCategoryInterface component : components) {
            component.showAll(level + 1);
        }
    }

}
