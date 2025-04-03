import categoriesManager.*;
import generatePassword.*;
import stateManager.PasswordHistoryManager;
import storage.*;
import authentication.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        AuthInterface baseAuth = new Auth(null);
        AuthInterface authWithMFA = new AuthMultifactor(baseAuth);
        AuthInterface authWithFingerprint = new AuthFingerPrint(baseAuth);

        String username = "admin";
        String password = "admin";

        System.out.println("Tentando autenticação com MFA e Impressão Digital...");
        authWithFingerprint.auth(username, password);
        System.out.println("Autenticação bem-sucedida!");

       /* // 1. Inicialização dos componentes
        FileStorage fileStorage = new FileStorage();
        PasswordStorage storage = new PasswordStorage(fileStorage);
        ArrayList<PasswordStorage> storages = new ArrayList<>();
        storages.add(storage);
        PasswordManager passwordManager = new PasswordManager(storages);
        PasswordInterface passGenerator = PasswordFactory.generatePassword("v1");
        PasswordHistoryManager historyManager = new PasswordHistoryManager();

        passwordManager.addPassword(passGenerator.generateText(), "Geral/bruno");

        System.out.println("\nSAVED:\n\n");
        for (Category category : passwordManager.getSavedCategories())
            category.showAll();

        System.out.println("\nUNSAVED:\n\n");
        for (Category category : passwordManager.getUnsavedCategories())
            category.showAll();

        System.out.println("\n\nSALVANDO ESTADO...\n\n");
        historyManager.saveState(passwordManager);


        System.out.println("\n\nSALVANDO...\n\n");
        passwordManager.saveChanges(storage);

        System.out.println("\nSAVED:\n\n");
        for (Category category : passwordManager.getSavedCategories())
            category.showAll();

        System.out.println("\nUNSAVED:\n\n");
        for (Category category : passwordManager.getUnsavedCategories())
            category.showAll();

        historyManager.undo(passwordManager);

        System.out.println("\n\nRESTAURANDO ESTADO 1...\n\n");

        System.out.println("\nSAVED:\n\n");
        for (Category category : passwordManager.getSavedCategories())
            category.showAll();

        System.out.println("\nUNSAVED:\n\n");
        for (Category category : passwordManager.getUnsavedCategories())
            category.showAll();

        System.out.println("\nFIM\n\n");*/

        /*


        PasswordInterface passGenerator = PasswordFactory.generatePassword("v1");

        // 2. Adiciona senhas com caminhos completos
        passwordManager.addPassword(passGenerator.generateText(), "Pessoal/Trello/conta1");
        passwordManager.addPassword(passGenerator.generateText(), "Pessoal/Microsoft/office/conta1");
        passwordManager.addPassword(passGenerator.generateText(), "Pessoal/Microsoft/office/conta 2");

        // 3. Salva estado atual antes de persistir
        historyManager.saveState(passwordManager);

        // 4. Persiste as alterações
        passwordManager.saveChanges(storages.get(0));

        // 5. Exibe a estrutura (opcional - precisaríamos de um método getRootCategories no PasswordManager)
        System.out.println("Estrutura completa:");
        for (Category category : passwordManager.getSavedCategories()) {
            category.showAll();
        }

        // 6. Recuperação de senha
        String senhaRecuperada = storages.get(0).retrieve(
                "Pessoal/Microsoft/office/conta1",
                "conta1"
        );
        System.out.println("\nSenha recuperada: " + senhaRecuperada);

        // 7. Opcional: Restauração do estado
        historyManager.undo(passwordManager);

         */
    }

}