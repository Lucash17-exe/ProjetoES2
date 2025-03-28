package categoriesManager;

import java.util.ArrayList;

public class Password implements PasswordCategoryInterface {
    private String name;
    private String password;
    private Category parent;

    public Password(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void showAll() {
        System.out.println("\t🔑 " + name + " | pass: " + password);
    }

    public void showAll(int level) {
        String indent = "";
        for(int i = 0; i < level; i++) {
            indent += "\t";
        }
        System.out.println(indent + "🔑 " + name + " | pass: " + password);
    }

    public String getData() {
        return name + " | " + password;
    }

    @Override
    public ArrayList<PasswordCategoryInterface> getComponents() {
        return null;
    }

    public String getCategoryPath() {
        if (parent == null) {
            return "";
        }
        return parent.getCategoryPath() + "/" + getData();
    }
}
