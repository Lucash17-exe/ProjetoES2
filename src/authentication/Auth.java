package authentication;

import java.io.IOException;

public class Auth implements AuthInterface {
    protected AuthInterface auth;

    public Auth(AuthInterface Auth) {
        this.auth = Auth;
    }

    @Override
    public void auth(String username, String password) throws AuthException, IOException {
        if (!"admin".equals(username) || !"admin".equals(password)) {
            throw new AuthException("Erro");
        }
    }
}
