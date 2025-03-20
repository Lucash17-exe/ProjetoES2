package generatePassword;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class PasswordAlgorithmV1 implements PasswordInterface {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.?";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final SecureRandom RANDOM = new SecureRandom();

    protected PasswordAlgorithmV1(){
    }

    public String generateText()
    {
        int length = 8;

        ArrayList<Character> password = new ArrayList<>();

        password.add(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        password.add(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        password.add(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.add(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        for (int i = 4; i < length; i++) {
            password.add(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));
        }

        Collections.shuffle(password);

        StringBuilder passwordString = new StringBuilder();
        for (char ch : password) {
            passwordString.append(ch);
        }

        return passwordString.toString();
    }

}
