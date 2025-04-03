package authentication;

import java.io.IOException;
import java.util.Random;

public class AuthFingerPrint extends Auth {
    public AuthFingerPrint(AuthInterface auth) {
        super(auth);
    }

    @Override
    public void auth(String username, String password) throws AuthException, IOException {
        super.auth(username, password);

        if (!verifyFingerprint(username)) {
            throw new AuthException("Falha na autenticação por impressão digital");
        }
    }

    private boolean verifyFingerprint(String username) {
        Random random = new Random();
        boolean fingerprintMatch = random.nextBoolean();
        System.out.println("Verificação de impressão digital para " + username + ": " + (fingerprintMatch ? "Sucesso" : "Falha"));
        return fingerprintMatch;
    }
}
