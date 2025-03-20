import categoriesManager.Category;
import categoriesManager.Password;
import generatePassword.PasswordInterface;
import generatePassword.PasswordFactory;

public class Main {
    public static void main(String[] args) {

        PasswordInterface pass = PasswordFactory.generatePassword("v1");

        Category pessoal = new Category("Pessoal");

        Category microsoft = new Category("Microsoft");

        Category office = new Category("office");

        Category trello = new Category("Trello");

        Password trelloPass = new Password("conta 1",pass.generateText());

        Password officePass = new Password("conta 1",pass.generateText());

        Password officePass2 = new Password("conta 1",pass.generateText());

        pessoal.addComponent(microsoft);

        pessoal.addComponent(trello);

        trello.addComponent(trelloPass);

        microsoft.addComponent(office);

        office.addComponent(officePass);

        office.addComponent(officePass2);

        pessoal.showAll();
    }
}