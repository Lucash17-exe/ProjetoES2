package authentication;

import java.io.IOException;
import java.util.Random;

public class AuthMultifactor extends Auth {
    public AuthMultifactor(AuthInterface auth) {
        super(auth);
    }

    @Override
    public void auth(String username, String password) throws AuthException, IOException {
        super.auth(username, password);

        if (!verifyMultifactor(username)) {
            throw new AuthException("Falha na autenticação multifator");
        }
    }

    private boolean verifyMultifactor(String username) {
        Random random = new Random();
        int generatedCode = random.nextInt(900000) + 100000;
        System.out.println("Código MFA gerado para " + username + ": " + generatedCode);

        int userInput = generatedCode;
        return userInput == generatedCode;
    }
}
