package generatePassword;

public abstract class PasswordFactory {

    public static Password generatePassword(String algorithm) throws AlgorithmNotFoundException{
        if(algorithm.equalsIgnoreCase("v1")) return new PasswordAlgorithmV1();

        throw new AlgorithmNotFoundException();
    }
}
