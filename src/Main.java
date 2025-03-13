import generatePassword.Password;
import generatePassword.PasswordAlgorithmV1;
import generatePassword.PasswordFactory;

public class Main {
    public static void main(String[] args) {

        Password pass = PasswordFactory.generatePassword("v1");

        System.out.println(pass.generateText());

    }
}