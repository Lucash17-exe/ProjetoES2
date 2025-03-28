package categoriesManager;
import java.util.ArrayList;
import java.util.List;
public interface PasswordCategoryInterface {
    String getName();
    void showAll();
    void showAll(int level);
    String getCategoryPath();
    ArrayList<PasswordCategoryInterface> getComponents();
    String getData();
}
