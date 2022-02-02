package bll.validators;

import java.util.regex.Pattern;

/**
 * Clasa pentru validarea introducerii unui numar de tip Integer
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class ValidatePositiveInteger implements Validator<String>{
    private static final String INTEGER_PATTERN = "[0-9]+";

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param s String-ul care se doreste a fi validat ca Integer pozitiv
     * @throws InvalidDataException exceptie proprie, aruncata in cazul in care String-ul nu poate fi in intreg pozitiv
     */
    @Override
    public void validate(String s) throws InvalidDataException {
        Pattern pattern = Pattern.compile(INTEGER_PATTERN);
        if (!pattern.matcher(s).matches()) {
            throw new InvalidDataException("Quantity has to be a positive integer");
        }
    }
}
