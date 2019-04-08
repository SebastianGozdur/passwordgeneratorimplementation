package pl.dominisz.passwordgeneratorimplementation;

import pl.dominisz.passwordgeneratorapi.PasswordGeneratorParameters;
import pl.dominisz.passwordgeneratorapi.PasswordGeneratorService;

import java.util.List;
import java.util.Random;

public class PasswordGeneratorImplementation implements PasswordGeneratorService {

    private static final Random RANDOM = new Random();
    private static final String LOWERCASE_CHARACTERS = "qwertyuiopasdfghjklzxcvbnm";
    private static final String UPPERCASE_CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String SYMBOLS = "!@#$%^&*()-=+";
    private static final String NUMBERS = "0123456789";

    public String generate(PasswordGeneratorParameters passwordGeneratorParameters) {
        if(isInvalid(passwordGeneratorParameters)){
            throw new IllegalArgumentException();
        }

        String alphabet = generateAlphabet(passwordGeneratorParameters);
        String password = generateRequiredTypes(passwordGeneratorParameters);
        Integer passwordLength = password.length();
                ;
        for (int i = 0; i < passwordGeneratorParameters.getPasswordLength() - passwordLength; i++) {
            password += alphabet.charAt(RANDOM.nextInt(alphabet.length()));
        }

        return password;
    }

    private String generateAlphabet(PasswordGeneratorParameters passwordGeneratorParameters) {
        String alphabet = "";

        alphabet += passwordGeneratorParameters.isIncludeLowercaseCharacters() ? LOWERCASE_CHARACTERS : "";
        alphabet += passwordGeneratorParameters.isIncludeUppercaseCharacters() ? UPPERCASE_CHARACTERS : "";
        alphabet += passwordGeneratorParameters.isIncludeSymbols() ? SYMBOLS : "";
        alphabet += passwordGeneratorParameters.isIncludeNumbers() ? NUMBERS : "";

        return alphabet;
    }

    private String generateRequiredTypes(PasswordGeneratorParameters passwordGeneratorParameters) {
        String password = "";

        password += passwordGeneratorParameters.isIncludeLowercaseCharacters() ? getRandomCharacter(LOWERCASE_CHARACTERS) : "";
        password += passwordGeneratorParameters.isIncludeUppercaseCharacters() ? getRandomCharacter(UPPERCASE_CHARACTERS) : "";
        password += passwordGeneratorParameters.isIncludeSymbols() ? getRandomCharacter(SYMBOLS) : "";
        password += passwordGeneratorParameters.isIncludeNumbers() ? getRandomCharacter(NUMBERS) : "";

        return password;
    }

    private Character getRandomCharacter(String alphabet) {
        return alphabet.charAt(RANDOM.nextInt(alphabet.length()));
    }

    private boolean isInvalid(PasswordGeneratorParameters passwordGeneratorParameters) {
        return passwordGeneratorParameters.getPasswordLength() <= 0 ||
                !(passwordGeneratorParameters.isIncludeLowercaseCharacters()
                || passwordGeneratorParameters.isIncludeNumbers()
                || passwordGeneratorParameters.isIncludeSymbols()
                || passwordGeneratorParameters.isIncludeUppercaseCharacters());
    }


    public List<String> generate(PasswordGeneratorParameters passwordGeneratorParameters, int count) {
        return null;
    }
}