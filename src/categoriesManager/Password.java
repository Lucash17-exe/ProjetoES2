package categoriesManager;

public class Password implements  PasswordCategoryInterface{
    private String name;
    private String password;

    public Password(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void showAll() {
        System.out.println("\tname: " + name + "pass: " + password);
    }

    public void showAll(int level) {
        String indent = "\t".repeat(level);

        System.out.println(indent + "\uD83D\uDD12" + name + " | pass: " + password);
    }
}
