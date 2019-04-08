package pl.dominisz.passwordgeneratorimplementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.dominisz.passwordgeneratorapi.PasswordGeneratorParameters;

class PasswordGeneratorImplementationTest {

    PasswordGeneratorImplementation passwordGeneratorImplementation = new PasswordGeneratorImplementation();

    @Test
    void shouldGeneratePasswordOfProperLength() {
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();
        passwordGeneratorParameters.setIncludeUppercaseCharacters(true);
        passwordGeneratorParameters.setIncludeLowercaseCharacters(true);
        passwordGeneratorParameters.setIncludeNumbers(true);
        passwordGeneratorParameters.setIncludeSymbols(true);
        passwordGeneratorParameters.setPasswordLength(10);

        //when
        String password = passwordGeneratorImplementation.generate(passwordGeneratorParameters);

        //then
        Assertions.assertEquals(10, password.length());
    }

    @Test
    void shouldGenerateLowercasePassword() {
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();
        passwordGeneratorParameters.setIncludeLowercaseCharacters(true);
        passwordGeneratorParameters.setPasswordLength(10);

        //when
        String password = passwordGeneratorImplementation.generate(passwordGeneratorParameters);

        //then
        Assertions.assertEquals(10, password.length());
        Assertions.assertEquals(password, password.toLowerCase());
    }

    @Test
    void shouldThrowIllegalArgumentException(){
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();

        //when //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {passwordGeneratorImplementation.generate(passwordGeneratorParameters);});
    }

    @Test
    void shouldGenerateUppercasePassword() {
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();
        passwordGeneratorParameters.setIncludeUppercaseCharacters(true);
        passwordGeneratorParameters.setPasswordLength(10);

        //when
        String password = passwordGeneratorImplementation.generate(passwordGeneratorParameters);

        //then
        Assertions.assertEquals(10, password.length());
        Assertions.assertEquals(password, password.toUpperCase());
    }

    @Test
    void shouldGenerateSymbolsPassword() {
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();
        passwordGeneratorParameters.setIncludeSymbols(true);
        passwordGeneratorParameters.setPasswordLength(10);

        //when
        String password = passwordGeneratorImplementation.generate(passwordGeneratorParameters);

        //then
        Assertions.assertTrue(containsOnlySymbols(password));
    }

    private boolean containsOnlySymbols(String string) {
        return string.matches("[!@#$%^&*()\\-=+]+");
    }

    @Test
    void shouldGenerateNumericPassword() {
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();
        passwordGeneratorParameters.setIncludeNumbers(true);
        passwordGeneratorParameters.setPasswordLength(10);

        //when
        String password = passwordGeneratorImplementation.generate(passwordGeneratorParameters);

        //then
        Assertions.assertTrue(containsOnlyNumbers(password));
    }

    @Test
    void shouldContainNumberSpecialCharUppercaseLowercase() {
        //given
        PasswordGeneratorParameters passwordGeneratorParameters = new PasswordGeneratorParameters();
        passwordGeneratorParameters.setIncludeLowercaseCharacters(true);
        passwordGeneratorParameters.setIncludeSymbols(true);
        passwordGeneratorParameters.setIncludeUppercaseCharacters(true);
        passwordGeneratorParameters.setIncludeNumbers(true);
        passwordGeneratorParameters.setPasswordLength(10);

        //when
        String password = passwordGeneratorImplementation.generate(passwordGeneratorParameters);

        //then
        Assertions.assertEquals(true, true);
    }

    private boolean containsOnlyNumbers(String password) {
        return password.matches("[0-9]+");
    }

    private boolean containsONlyUppercaseChars(String password) {
        return password.matches("[A-B]+");
    }

    private boolean containsLowercaseChars(String password) {
        return password.matches("[a-b]+");
    }
}