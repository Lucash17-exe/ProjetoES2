import categoriesManager.*;
import generatePassword.*;
import storage.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileStorage fileStorage = new FileStorage();
        PasswordStorage passwordStorage = new PasswordStorage(fileStorage);

        PasswordInterface pass = PasswordFactory.generatePassword("v1");

        Category pessoal = new Category("Pessoal");

        Category microsoft = new Category("Microsoft");

        Category office = new Category("office");

        Category trello = new Category("Trello");

        Password trelloPass = new Password("conta 1",pass.generateText());

        Password officePass = new Password("conta 1",pass.generateText());

        Password officePass2 = new Password("conta 2",pass.generateText());

        pessoal.addComponent(microsoft);

        pessoal.addComponent(trello);

        trello.addComponent(trelloPass);

        microsoft.addComponent(office);

        office.addComponent(officePass);

        office.addComponent(officePass2);

        pessoal.showAll();

        System.out.println("Trello Path: " + trelloPass.getCategoryPath());
        System.out.println("Office Path: " + officePass.getCategoryPath());
        System.out.println("Office2 Path: " + officePass2.getCategoryPath());
        passwordStorage.save(pessoal);
    }
}