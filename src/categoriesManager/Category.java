package categoriesManager;

import java.awt.*;
import java.util.ArrayList;

public class Category implements PasswordCategoryInterface {
    private String name;
    private ArrayList<PasswordCategoryInterface> components = new ArrayList<>();
    private Category parent;

    public Category(String name) {
        this.name = name;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void addComponent(PasswordCategoryInterface component) {
        components.add(component);
        if (component instanceof Category) {
            ((Category) component).setParent(this);
        } else if (component instanceof Password) {
            ((Password) component).setParent(this);
        }
    }

    public void removeComponent(PasswordCategoryInterface component) {
        components.remove(component);
    }

    @Override
    public ArrayList<PasswordCategoryInterface> getComponents() {
        if (this.components == null) {
            this.components = new ArrayList<>();
        }
        return this.components;
    }

    public PasswordCategoryInterface containsComponent(String componentName){
        for (PasswordCategoryInterface component : components){
            if (component.getName().equals(componentName))
            {
                return component;
            }
        }
        return null;
    }

    public String getData() {
        return name;
    }

    public void showAll() {
        showAll(0);
    }

    public void showAll(int level) {
        String indent = "";
        for(int i = 0; i < level; i++) {
            indent += "\t";
        }
        System.out.println(indent + "📁 " + name);

        for (PasswordCategoryInterface component : components) {
            component.showAll(level + 1);
        }
    }

    public String getCategoryPath() {
        if (parent == null) {
            return name;
        }
        return parent.getCategoryPath() + "/" + name;
    }
}
